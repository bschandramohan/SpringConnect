import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.0"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"
}

group = "com.bschandramohan.learn.springconnect"
version = "1.0.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    // Couldn't get HAL browser to work
//    implementation("org.springframework.data:spring-data-rest-webmvc:3.4.1")
//    implementation("org.springframework.boot:spring-boot-starter-data-rest")
//    implementation("org.springframework.data:spring-data-rest-hal-browser:3.3.5-release")

    // AOP
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.aspectj:aspectjweaver")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")
//    implementation("javax.validation:validation-api")
//    implementation("org.hibernate:hibernate-validator:6.0.12.Final")

    // Swagger
    implementation("org.springdoc:springdoc-openapi-ui:1.5.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
