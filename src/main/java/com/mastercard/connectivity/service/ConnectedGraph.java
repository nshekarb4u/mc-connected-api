package com.mastercard.connectivity.service;

import com.mastercard.connectivity.model.City;
import lombok.Getter;

import java.util.*;

@Getter
public class ConnectedGraph {
    final private HashMap<City, Set<City>> connections;

    public ConnectedGraph() {
        connections = new HashMap<>();
    }

    public void addRoad(City origin, City destination) {
        if (!connections.containsKey(origin)) {
            connections.put(origin, new HashSet<City>() {{
                add(destination);
            }});
        } else {
            connections.get(origin).add(destination);
        }

        // As the road is bi-directional, we want to add an road from destination to origin as well
        if (!connections.containsKey(destination)) {
            connections.put(destination, new HashSet<City>() {{
                add(origin);
            }});
        } else {
            connections.get(destination).add(origin);
        }
    }

    public boolean hasRoadBetween(City origin, City destination) {
        boolean directRoad = connections.containsKey(origin)
                && connections.get(origin) != null
                && connections.get(origin).contains(destination);
        boolean indirectRoad = false;
        if (!directRoad) {
            indirectRoad = bfs(origin, destination);
        }
        return directRoad||indirectRoad;
    }

    public boolean bfs(final City origin, final City destination) {
        // Assume No cities are connected/traversed
        final Map<City, Boolean> visited = new HashMap<>();
        for (City city : connections.keySet()) {
            visited.put(city, false);
        }

        City current = origin;

        // Create a queue for verifying next set of direct connections
        LinkedList<City> queue = new LinkedList<City>();
        visited.put(origin, true);
        queue.add(origin);

        while (queue.size() != 0) {
            // remove city from queue and verify whether it matches to destination
            current = queue.poll();
            if (current.equals(destination)) return true;

            //get all direct connections from origin city
            Set<City> directRoads = connections.get(current);
            for (City destCity : directRoads) {
                if (!visited.get(destCity)) {
                    visited.put(destCity, true);
                    queue.add(destCity);
                }
            }
        }
        return false;
    }

    public int getCityCount() {
        /*Set<City> cities =  new HashSet<>();
        for(Map.Entry<City,Set<City>> entry:connections.entrySet()){
            cities.add(entry.getKey());
            cities.addAll(entry.getValue());
        }
        return cities.size();*/
        return connections.size();
    }
}
