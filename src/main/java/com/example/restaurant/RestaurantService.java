package com.example.restaurant;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    private List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant createRestaurant(Restaurant restaurantToCreate) {
        restaurants.add(restaurantToCreate);
        return restaurantToCreate;
    }
    public void assignClientsOnRestaurant(String restaurantId, int clients) throws Exception {
        Restaurant restaurant = findRestaurant(restaurantId);
        restaurant.addClients(clients);

    }
    private Restaurant findRestaurant(String restaurantID) throws Exception {
        for (Restaurant restaurant : new ArrayList<>(restaurants)) {
            if (restaurant.getRestaurantId().equals(restaurantID)) {
                return restaurant;
            }
        }
        throw new Exception("No s'ha trobat");
    }
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Restaurant getRestaurant( String restaurantId) throws Exception {
        for (Restaurant restaurant : new ArrayList<>(restaurants)) {
            if (restaurant.getRestaurantId().equals(restaurantId)) {
                return restaurant;
            }
        }
        throw new Exception("No s'ha trobat");
    }
    public List<Table> getTables( String restaurantId) throws Exception {
        Restaurant restaurant = findRestaurant(restaurantId);
        List<Table> tables = restaurant.getTables();
        return tables;
    }
    public Table getTable(String restaurantId,String tableId) throws Exception {
        Restaurant restaurant = findRestaurant(restaurantId);
        Table table = restaurant.getTable(tableId);
        return table;
    }
    public void removeAllRestaurants() {
        restaurants = new ArrayList<>();
    }

    public void removeRestaurant( String restaurantId) {
        for (Restaurant restaurant : new ArrayList<>(restaurants)) {
            if (restaurant.getRestaurantId().equals(restaurantId)) {
                restaurants.remove(restaurant);
            }
        }
    }
    public void removeAllTables( String restaurantId) throws Exception {
        Restaurant restaurant = findRestaurant(restaurantId);
        restaurant.getTables().clear();
    }
    public void removeTable(String restaurantId,  String tableId) throws Exception {
        Restaurant restaurant = findRestaurant(restaurantId);
        restaurant.removeTable(tableId);

    }
    public void updateRestaurant( Restaurant restaurantToUpdate,  String restaurantId) throws Exception {
        for (Restaurant restaurant : new ArrayList<>(restaurants)) {
            if (restaurant.getRestaurantId().equals(restaurantId)) {
                restaurant.setType(restaurantToUpdate.getType());
                return;
            }
        }
        throw new Exception("No s'ha trobat");
    }



}
