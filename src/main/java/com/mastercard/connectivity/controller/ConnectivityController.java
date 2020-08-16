package com.mastercard.connectivity.controller;

import com.mastercard.connectivity.service.ConnectivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectivityController {


    @GetMapping(value = "/connected", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String isThereARouteBetween(@RequestParam("origin") final String origin, @RequestParam("destination") final String destination){
        String response = "no";
        return response;
    }
}
