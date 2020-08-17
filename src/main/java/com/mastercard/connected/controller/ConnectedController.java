package com.mastercard.connected.controller;

import com.mastercard.connected.service.ConnectedService;
import com.mastercard.connected.util.ApiResullt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connected")
@Api(value = "Connected Service",
        produces = "'yes' if there is a road between given cities, 'no' otherwise.",
        consumes = "Source and Destination City Names")
public class ConnectedController {

    @Autowired
    private ConnectedService service;

    @ApiOperation(value = "find whether given cities connected by road", response = String.class)
    @GetMapping(consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String roadExitsBetween(@RequestParam("origin") final String origin, @RequestParam("destination") final String destination){
        if(StringUtils.isEmpty(origin) || StringUtils.isEmpty(destination)){
            return ApiResullt.NO.getValue();
        }
        boolean roadExists = service.hasRoadBetween(origin, destination);
        if(roadExists) return ApiResullt.YES.getValue();
        return ApiResullt.NO.getValue();
    }
}
