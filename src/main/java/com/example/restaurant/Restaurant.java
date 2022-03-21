package com.example.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {
    private String restaurantId = UUID.randomUUID().toString();
    private static final int MAX_CAPACITY = 24;
    private static final int MAX_NUMBER_OF_TABLES = 4;


    private String name;
    private int type;
    private static final int PIZZA = 1;
    private static final int KEBAB = 2;
    private static final int CHINO = 3;

    private List<Table> tables = new ArrayList<>();

    public Restaurant() {

    }

    public Restaurant(String name, int type) throws Exception {
        checkName(name);
        checkType(type);
        this.name = name;
        this.type = type;
    }

    private void checkType(int type) throws Exception {
        if (type != PIZZA && type != CHINO && type != KEBAB) {
            throw new Exception();
        }
    }

    public int getType() {
        return type;
    }

    private void checkName(String name) throws Exception {
        if (name.equals("")) throw new Exception();
    }

    public String getName() {
        return name;
    }

    public int getCurrentSeatings() {
        int result = 0;
        for (Table table : tables) {
            result += table.getCurrentSeatings();
        }
        return result;
    }

    public int getRemainingSeats() {
        return MAX_CAPACITY - getCurrentSeatings();
    }


    public void addClients(int numOfPeople) throws Exception {
        checkPeopleCanEnter(numOfPeople);

        while (numOfPeople > 0 && hasRemainingTables()) {
            Table table = new Table();
            numOfPeople = table.addClients(numOfPeople);
            tables.add(table);
        }

        if (numOfPeople > 0) throw new Exception("No quedan taules");
    }

    private boolean hasRemainingTables() {
        return this.tables.size() < MAX_NUMBER_OF_TABLES;
    }

    private void checkPeopleCanEnter(int numOfPeople) throws Exception {
        if ((this.getCurrentSeatings() + numOfPeople) > MAX_CAPACITY)
            throw new Exception("Massa gent");
    }

    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public String printTablesStatus() {
        String result = "";
        int i = 1;
        for (Table table : tables) {
            result += "Mesa " + i + " :" + table.getCurrentSeatings() + " personas \n";
            i++;
        }
        return result;
    }

    public void removeTable(String tableId)  {

        for (int i = 0; i < tables.size(); i++) {

            Table table = tables.get(i);

            if (table.getTableId().equals(tableId)) {
                tables.remove(i);
                return;
            }
        }

}

    public Table getTable(String tableId) throws Exception {
        for (Table table : tables) {
            if (table.getTableId().equals(tableId)) {
                return table;
            }
        }
        throw new Exception("No s'ha trobat");
    }


    public List<Table> getTables() {
        return tables;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRestaurantId() {
        return restaurantId;
    }
}
