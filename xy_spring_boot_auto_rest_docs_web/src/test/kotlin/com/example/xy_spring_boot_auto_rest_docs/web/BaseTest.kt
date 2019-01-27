package com.example.xy_spring_boot_auto_rest_docs.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Before
import org.apache.commons.io.IOUtils
import org.junit.Rule
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.restdocs.JUnitRestDocumentation
import capital.scalable.restdocs.AutoDocumentation
import capital.scalable.restdocs.jackson.JacksonResultHandlers.prepareJackson
import capital.scalable.restdocs.response.ResponseModifyingPreprocessors
import org.springframework.restdocs.cli.CliDocumentation
import org.springframework.restdocs.http.HttpDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@WebAppConfiguration
@SpringBootTest
@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [WebTestConfig::class])
abstract class BaseTest {
    companion object {
        const val ORGANIZATION = "zup"
        const val ORGANIZATION_SUCCESS = "success"
        const val APPLICATION = "bff_test"
        const val CHANNEL = "61354735-eec6-4d99-ac37-d6d8e0bbbcf2"
        const val ORGANIZATION_DESCRIPTION = "Identify your organization"
        const val APPLICATION_DESCRIPTION = "Identify your application"
        const val STRING_TYPE = "String"
        const val LONG_TYPE = "Long"
        const val INT_TYPE = "Int"
        const val LIST_TYPE = "List"
        const val OBJECT_TYPE = "Object"
        const val MAP_TYPE = "Map"
        const val MAY_NOT_BE_NULL = "may not be null"
        const val BOOLEAN = "Boolean"
        const val JSON_NODE_TYPE = "JsonNode"

    }

    fun loadJsonAsString(path: String): String =
            IOUtils.toString(javaClass.classLoader.getResourceAsStream(path), "UTF-8")


    @Rule
    @JvmField
    final val restDocumentation = JUnitRestDocumentation("target/generated-snippets")

    @Autowired
    private lateinit var context: WebApplicationContext

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    lateinit var mockMvc: MockMvc


    @Before
    fun setUp() {

        //MockMVC without tenant
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .alwaysDo<DefaultMockMvcBuilder>(prepareJackson(objectMapper))
                .alwaysDo<DefaultMockMvcBuilder>(
                        document(
                                "{class-name}/{method-name}",
                                Preprocessors.preprocessRequest(
                                        ResponseModifyingPreprocessors.replaceBinaryContent(),
                                        ResponseModifyingPreprocessors.limitJsonArrayLength(objectMapper),
                                        Preprocessors.prettyPrint()
                                ),
                                Preprocessors.preprocessResponse(
                                        ResponseModifyingPreprocessors.replaceBinaryContent(),
                                        ResponseModifyingPreprocessors.limitJsonArrayLength(objectMapper),
                                        Preprocessors.prettyPrint()
                                )
                        )
                )
                .apply<DefaultMockMvcBuilder>(
                        documentationConfiguration(restDocumentation).uris()
                                .withScheme("http")
                                .withHost("localhost")
                                .withPort(8080)
                                .and().snippets()
                                .withDefaults(
                                        CliDocumentation.curlRequest(),
                                        HttpDocumentation.httpRequest(),
                                        HttpDocumentation.httpResponse(),
                                        AutoDocumentation.requestFields(),
                                        AutoDocumentation.responseFields(),
                                        AutoDocumentation.pathParameters(),
                                        AutoDocumentation.requestParameters(),
                                        AutoDocumentation.description(),
                                        AutoDocumentation.methodAndPath(),
                                        AutoDocumentation.section()
                                )
                )
                .build()

    }
}