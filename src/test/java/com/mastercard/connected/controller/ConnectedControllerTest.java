package com.mastercard.connected.controller;

import com.mastercard.connected.service.ConnectedService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {ConnectedController.class})
public class ConnectedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConnectedService service;

    @Test
    public void test_When_Connected_Road_Exists() throws Exception {
        String origin="Chicago";
        String destination="Charlotte";
        Mockito.when(service.hasRoadBetween(origin,destination)).thenReturn(true);
        this.mockMvc
                .perform(get("/connected")
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
                        .accept(MediaType.TEXT_PLAIN_VALUE)
                        .param("origin",origin)
                        .param("destination",destination))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("yes"));
        verify(service, times(1)).hasRoadBetween(origin,destination);
    }
    @Test
    public void test_When_Origin_City_Is_Empty() throws Exception {
        String origin="";
        String destination="Charlotte";
        Mockito.when(service.hasRoadBetween(origin,destination)).thenReturn(false);
        this.mockMvc
                .perform(get("/connected")
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
                        .accept(MediaType.TEXT_PLAIN_VALUE)
                        .param("origin",origin)
                        .param("destination",destination))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("no"));
        verify(service, times(0)).hasRoadBetween(origin,destination);
    }
    @Test
    public void test_When_Destination_City_Is_Empty() throws Exception {
        String origin="Chicago";
        String destination="";
        Mockito.when(service.hasRoadBetween(origin,destination)).thenReturn(false);
        this.mockMvc
                .perform(get("/connected")
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
                        .accept(MediaType.TEXT_PLAIN_VALUE)
                        .param("origin",origin)
                        .param("destination",destination))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("no"));
        verify(service, times(0)).hasRoadBetween(origin,destination);
    }
    @Test
    public void test_When_Unexpected_Input_Is_Supplied() throws Exception {
        this.mockMvc
                .perform(get("/connected")
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
                        .accept(MediaType.TEXT_PLAIN_VALUE))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void test_With_Root_Context() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andDo(print()).andExpect(status().is4xxClientError());
        verify(service, never()).hasRoadBetween(anyString(),anyString());
    }
    @Test
    public void test_When_CitiesAreNotConnected() throws Exception {
        this.mockMvc
                .perform(get("/connected")
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
                        .accept(MediaType.TEXT_PLAIN_VALUE)
                        .param("origin","Unknown")
                        .param("destination","Not_decided"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("no"));
        verify(service, times(1)).hasRoadBetween(anyString(),anyString());
    }
    @Test
    public void test_When_Invalid_Http_Method_Is_Used() throws Exception {
        this.mockMvc
                .perform(post("/connected")
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
                        .accept(MediaType.TEXT_PLAIN_VALUE)
                        .param("origin","Unknown")
                        .param("destination","Not_decided"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("no"));
    }
    @Test
    public void test_When_Invalid_MediaType_Is_Used() throws Exception {
        this.mockMvc
                .perform(get("/connected")
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
                        .accept(MediaType.APPLICATION_PDF)
                        .param("origin","Unknown")
                        .param("destination","Not_decided"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("no"));
    }
    @Test
    public void test_When_Request_Params_Are_Blank() throws Exception {
        this.mockMvc
                .perform(get("/connected")
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
                        .accept(MediaType.TEXT_PLAIN_VALUE)
                        .param("origin","")
                        .param("destination",""))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("no"));
    }
    @Test
    public void test_When_Internal_Service_Exception() throws Exception {
        String origin="Chicago";
        String destination="Charlotte";
        Mockito.when(service.hasRoadBetween(origin,destination)).thenThrow(NullPointerException.class);
        this.mockMvc
                .perform(get("/connected")
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
                        .accept(MediaType.TEXT_PLAIN_VALUE)
                        .param("origin",origin)
                        .param("destination",destination))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("Unexpected Error at server side. Please contact API Team if it repeats."));
        verify(service, times(1)).hasRoadBetween(origin,destination);
    }
}
