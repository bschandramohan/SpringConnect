package com.bschandramohan.learn.springbatch

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.*
import org.springframework.batch.support.transaction.TransactionAwareProxyFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Configures and runs a Spring Batch job.
 */
@Configuration
@EnableBatchProcessing
open class SampleBatchJob {

    @Autowired
    lateinit var job: JobBuilderFactory

    @Autowired
    lateinit var step: StepBuilderFactory

    @Bean
    open fun doJob(step1: Step): Job {
        return job.get("doJob")
                .flow(step1)
                .end()
                .build()
    }

    @Bean
    open fun step1(): Step {
        return step.get("step1")
                .chunk<Int, Int>(2) // Chunk the processing to 2 items at a time
                .reader(myReader())
                .processor(myProcessor())
                .writer(myWriter())
                .build()
    }

    @Bean
    open fun myReader(): ItemReader<Int> {
        return CustomItemReader(mutableListOf(1, 3, 5, 7, 2, 4, 6, 8))
    }

    @Bean
    open fun myProcessor(): ItemProcessor<Int, Int> {
        return SquareItemProcessor()
    }

    @Bean
    open fun myWriter(): ItemWriter<Int> {
        return CustomItemWriter()
    }
}

class CustomItemReader<Int>(private var items: MutableList<Int>) : ItemReader<Int> {

    @Throws(Exception::class, UnexpectedInputException::class, NonTransientResourceException::class, ParseException::class)
    override fun read(): Int? {

        return if (!items.isEmpty()) {
            items.removeAt(0)
        } else null
    }
}

class CustomItemWriter<Int> : ItemWriter<Int> {
    private var output: MutableList<Int> = TransactionAwareProxyFactory.createTransactionalList()

    @Throws(Exception::class)
    override fun write(items: List<Int>) {
        items.forEach { i -> println("Output=$i") }
        output.addAll(items)
    }
}

class SquareItemProcessor<Int> : ItemProcessor<Int, Int> {
    override fun process(number: Int): Int {
        println("processing number=$number")

        val result: Number = when (number) {
            is kotlin.Int -> number * number
            else -> throw IllegalArgumentException()
        }

        @Suppress("UNCHECKED_CAST")
        return result as Int
    }
}
