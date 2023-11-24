package com.example.exjavafx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImplementation implements RepositoryDAO<String,Product> {

    @Override
    public List<Product> read() {
        String sql = "SELECT * FROM product";
        List<Product> products = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:products.sqlite");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

             ResultSet rs = stmt.executeQuery(); // executa o comando SQL e armazena no ResultSet
            while (rs.next()){
                Product p = new Product(rs.getString("name"),
                        rs.getInt("quantity"), rs.getInt("price"));

                products.add(p);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean save(Product p) {
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:products.sqlite");
            String sql = "INSERT INTO product (name, price, quantity) values (?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            //stmt.setInt(1, getId());
            stmt.setString(1, p.getName());
            stmt.setDouble(2, p.getPrice());
            stmt.setInt(3, p.getQuantity());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            if (stmt != null){
                try {stmt.close();}
                catch (SQLException e) { e.printStackTrace();}
            }
            if (conn != null){
                try {conn.close();}
                catch (SQLException e) { e.printStackTrace();}
            }
        }
        return true;
    }

    @Override
    public boolean findOne(String key) {

        String sql = "SELECT name FROM product";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:products.sqlite");
             PreparedStatement stmt = conn.prepareStatement(sql);){

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String productName = rs.getString("name");
                if (productName.equals(key)) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean update(Product object, String key) {
        return false;
    }


    @Override
    public boolean delete(Product object) {

        String sql = "DELETE FROM product WHERE name = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:products.sqlite");
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1,object.getName());

            int rows = stmt.executeUpdate();
            if (rows > 0) return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean clear(){

        String sql = "DELETE FROM product";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:products.sqlite");
             PreparedStatement stmt = conn.prepareStatement(sql)){

            int rows = stmt.executeUpdate();
            if (rows > 0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }
}
