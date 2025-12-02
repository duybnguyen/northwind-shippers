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

    public void insert(Shipper shipper) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Shippers (CompanyName, Phone) VALUES (?, ?)");) {

            preparedStatement.setString(1, shipper.getCompanyName());
            preparedStatement.setString(2, shipper.getPhoneNumber());
            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows Inserted: " + rows);
            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    System.out.println("A new key was added: " + keys.getInt(1));
                }
            }
        } catch (Exception exception) {
            System.err.println("ERROR inserting shipper: ");
            exception.printStackTrace();
        }

    }

    public void updateRecord(int shipperId, String newCompanyName, String newPhone) {
        String sql = "UPDATE Shippers SET CompanyName = ?, Phone = ? WHERE ShipperID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, newCompanyName);
            preparedStatement.setString(2, newPhone);
            preparedStatement.setInt(3, shipperId);

            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows Updated: " + rows);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void deleteRecord(int shipperId) {
        String sql = "DELETE FROM Shippers WHERE ShipperID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, shipperId);

            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows Deleted: " + rows);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
