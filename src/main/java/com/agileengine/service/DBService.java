package com.agileengine.service;

import com.agileengine.entity.ImageDataEntity;

import java.sql.*;

public class DBService {

    private static final String DB_URL = "jdbc:h2:/c:/JavaPrj/db/ImageDB";
    private final static String USER = "user";
    private final static String PASSWORD = "pass";
    private static Connection connection = null;

    private static DBService dbService;
    private DBService(){}

    public static DBService getInstance() {
        if (dbService == null) {
            synchronized (DBService.class) {
                if (dbService == null) {
                    dbService = new DBService();
                }
            }
        }
        return dbService;
    }

    private Connection getDBConnection() {

        try {

            if ((connection == null) || (connection.isClosed())) {

                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                connection.setTransactionIsolation(4);
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            System.out.println("Error creating connection");
            e.printStackTrace();
        }
        return connection;
    }

    public void createTable() throws SQLException {

        try {

            Statement statement = getDBConnection().createStatement();

            statement.execute("drop table images if exists ");
            statement.execute("create table images(image_id int primary key, cropped_picture VARCHAR(100))");

            connection.commit();
            System.out.println("Table creating sucsessfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
        }
    }

    public void addImage(ImageDataEntity image) throws SQLException {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO images (image_id, cropped_picture) VALUES (?, ?)");
            preparedStatement.setInt(1, image.getImageId());
            preparedStatement.setString(2, image.getCroppedImage());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();

        } finally {

            if (connection != null) {
            }
        }
    }

    public ImageDataEntity getImageById(int id) throws SQLException {

        ImageDataEntity image = new ImageDataEntity();

        try {

            Statement statement = getDBConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM images where imageId = " + id);

            while (rs.next()) {
                image.setCroppedImage(rs.getString("cropped_picture"));
                image.setImageId(rs.getInt("image_id"));
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
        }
        return image;
    }
}
