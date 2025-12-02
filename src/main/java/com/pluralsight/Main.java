package com.pluralsight;

import com.pluralsight.db.DataManager;
import com.pluralsight.model.Shipper;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("User and Password are needed");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        DataManager dataManager = new DataManager(dataSource);

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Add New Shipper ===");
        System.out.print("Company Name: ");
        String companyName = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        Shipper newShipper = new Shipper(companyName, phone);
        dataManager.insert(newShipper);

        System.out.println("Inserted new shipper with ID: " + newShipper.getShipperID());
        System.out.println();

        System.out.println("=== All Shippers ===");

        System.out.println();

        System.out.println("=== Update Shipper Phone ===");
        System.out.print("Enter ShipperID to update: ");
        int updateId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter new Phone Number: ");
        String newPhone = scanner.nextLine();

        dataManager.updateRecord(updateId, companyName, newPhone);
        System.out.println("Phone updated!\n");

        System.out.println("=== All Shippers After Update ===");
        printShippers(dataManager.getAllShippers());
        System.out.println();

        System.out.println("=== Delete a Shipper (NOT IDs 1–3) ===");
        System.out.print("Enter ShipperID to delete: ");
        int deleteId = Integer.parseInt(scanner.nextLine());

        dataManager.deleteRecord(deleteId);
        System.out.println("Shipper deleted (if existed).\n");

        System.out.println("=== All Shippers After Delete ===");
        printShippers(dataManager.getAllShippers());

        scanner.close();
    }
    private static void printShippers(List<Shipper> shippers) {
        if (shippers.isEmpty()) {
            System.out.println("(No shippers found)");
            return;
        }

        for (Shipper shipper : shippers) {
            System.out.println(
                    shipper.getShipperID() + " | " +
                            shipper.getCompanyName() + " | " +
                            shipper.getPhoneNumber()
            );
        }
    }
}

