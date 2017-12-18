package com.vibe.app.controllers;

import com.google.gson.Gson;
import com.vibe.app.dto.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    public void should_return_202() throws Exception {

        Person david = new Person("David", "12/25/1999");

        mockMvc.perform(post("/api/greet/person")
                .contentType("application/json")
                .content(gson.toJson(david)))
                .andExpect(status().isAccepted());
    }

    @Test
    public void should_return_400_if_date_is_null() throws Exception {

        Person david = new Person("David", null);

        mockMvc.perform(post("/api/greet/person")
                .contentType("application/json")
                .content(gson.toJson(david)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_return_400_if_date_is_in_invalid_format() throws Exception {

        Person david = new Person("David", "25121999");

        mockMvc.perform(post("/api/greet/person")
                .contentType("application/json")
                .content(gson.toJson(david)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_return_400_if_name_is_null() throws Exception {

        Person david = new Person(null, "12/25/1999");

        mockMvc.perform(post("/api/greet/person")
                .contentType("application/json")
                .content(gson.toJson(david)))
                .andExpect(status().isBadRequest());
    }

}