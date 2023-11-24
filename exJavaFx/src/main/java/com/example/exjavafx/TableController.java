package com.example.exjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.List;

public class TableController {


    @FXML private TextField txtProductName;
    @FXML private TextField txtPrice;
    @FXML private TextField txtQuantity;

    @FXML private TableView<Product> table;
    //@FXML private TableColumn<Product, Integer> cId;
    @FXML private TableColumn<Product, String> cName;
    @FXML private TableColumn<Product, Double> cPrice;
    @FXML private TableColumn<Product, Integer> cQuantity;

    private ObservableList<Product> observableList;
    private RepositoryDAO repository;


    @FXML
    public void initialize(){
        linkTableColumnstoAtributes();
        bindDataSourceToTable();
        loadDataIntoTable();
    }

    private void linkTableColumnstoAtributes() {
        //cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        cQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }
    private void bindDataSourceToTable() {
        observableList = FXCollections.observableArrayList();
        table.setItems(observableList);
    }
    private void loadDataIntoTable() {
        repository = new ProductDaoImplementation();
        final List products = repository.read();
        observableList.clear();
        observableList.addAll(products);
    }

    //O botão “Save” deve salvar um novo Product se ele não
    //estiver na tabela ou editar caso esteja.
    public void save(ActionEvent actionEvent){

        //pega texto que foi inserido no campo de insercao
        final String productName = txtProductName.getText();
        final double price = Double.parseDouble(txtPrice.getText());
        final int quantity = Integer.parseInt(txtQuantity.getText());

        Product product = new Product(productName,price,quantity);

        if (!repository.findOne(productName)){
            System.out.println(repository.save(product));
            observableList.add(product);
        }

    }
    public void delete(ActionEvent actionEvent){
        Product product = table.getSelectionModel().getSelectedItem();
        repository.delete(product);
        observableList.remove(product);

    }
    public void clear(ActionEvent actionEvent){
        repository.clear();
        observableList.clear();
    }

    public RepositoryDAO getRepository() {
        return repository;
    }
}
