package com.example.restaurant;


import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantRestController {
    private RestaurantService restaurantService;

    public RestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }



    @PostMapping("/restaurants")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurantToCreate) {
        return restaurantService.createRestaurant(restaurantToCreate);
    }

    @PostMapping("/restaurants/{restaurantId}/tables")
    public void assignClientsOnRestaurant(@PathVariable String restaurantId, @RequestBody String jsonString) throws Exception {
        JSONObject json = new JSONObject(jsonString);
        int clients = json.getInt("clients");

        restaurantService.assignClientsOnRestaurant(restaurantId, clients);
    }


    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/restaurants/{restaurantId}")
    public Restaurant getRestaurant(@PathVariable String restaurantId) throws Exception {
       return restaurantService.getRestaurant(restaurantId);
    }

    @GetMapping("/restaurants/{restaurantId}/tables")
    public List<Table> getTables(@PathVariable String restaurantId) throws Exception {
        return restaurantService.getTables(restaurantId);
    }

    @GetMapping("/restaurants/{restaurantId}/tables/{tableId}")
    public Table getTable(@PathVariable String restaurantId, @PathVariable String tableId) throws Exception {
       return restaurantService.getTable(restaurantId, tableId);
    }

    @DeleteMapping("/restaurants")
    public void removeAllRestaurants() {
        restaurantService.removeAllRestaurants();
    }

    @DeleteMapping("/restaurants/{restaurantId}")
    public void removeRestaurant(@PathVariable String restaurantId) {
        restaurantService.removeRestaurant(restaurantId);
    }

    @DeleteMapping("/restaurants/{restaurantId}/tables")
    public void removeAllTables(@PathVariable String restaurantId) throws Exception {
       restaurantService.removeAllTables(restaurantId);
    }


    @DeleteMapping("/restaurants/{restaurantId}/tables/{tableId}")
    public void removeTable(@PathVariable String restaurantId, @PathVariable String tableId) throws Exception {
        restaurantService.removeTable(restaurantId,tableId);

    }

    @PutMapping("/restaurants/{restaurantId}")
    public void updateRestaurant(@RequestBody Restaurant restaurantToUpdate, @PathVariable String restaurantId) throws Exception {
       restaurantService.updateRestaurant(restaurantToUpdate,restaurantId);
    }

}
