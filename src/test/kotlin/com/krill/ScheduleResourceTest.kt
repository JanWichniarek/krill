package com.krill

import com.krill.core.schedule.ScheduleDTO
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.Test
import javax.ws.rs.core.Response

@QuarkusTest
@QuarkusTestResource(
    PostgresResource::class
)
class ScheduleResourceTest {

    @Test
    fun testCreateScheduleEndpoint() {
        val scheduleCreatedResponse = given()
            .`when`().post("/schedules")
            .then()
            .statusCode(Response.Status.CREATED.statusCode)
            .contentType(ContentType.JSON)
            .body("scheduleId", notNullValue())
            .extract().`as`(ScheduleDTO::class.java)

        given().`when`().get("/schedules/"+scheduleCreatedResponse.scheduleId)
            .then()
            .statusCode(Response.Status.OK.statusCode)
            .contentType(ContentType.JSON)
            .body("scheduleId", equalTo(scheduleCreatedResponse.scheduleId.toString()))
    }

}