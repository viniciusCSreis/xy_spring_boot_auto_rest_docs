package com.example.xy_spring_boot_auto_rest_docs.web.city.controller

import com.example.xy_spring_boot_auto_rest_docs.web.BaseTest
import org.junit.Assert.*
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class CityControllerTest : BaseTest(){
    @Test
    fun findAllCitiesTest() {
        this.mockMvc.perform(
                RestDocumentationRequestBuilders.get("/v1/cities")
        )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
    }

    @Test
    fun createCityTest() {

        val json = "{" +
                "\"name\": \"uberlândia\"," +
                "\"type\": \"URBAN\"," +
                "\"description\": \"City where everybody use uber :D\"" +
                "}"

        this.mockMvc.perform(
                RestDocumentationRequestBuilders.post("/v1/cities")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andReturn()
    }
}