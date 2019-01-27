package com.example.xy_spring_boot_auto_rest_docs.api.v1

import com.example.xy_spring_boot_auto_rest_docs.api.v1.request.CreateCityRequest
import com.example.xy_spring_boot_auto_rest_docs.api.v1.response.CreateCityResponse
import com.example.xy_spring_boot_auto_rest_docs.api.v1.response.ListAllCitiesResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("v1/cities")
interface CityApi {


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listAllCities() : List<ListAllCitiesResponse>

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCity(@Valid @RequestBody createCityRequest: CreateCityRequest) : CreateCityResponse


}