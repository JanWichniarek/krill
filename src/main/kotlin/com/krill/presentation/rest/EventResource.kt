package com.krill.presentation.rest

import com.krill.core.category.CategoryDTO
import com.krill.core.event.*
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter
import java.time.LocalDateTime
import java.util.*
import javax.enterprise.context.RequestScoped
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("schedules/{scheduleId}/events")
class EventResource(val service: EventService) {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Parameter(name="scheduleId", `in`=ParameterIn.PATH, required = true)
    fun create(@PathParam("scheduleId") scheduleId:UUID, eventDTO: EventDTO): EventDTO = service.create(scheduleId, eventDTO)

    @Path("sample")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun sample(): EventDTO = EventDTO(0L, "Sample event", EventType.ONE_TIME, TimeType.POINT, LocalDateTime.now(), "This is sample event", setOf(
        CategoryDTO(0L, "Sample category", "blue")
    ), Priority.MEDIUM, "this should be an icon url")

}