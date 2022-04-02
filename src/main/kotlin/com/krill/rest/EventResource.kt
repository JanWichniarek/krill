package com.krill.rest

import com.krill.dto.CategoryDTO
import com.krill.dto.EventDTO
import com.krill.entity.EventType
import com.krill.entity.Priority
import com.krill.entity.TimeType
import com.krill.service.EventService
import java.time.LocalDateTime
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("events")
class EventResource(val service: EventService) {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun create(eventDTO: EventDTO): EventDTO = service.create(eventDTO)

    @Path("sample")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun sample(): EventDTO = EventDTO("Sample event", EventType.ONE_TIME, TimeType.POINT, LocalDateTime.now(), "This is sample event", setOf(CategoryDTO("Sample category", "blue")), Priority.MEDIUM, "this should be an icon url")

}