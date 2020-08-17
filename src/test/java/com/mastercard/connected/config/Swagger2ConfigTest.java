package com.mastercard.connected.config;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import springfox.documentation.spring.web.plugins.Docket;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Swagger2Config.class)
public class Swagger2ConfigTest {

    @Autowired
    private Swagger2Config swagger2Config;

    @Test
    public void loadDocketConfig(){
        Docket apiDocket = swagger2Config.api();

        Assertions.assertNotNull(apiDocket);
    }

}