package com.pluralsight.db;
import com.pluralsight.model.Shipper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private DataSource dataSource;

    public DataManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Shipper> getAllShippers() {
        List<Shipper> shippers = new ArrayList<>();
        String shippersQuery = "SELECT ShipperID, CompanyName, Phone FROM Shippers";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(shippersQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int shipperID = resultSet.getInt(1);
                String companyName = resultSet.getString(2);
                String phone = resultSet.getString(3);

                shippers.add(new Shipper(companyName, phone, shipperID));
            }

        } catch (Exception exception) {
            System.err.println("An error has occurred!");
            exception.printStackTrace();
        }
        return shippers;
    }

}
