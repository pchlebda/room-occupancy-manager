package com.smart.room.it

import com.fasterxml.jackson.databind.ObjectMapper
import com.smart.room.RoomsUsageTestData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class RoomUsageControllerIT extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    def "should calculate usage and value"() {
        given:
        def expected = RoomsUsageTestData.getDefaultResponse()
        def request = RoomsUsageTestData.getDefaultRequest()

        expect:
        mockMvc.perform(post("/api/v1/room")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)))
    }
}
