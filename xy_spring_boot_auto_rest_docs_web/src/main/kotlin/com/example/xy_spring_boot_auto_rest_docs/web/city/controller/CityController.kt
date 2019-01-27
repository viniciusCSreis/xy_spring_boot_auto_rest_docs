package com.example.xy_spring_boot_auto_rest_docs.web.city.controller

import com.example.xy_spring_boot_auto_rest_docs.api.v1.CityApi
import com.example.xy_spring_boot_auto_rest_docs.api.v1.request.CreateCityRequest
import com.example.xy_spring_boot_auto_rest_docs.api.v1.response.CityTpe
import com.example.xy_spring_boot_auto_rest_docs.api.v1.response.CreateCityResponse
import com.example.xy_spring_boot_auto_rest_docs.api.v1.response.ListAllCitiesResponse
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
class CityController : CityApi {


    override fun listAllCities(): List<ListAllCitiesResponse> {

        val list = ArrayList<ListAllCitiesResponse>()
        list.add(ListAllCitiesResponse("${UUID.randomUUID()}","uberlândia",CityTpe.URBAN,"City where everybody use uber :D"))
        list.add(ListAllCitiesResponse("${UUID.randomUUID()}","uberaba",CityTpe.RURAL))

        return list

    }

    override fun createCity(@Valid @RequestBody createCityRequest: CreateCityRequest) : CreateCityResponse{
        return CreateCityResponse("${UUID.randomUUID()}")
    }

}