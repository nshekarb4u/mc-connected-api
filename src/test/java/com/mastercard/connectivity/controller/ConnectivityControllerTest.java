package com.mastercard.connectivity.controller;

import com.mastercard.connectivity.service.ConnectivityService;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
public class ConnectivityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConnectivityService service;

    @Test
    public void test_When_Unexpected_Input_Is_Supplied() throws Exception {
        this.mockMvc
                .perform(get("/connected"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("no"));
    }
    @Test
    public void test_With_Root_Context() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("no"));
    }
    @Test
    public void test_When_CitiesAreNotConnected() throws Exception {
        this.mockMvc
                .perform(get("/connected")
                        .param("origin","Unknown")
                        .param("destination","Not_decided"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("no"));
    }
    @Test
    public void test_When_Invalid_Http_Method_Is_Used() throws Exception {
        this.mockMvc
                .perform(post("/connected")
                        .param("origin","Unknown")
                        .param("destination","Not_decided"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("no"));
    }
    @Test
    public void test_When_Invalid_MediaType_Is_Used() throws Exception {
        this.mockMvc
                .perform(get("/connected")
                        .accept(MediaType.APPLICATION_PDF)
                        .param("origin","Unknown")
                        .param("destination","Not_decided"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("no"));
    }
}
