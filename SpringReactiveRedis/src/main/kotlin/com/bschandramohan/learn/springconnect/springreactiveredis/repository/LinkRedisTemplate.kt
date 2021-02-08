package com.bschandramohan.learn.springconnect.springreactiveredis.repository

import com.bschandramohan.learn.springconnect.springreactiveredis.domain.Link
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class LinkRedisTemplate {
    @Bean
    fun redisTemplateForLink(reactiveRedisConnectionFactory: ReactiveRedisConnectionFactory): ReactiveRedisTemplate<String, Link> {
        val keySerializer = StringRedisSerializer()
        val valueSerializer = Jackson2JsonRedisSerializer(Link::class.java)
        val context = RedisSerializationContext.newSerializationContext<String, Link>(keySerializer).value(valueSerializer).build()

        return ReactiveRedisTemplate(reactiveRedisConnectionFactory, context)
    }
}
