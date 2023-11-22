package com.example.exjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import java.sql.SQLException;
import java.util.List;

public class TableController {

    private int idAutoIncrement;
    @FXML private TextField txtProductName;
    @FXML private TextField txtPrice;
    @FXML private TextField txtQuantity;

    @FXML private TableView<Product> table;
    @FXML private TableColumn<Product, Integer> cId;
    @FXML private TableColumn<Product, String> cName;
    @FXML private TableColumn<Product, Double> cPrice;
    @FXML private TableColumn<Product, Integer> cQuantity;

    private ObservableList<Product> observableList;
    private ProductRepository repository;


    @FXML
    public void initialize(){
        linkTableColumnstoAtributes();
        bindDataSourceToTable();
        loadDataIntoTable();
    }


    private void linkTableColumnstoAtributes() {
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        cQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }
    private void bindDataSourceToTable() {
        observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
    }
    private void loadDataIntoTable() {
        repository = new FakeRepository();
        final List<Product> products = repository.findAll();
        observableList.clear();
        observableList.addAll(products);
    }


    //O botão “Save” deve salvar um novo Product se ele não
    //estiver na tabela ou editar caso esteja.
    public void save(ActionEvent actionEvent){

//        PreparedStatement stmt = null;
//        Connection conn = null;
//
//        try {
//            conn = DriverManager.getConnection("jdbc:sqlite:company.db");
//            String sql = "INSERT INTO product (code, name, price, quantity) values (?, ?, ?, ?)";
//            stmt = conn.prepareStatement(sql);
//
//            stmt.setInt(idAutoIncrement, p.getId());
//            stmt.setString(1, p.getName());
//            stmt.setDouble(2, p.getPrice());
//            stmt.setInt(3, p.getQuantity());
//
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            if (stmt != null){
//                try {stmt.close();}
//                catch (SQLException e) { e.printStackTrace();}
//            }
//            if (conn != null){
//                try {conn.close();}
//                catch (SQLException e) { e.printStackTrace();}
//            }
//        }

        //pega texto que foi inserido no campo de insercao
        final String productName = txtProductName.getText();
        final double price = Double.parseDouble(txtPrice.getText());
        final int quantity = Integer.parseInt(txtQuantity.getText());

        Product product = new Product(idAutoIncrement,productName,price,quantity);

        observableList.add(product);
        idAutoIncrement++;
    }
    public void delete(ActionEvent actionEvent){
        Product product = table.getSelectionModel().getSelectedItem();
        observableList.remove(product);

    }
    public void clear(ActionEvent actionEvent){
        observableList.clear();
    }
}
