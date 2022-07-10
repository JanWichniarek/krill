package com.krill.presentation.rest

import com.krill.core.schedule.ScheduleService
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter
import java.time.LocalDate
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

@Path("schedules")
class ScheduleResource(val service: ScheduleService, val eventResource: EventResource) {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun create(@Context uriInfo: UriInfo): Response {
        val schedule = service.create()
        val uri = uriInfo.absolutePathBuilder.path(schedule.scheduleId.toString()).build()
        return Response.created(uri).entity(schedule).build()
    }

    @Path("{scheduleId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun get(
        @PathParam("scheduleId") scheduleId: String,
        @QueryParam("eventsFrom") eventsFrom: LocalDate?,
        @QueryParam("eventsTo") eventsTo: LocalDate?
    ): Response = service.get(scheduleId, eventsFrom, eventsTo)
        ?.let { Response.ok().entity(it).build() }
        ?: Response.status(Response.Status.NOT_FOUND).build()


}