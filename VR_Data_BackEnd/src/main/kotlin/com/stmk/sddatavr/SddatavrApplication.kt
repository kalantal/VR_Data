package com.stmk.sddatavr

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration

@SpringBootApplication
@EnableAutoConfiguration(exclude = arrayOf(JacksonAutoConfiguration::class))
class SddatavrApplication {}

fun main(args: Array<String>) {
    SpringApplication.run(SddatavrApplication::class.java, *args)
}
