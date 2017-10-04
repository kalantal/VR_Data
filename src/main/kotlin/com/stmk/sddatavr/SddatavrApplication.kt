package com.stmk.sddatavr

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SddatavrApplication {}

fun main(args: Array<String>) {
    SpringApplication.run(SddatavrApplication::class.java, *args)
}
