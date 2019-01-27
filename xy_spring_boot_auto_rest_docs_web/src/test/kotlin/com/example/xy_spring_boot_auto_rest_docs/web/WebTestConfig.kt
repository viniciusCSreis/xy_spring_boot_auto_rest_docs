package com.example.xy_spring_boot_auto_rest_docs.web

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(Application::class)
open class WebTestConfig