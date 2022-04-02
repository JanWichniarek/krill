package com.krill.service

import com.krill.dto.EventDTO
import javax.enterprise.context.RequestScoped

@RequestScoped
class EventService {
    fun create(eventDTO: EventDTO): EventDTO {
        TODO("Not yet implemented")
    }
}