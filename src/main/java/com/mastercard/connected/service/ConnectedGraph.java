package com.mastercard.connected.service;

import com.mastercard.connected.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/***
 * This component is representing connection between cities as a graph.
 * This allows us to find the routes between them in an efficient way.
 *
 * @author shekar nyala
 */
public class ConnectedGraph {

    private static final Logger logger = LoggerFactory.getLogger(ConnectedGraph.class);
    final private HashMap<City, Set<City>> connections;

    public ConnectedGraph() {
        connections = new HashMap<>();
    }

    /**
     * create an Edge as a road between given cities
     * @param origin
     * @param destination
     */
    public void addRoad(City origin, City destination) {
        if (!connections.containsKey(origin)) {
            connections.put(origin, newHashSet(destination));
        } else {
            connections.get(origin).add(destination);
        }

        // As the road is bi-directional, we have to add a road from destination to origin as well
        if (!connections.containsKey(destination)) {
            connections.put(destination, newHashSet(origin));
        } else {
            connections.get(destination).add(origin);
        }
    }

    /**
     * find whether there is a road between given cities
     *
     * @param origin
     * @param destination
     * @return true if there exists a road between {origin} and {destination}, false otherwise
     */
    public boolean hasRoadBetween(City origin, City destination) {
        logger.info("** Checking road between [{} and {}] cities", origin, destination);
        if(!(connections.containsKey(origin) && connections.containsKey(destination)))
            return false;

        boolean directRoad = connections.get(origin).contains(destination);

        boolean indirectRoad = false;
        if (!directRoad) {
            indirectRoad = bfs(origin, destination);
        }
        return directRoad||indirectRoad;
    }

    private static final <T> Set<T> newHashSet(T... elements) {
        Set<T> set = new HashSet<T>();
        Collections.addAll(set, elements);
        return set;
    }

    /**
     * traverse each city that is reachable from {origin} and return true if there is any city, which matches to given {destination}
     * @param origin
     * @param destination
     * @return true if there is a city on the way, which matches with destination
     */
    public boolean bfs(final City origin, final City destination) {
        // Assume No cities are connected/traversed
        final Map<City, Boolean> visited = new HashMap<>();
        for (City city : connections.keySet()) {
            visited.put(city, false);
        }

        City current = origin;

        // Queue for verifying next set of direct connections
        LinkedList<City> queue = new LinkedList<City>();
        visited.put(origin, true);
        queue.add(origin);

        while (queue.size() != 0) {
            // remove city from queue and verify whether it matches to destination
            current = queue.poll();
            if (current.equals(destination)) return true;

            //get all direct connections from <origin> or <intermediary> city
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

    /**
     * find total number of cities in the entire network of connections
     * @return count of cities
     */
    public int getCityCount() {
        return connections.size();
    }
}
