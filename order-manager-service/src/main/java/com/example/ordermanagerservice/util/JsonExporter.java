package com.example.ordermanagerservice.util;

import com.example.ordermanagerservice.entity.Order;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonExporter {

    public static void exportOrdersToJson(List<Order> orders, String path) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(orders, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
