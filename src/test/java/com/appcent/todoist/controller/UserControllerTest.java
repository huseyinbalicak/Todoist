package com.appcent.todoist.controller;

import com.appcent.todoist.BaseTest;
import com.appcent.todoist.TodoistApplication;
import com.appcent.todoist.config.H2TestProfileJpaConfig;
import com.appcent.todoist.dto.save.UserSaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {TodoistApplication.class, H2TestProfileJpaConfig.class})
class UserControllerTest extends BaseTest {

    private static final String BASE_PATH = "/users";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void findAll() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH).content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
/*
    @Test
    void save() throws Exception {

        UserSaveRequestDto userSaveRequestDto = UserSaveRequestDto.builder()
                .firstName("Hasan")
                .lastName("balicak")
                .email("hasanbal@gmail.com")
                .userName("hasan123")
                .password("balhasan12")
                .build();

        String body = objectMapper.writeValueAsString(userSaveRequestDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(body).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
*/
}