package com.example;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Main {
    public static void main(String[] args) {
        // Create a MongoDB client
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        // Get the database
        MongoDatabase database = mongoClient.getDatabase("Employee");

        // Get the collection
        MongoCollection<Document> collection = database.getCollection("employee");

        // Create documents to insert
        Document employee1 = new Document("Employee Id", "101")
            .append("Employee Name", "Abi")
            .append("Department", "Computer Science Engineering")
            .append("Role", "HOD");

        Document employee2 = new Document("Employee Id", "102")
            .append("Employee Name", "Arun")
            .append("Department", "Computer Science Engineering")
            .append("Role", "Professor");

        Document employee3 = new Document("Employee Id", "103")
            .append("Employee Name", "Aravind")
            .append("Department", "Computer Science Engineering")
            .append("Role", "Assistant");

        // Insert documents
        collection.insertOne(employee1);
        collection.insertOne(employee2);
        System.out.println("Documents inserted successfully!");

        // Display all employees (Test Case 1)
        System.out.println("Test Case 1: List of Employees:");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }

        // Insert another employee and display the updated list
        collection.insertOne(employee3);
        System.out.println("Updated Employee List:");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }

        // Test Case 2: Search for an employee by ID and confirm that the correct information is returned
        String searchId = "102"; // Employee Id to search for
        System.out.println("Test Case 2: Search for Employee with ID " + searchId);

        Document employee = collection.find(Filters.eq("Employee Id", searchId)).first();
        if (employee != null) {
            System.out.println("Employee found: " + employee.toJson());
        } else {
            System.out.println("Employee with ID " + searchId + " not found.");
        }

        // Close the connection
        mongoClient.close();
    }
}
