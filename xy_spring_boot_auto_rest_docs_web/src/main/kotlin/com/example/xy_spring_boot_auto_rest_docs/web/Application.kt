package com.example.xy_spring_boot_auto_rest_docs.web

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class Application


fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}