package com.krill.presentation.rest

import java.time.LocalDate
import javax.ws.rs.ext.ParamConverter

class LocalDateConverter : ParamConverter<LocalDate?> {
    override fun toString(value: LocalDate?): String = value.toString()

    override fun fromString(value: String?): LocalDate? = value?.let { LocalDate.parse(it) }
}