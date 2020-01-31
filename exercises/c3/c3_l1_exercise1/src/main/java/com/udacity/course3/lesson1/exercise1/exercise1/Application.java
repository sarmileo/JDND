package com.udacity.course3.lesson1.exercise1.exercise1;


import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Application {

    enum Member
    {
        notUsed, name, lastName, age, gender, balance;

        public static int set(String columnName)
        {
            int index = 0;
            switch (columnName)
            {
                case "name":
                    index = 1;
                    break;
                case "lastName":
                    index = 2;
                    break;
                case "age":
                    index = 3;
                    break;
                case "gender":
                    index = 4;
                    break;
                case "balance":
                    index = 5;
                    break;
                default:
                    index = 0;
            }

            return index;
        }
    }

    public static void main(String[] args) throws SQLException {

        // STEP 1: Create the JDBC URL for JDND-C3 database
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(
                "jdbc:mysql://localhost/JDND_C3"
        );
        dataSource.setUser("root");
        dataSource.setPassword("pass");

        // STEP 2: Setup and Run Flyway migration that creates the member table using its Java API
        // https://flywaydb.org/getstarted/firststeps/api#integrating-flyway
        // Note the above link talks about connecting to H2 database, for this exercise, MySQL is used. Adapt the code accordingly.
        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();

        // Start the migration
        flyway.baseline();
        flyway.migrate();


        // STEP 3: Obtain a connection to the JDND-C3 database
        try {
            try (Connection connection = DataSourceUtils.getConnection(dataSource)) {
                System.out.println("Connected to " + connection.getMetaData().getDatabaseProductName());


                // STEP 4: Use Statement to INSERT 2 records into the member table
                // NOTE: The member table is created using Flyway by placing the migration file in src/main/resources/db/migration
                String insert = "insert into JDND_C3.member"
                        + "(first_name, last_name, age, gender, balance) VALUES"
                        + "(?, ?, ?, ?, ?)";

                String select = "select * from JDND_C3.member";

                PreparedStatement ps = connection.prepareStatement(insert);

                ps.setString(Member.set("name"), "Leo");
                ps.setString(Member.set("lastName"), "Sarmi");
                ps.setInt(Member.set("age"), 35);
                ps.setString(Member.set("gender"), "M");
                ps.setFloat(Member.set("balance"), (float) 10.0);

                ps.executeUpdate();

                ps.setString(Member.set("name"), "Jonny");
                ps.setString(Member.set("lastName"), "B");
                ps.setInt(Member.set("age"), 34);
                ps.setString(Member.set("gender"), "M");
                ps.setFloat(Member.set("balance"), (float) 20.0);

                ps.executeUpdate();

                // STEP 5: Read ALL the rows from the member table and print them here
                ps = connection.prepareStatement(select);

                ResultSet result = ps.executeQuery();

                result.last();
                int numberOfRows = result.getRow();
                System.out.println("JNDN_C3 has " + numberOfRows + " members");
                System.out.println("results columns: " + result.getMetaData().getColumnCount());
                result.beforeFirst();

                while(result.next()) {
                    String     name        = result.getString     (result.findColumn("first_name"));
                    int        age         = result.getInt        (result.findColumn("age"));
                    BigDecimal balance = result.getBigDecimal (result.findColumn("balance"));

                    System.out.println("name:" + name + " age: " + age + " balance: " + balance);
                }

                // STEP 6: verify that all inserted rows have been printed

            }

        } catch (SQLException ex) {
            // handle errors
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

}