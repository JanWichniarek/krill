package com.krill.presentation.rest

import java.lang.reflect.Type
import java.time.LocalDate
import javax.ws.rs.ext.ParamConverter
import javax.ws.rs.ext.ParamConverterProvider
import javax.ws.rs.ext.Provider


@Provider
class LocalDateParamConverterProvider : ParamConverterProvider {
    override fun <T> getConverter(
        rawType: Class<T>, genericType: Type?,
        annotations: Array<Annotation?>?
    ): ParamConverter<T>? {
        return if (rawType == LocalDate::class.java) LocalDateConverter() as ParamConverter<T> else null
    }

}
