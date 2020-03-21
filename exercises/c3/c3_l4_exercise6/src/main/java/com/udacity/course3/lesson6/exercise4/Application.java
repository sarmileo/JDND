package com.udacity.course3.lesson6.exercise4;

import com.mongodb.Block;
import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class Application {

    public static void main(String[] args) {
        // STEP 1: Craft the URI to connect to your local MongoDB server
        // Host: localhost
        // Port: 27017 (default)
        // Username: course3
        // Password: course3
        // DB: jdnd-c3
        // Mongodb initialization parameters.
        int port_no = 27017;
        String host_name = "localhost", db_name = "jdnd-c3", db_coll_name = "club_members";
        String username = "course3", password = "course3";

        // Mongodb connection string.
        //String uri = "mongodb://course3:course3@localhost:27017/jdnd-c3";
        String client_url = "mongodb://" + username + ":" + password + "@" + host_name + ":" + port_no + "/" + db_name;

        // STEP 2: Create a MongoClient
        MongoClient mongoClient = MongoClients.create(client_url);

        // STEP 3: Select the jdnd-c3 database to work with
        MongoDatabase database = mongoClient.getDatabase("jdnd-c3");

        //	Create a collection named members.
        try
        {
            database.createCollection(db_coll_name);
        } catch (MongoCommandException e)
        {
            System.err.println("Collection not created: " + e.getErrorMessage());
        }

        // Get Collection
        MongoCollection<Document> clubMembers = database.getCollection(db_coll_name);

        /**
         * Perform all the steps listed in the exercise
         * **/
        /** 1.Find all members whose last_name is Khan and print the results. There should be one document in the results. */
        findByLastName(clubMembers, "Khan");

        /** 2.Find all members whose last_name is Doe and gender is female and print the results. There should be one document in the results. */
        findByLastNameAndGender(clubMembers, "Doe", "female");

        /** 3.Find all members who are interested in golf and print the results. There should be 2 documents in the results.*/
        Map<String, List<String>> membersMap = new HashMap<>();
        membersMap.put("interests", Arrays.asList("golf", "crossfit"));
        membersMap.put("last_name", Arrays.asList("Khan"));

        findByKeyAndValue(clubMembers, membersMap);

        /** 4.Find all members who live in MN and print the results. There should be 2 documents in the results. */
        Map<String, List<String>> membersMap1 = new HashMap<>();
        membersMap1.put("address.state", Arrays.asList("MN"));

        findByKeyAndValue(clubMembers, membersMap1);

        /** 5.Count the number of members who are male and print the results. Result: 3 */
        Map<String, List<String>> membersMap2 = new HashMap<>();
        membersMap2.put("gender", Arrays.asList("male"));

        countByKeyAndValue(clubMembers, membersMap2);

        /** 6.Find the first member who is a female sorted by their first_name and print the results. Result: Member’s first_name is Jane. */
        Map<String, List<String>> membersMap3 = new HashMap<>();
        membersMap3.put("gender", Arrays.asList("female"));
        String sortCriteria = "last_name";
        int limit = 3; // 0 : no limit
        int order = 1; // -1 : descending, 1 : ascending
        findSortByKeyAndValue(clubMembers, membersMap3, sortCriteria, limit, order);

        // IMPORTANT: Make sure to close the MongoClient at the end so your program exits.
        mongoClient.close();
    }

    public static void findByLastName(MongoCollection<Document> collection,
                                      String lastName)
    {
        System.out.println("Find by last name Khan: ");
        Block<Document> printBlock = document -> System.out.println(document.toJson());

        collection.find(eq("last_name", "Khan"))
                .forEach(printBlock);
    }

    public static void findByLastNameAndGender(MongoCollection<Document> collection,
                                               String lastName,
                                               String gender)
    {
        System.out.println("\nFind by last name Doe and gender female: ");
        Block<Document> printBlock = document -> System.out.println(document.toJson());

        collection.find(and(eq("last_name", "Doe"),
                            eq("gender", "female")))
                .forEach(printBlock);
    }

    public static void findByKeyAndValue(MongoCollection<Document> collection,
                                         Map<String, List<String>> keyValueMap)
    {
        System.out.println("\nFind by Key and Value: ");

        /** One solution */
        keyValueMap.forEach((key, values) -> {
            values.forEach(value -> {
                System.out.println("Finding by : " + key + ": " + value);
                Block<Document> printBlock = document -> System.out.println(document.toJson());

                collection.find(eq(key, value))
                        .forEach(printBlock);
            });
        });
    }

    public static void countByKeyAndValue(MongoCollection<Document> collection,
                                          Map<String, List<String>> keyValueMap)
    {
        System.out.println("\nCount by Key and Value: ");

        /** One solution */
        keyValueMap.forEach((key, values) -> {
            values.forEach(value -> {
                System.out.println("Counting by : " + key + ": " + value);

                System.out.println("Result: " + collection.countDocuments(eq(key, value)));
            });
        });
    }

    public static void findSortByKeyAndValue(MongoCollection<Document> collection,
                                             Map<String, List<String>> keyValueMap,
                                             String sortCriteria,
                                             int limit,
                                             int order)
    {
        System.out.println("\nFind and sort by Key and Value: ");

        /** One solution */
        keyValueMap.forEach((key, values) -> {
            values.forEach(value -> {
                System.out.println("Finding by : " +
                                    " \n - " + key + "           : " + value +
                                    ",\n - sorting by       : " + sortCriteria +
                                    ",\n - sorting order    : " + ((order > 0) ? "ascending" : "descending") +
                                    ",\n - limit results to : " + ((limit > 0) ? limit : "no limit") );
                System.out.println();
                Block<Document> printBlock = document -> System.out.println(document.toJson());

                collection.find(eq(key, value))
                            .sort(new Document(sortCriteria, order))
                            .limit(limit)
                            .forEach(printBlock);
            });
        });
    }

}