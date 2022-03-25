package com.example.restaurant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TableRepository extends CrudRepository<Table, String> {
    @Transactional
    List<Restaurant> deleteAllByRestaurant(Restaurant restaurant);
}
