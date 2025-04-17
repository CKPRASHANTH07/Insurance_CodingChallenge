package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getPropertyString(String fileName) {
        Properties props = new Properties();
        String filePath = "resources/" + fileName;
        try (FileInputStream fis = new FileInputStream(filePath)) {
            props.load(fis);
            String hostname = props.getProperty("hostname");
            String port = props.getProperty("port");
            String dbname = props.getProperty("dbname");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            if (hostname == null || port == null || dbname == null || username == null || password == null) {
                throw new IOException("Missing required properties in " + fileName);
            }

            return "jdbc:mysql://" + hostname + ":" + port + "/" + dbname + "?user=" + username + "&password=" + password;
        } catch (IOException e) {
            System.err.println("Error reading properties file: " + filePath);
            e.printStackTrace();
            return null;
        }
    }
}