package com.example.restaurant;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "tables")
public class Table {

    @Id
    private String tableId= UUID.randomUUID().toString();
    private static final int MAX_CAPACITY = 6;
    private int currentSeatings = 0;

    @JsonIgnore
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Table() {

    }

    public int addClients(int numOfPeople) throws Exception {
        checkValidNumOfPeople(numOfPeople);
        this.currentSeatings = numOfPeople;
        if (this.currentSeatings > MAX_CAPACITY) {
            this.currentSeatings = MAX_CAPACITY;
        }
        return numOfPeople - currentSeatings;
    }

    private void checkValidNumOfPeople(int numOfPeople) throws Exception {
        if (numOfPeople <= 0) throw new Exception();
    }

    public int getCurrentSeatings() {
        return currentSeatings;
    }

    public String getTableId() {
        return tableId;
    }


}
