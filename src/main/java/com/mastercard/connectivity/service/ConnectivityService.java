package com.mastercard.connectivity.service;

import com.mastercard.connectivity.model.City;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
@Getter
@Setter
public class ConnectivityService {

    private static final Logger logger = LoggerFactory.getLogger(ConnectivityService.class);
    private final ConnectedGraph connectedGraph = new ConnectedGraph();;

    @Value("app.routes.file-name")
    private String fileName;

    @PostConstruct
    public ConnectedGraph loadCitiesFile() throws Exception {
        try {
            File resourceFile = new ClassPathResource(fileName).getFile();
            Scanner scanner = new Scanner(resourceFile);
            logger.info("** resource file contents:{}", resourceFile.getAbsolutePath());
            int count = 0;
            while (scanner.hasNextLine()) {
                String road = scanner.nextLine();
                logger.info("{}.{}", ++count, road);
                String[] cities = road.split(",");
                connectedGraph.addRoad(new City(cities[0].trim()),new City(cities[1].trim()));
            }
            scanner.close();
        } catch (FileNotFoundException fileNotFoundException) {
            logger.error("Required Mandatory city routes file is missing,fileName={}", fileName);
            throw new FileNotFoundException(fileNotFoundException.getMessage());
        } catch (Exception otherException) {
            logger.error("Unexpected error while reading routes text file, fileName={},error={}"
                    , fileName, otherException.getMessage());
            throw new Exception(otherException.getMessage());
        }
        return connectedGraph;
    }

    public boolean hasRoadBetween(String origin, String destination){
        return connectedGraph.hasRoadBetween(new City(origin), new City(destination));
    }

}
