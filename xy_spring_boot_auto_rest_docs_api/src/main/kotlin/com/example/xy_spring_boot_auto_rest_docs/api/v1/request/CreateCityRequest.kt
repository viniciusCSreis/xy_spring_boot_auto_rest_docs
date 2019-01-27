package com.example.xy_spring_boot_auto_rest_docs.api.v1.request

import org.hibernate.validator.constraints.NotEmpty
import javax.validation.constraints.Pattern

data class CreateCityRequest(

        /**
         * Name of City
         */
        @field:[NotEmpty]
        val name: String? = null,
        /**
         * Type of City
         */
        @field:[Pattern(regexp = "RURAL|CAPITAL|URBAN") NotEmpty]
        val type: String? = null,

        /**
         * Description of City
         */
        val description: String? = null

)