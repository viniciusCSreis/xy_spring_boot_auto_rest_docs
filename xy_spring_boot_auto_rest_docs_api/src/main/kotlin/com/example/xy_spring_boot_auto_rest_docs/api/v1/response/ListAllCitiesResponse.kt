package com.example.xy_spring_boot_auto_rest_docs.api.v1.response

import org.hibernate.validator.constraints.NotEmpty
import javax.validation.constraints.Pattern

/**
 * Response of List All Cities End point
 */
data class ListAllCitiesResponse (
        /**
         * Id of City
         */
        val id:String,
        /**
         * Name of City
         */
        val name: String,
        /**
         * Type of City
         */
        val type: CityTpe?,

        /**
         * Description of City
         */
        val description: String? = null
)

enum class CityTpe{
        RURAL,
        CAPITAL,
        URBAN
}