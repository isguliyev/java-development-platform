package io.github.isguliyev.examples.connection;

import java.sql.DriverManager;
import java.sql.Driver;

import java.util.Enumeration;

public class JDBCDriverPrinter {
    public static void printAllDrivers() {
        Enumeration<Driver> enumeration = DriverManager.getDrivers();

        while (enumeration.hasMoreElements()) {
            Driver driver = enumeration.nextElement();

            System.out.printf(
                "%s %d.%d\n",
                driver.getClass().getName(),
                driver.getMajorVersion(),
                driver.getMinorVersion()
            );
        }
    }
}