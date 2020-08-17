package com.mastercard.connected.service;

import com.mastercard.connected.model.City;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ConnectedServiceTest {

    private static ConnectedService service;

    @BeforeAll
    public static void setUp(){
        service = new ConnectedService();
    }

    @Test
    void verify_default_cities_file_getLoaded_successfully() throws Exception {
        service.setFileName("city.txt");
        ConnectedGraph connectedGraph = service.loadCitiesFile();
        assertEquals(6,connectedGraph.getCityCount());
    }
    @Test
    void verify_when_cities_file_is_notAvailable() {
        service.setFileName("file_is_not_found.txt");
        assertThrows(FileNotFoundException.class,()->service.loadCitiesFile());
    }
    @Test
    void verify_when_cities_file_is_not_in_csv_format() {
        service.setFileName("plain_text_no_comma.txt");
        assertThrows(Exception.class,()->service.loadCitiesFile());
    }
    @Test
    void verify_bi_directional_road_connection() throws Exception {
        service.setFileName("city.txt");
        ConnectedGraph connectedGraph = service.loadCitiesFile();
        City origin = new City("Newark");
        City target = new City("Boston");
        assertEquals(true,connectedGraph.hasRoadBetween(origin,target));
        assertEquals(true,connectedGraph.hasRoadBetween(target,origin));
    }
    @Test
    void verify_when_indirect_road_connection() throws Exception {
        service.setFileName("city.txt");
        ConnectedGraph connectedGraph = service.loadCitiesFile();
        City origin = new City("Boston");
        City target = new City("Philadelphia");
        assertEquals(true,connectedGraph.hasRoadBetween(origin,target));
    }
    @Test
    void verify_when_no_road_connection() throws Exception {
        service.setFileName("city.txt");
        ConnectedGraph connectedGraph = service.loadCitiesFile();
        assertFalse(service.hasRoadBetween("Philadelphia","Albany"));
    }
    @Test
    void verify_direct_road_connection() throws Exception {
        service.setFileName("city.txt");
        service.loadCitiesFile();
        String origin = "Newark";
        String target = "Boston";
        assertTrue(service.hasRoadBetween(origin,target));
    }
    @Test
    void verify_when_given_origin_city_is_no_available() throws Exception {
        service.setFileName("city.txt");
        service.loadCitiesFile();
        String origin = "ZSDFGTHG";
        String target = "Boston";
        assertFalse(service.hasRoadBetween(origin,target));
    }
    @Test
    void verify_when_given_destination_city_is_no_available() throws Exception {
        service.setFileName("city.txt");
        service.loadCitiesFile();
        String origin = "Boston";
        String target = "WSEFRGHH#$";
        assertFalse(service.hasRoadBetween(origin,target));
    }
}