package com.example.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private TableRepository tableRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, TableRepository tableRepository) {
        this.restaurantRepository = restaurantRepository;
        this.tableRepository = tableRepository;
    }

    public Restaurant createRestaurant(Restaurant restaurantToCreate) {
        this.restaurantRepository.save(restaurantToCreate);
        return restaurantToCreate;
    }
    public void assignClientsOnRestaurant(String restaurantId, int clients) throws Exception {
        Restaurant restaurant = findRestaurant(restaurantId);
        List<Table>tables = restaurant.addClients(clients);
        tableRepository.saveAll(tables);

    }
    private Restaurant findRestaurant(String restaurantId) throws Exception {
        return restaurantRepository.findById(restaurantId).get();

    }
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        this.restaurantRepository.findAll().forEach(restaurants :: add);
        return restaurants;
    }

    public Restaurant getRestaurant( String restaurantId) throws Exception {
        return restaurantRepository.findById(restaurantId).get();

    }
    public List<Table> getTables( String restaurantId) throws Exception {
          Restaurant restaurant = findRestaurant(restaurantId);
          return restaurant.getTables();

    }
    public Table getTable(String restaurantId,String tableId) throws Exception {
          return tableRepository.findById(tableId).get();

    }
    public void removeAllRestaurants() {
        restaurantRepository.deleteAll();

    }

    public void removeRestaurant( String restaurantId) {
        restaurantRepository.deleteById(restaurantId);

    }
    public void removeAllTables( String restaurantId) throws Exception {
        Restaurant restaurant = findRestaurant(restaurantId);
        tableRepository.deleteAllByRestaurant(restaurant);

    }
    public void removeTable(String restaurantId,  String tableId) throws Exception {
        tableRepository.deleteById(tableId);


    }
    public void updateRestaurant( Restaurant restaurantToUpdate,  String restaurantId) throws Exception {
        Restaurant restaurant = findRestaurant(restaurantId);
        restaurant.setType(restaurantToUpdate.getType());
        restaurant.setName(restaurantToUpdate.getName());
        restaurantRepository.save(restaurantToUpdate);

    }
    
}
