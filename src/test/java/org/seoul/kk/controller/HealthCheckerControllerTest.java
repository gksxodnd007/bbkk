package org.seoul.kk.controller;

import org.junit.Test;
import org.seoul.kk.SpringMockMvcTestSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HealthCheckerControllerTest extends SpringMockMvcTestSupport {

    @Test
    public void healthCheckTest() throws Exception {
        this.mockMvc.perform(get("/v1/health-checker"))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.deployDate").exists())
                .andExpect(jsonPath("$.deployVersion").exists())
                .andExpect(jsonPath("$.distributor").exists());
    }

}
