/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import Model.Inhouse;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tylerbrowninventorysystem.TylerBrownInventorySystem;
import static tylerbrowninventorysystem.TylerBrownInventorySystem.inv;
import static tylerbrowninventorysystem.TylerBrownInventorySystem.selected;

//fx:controller="MainScreenController"

/**
 *
 * @author Barid
 */
public class MainScreenController implements Initializable{
    
    //Declare observable lists for showing the data in a table.
    private ObservableList<Part> partList;
    private ObservableList<Product> productList; 
    

    @FXML
    private TextField partsSearchBox;

    @FXML
    private TableView<Part> partsDisplay;
    
    @FXML 
    private TableColumn<Part, Integer> partIDColumn;
    
    @FXML
    private TableColumn<Part, String> partNameColumn;
    
    @FXML 
    private TableColumn<Part, Integer> inventoryColumn;
    
    @FXML 
    private TableColumn<Part, Double> priceColumn;

    @FXML
    private TextField productsSearchBox;

    @FXML
    private TableView<Product> productsDisplay;
    
    @FXML
    private TableColumn<Product, Integer> productIDColumn;
    
    @FXML
    private TableColumn<Product, String> productNameColumn;
    
    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;
    
    @FXML
    private TableColumn<Product, Double> productPriceColumn;
    
    @FXML
    void addPartClick(ActionEvent event) throws IOException
    {
      //Load the add part screen
      try
       {
       Scene mainScreenScene=new Scene(FXMLLoader.load(TylerBrownInventorySystem.class.getResource("/ViewController/AddModifyPart8.fxml")));
       Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       //mainStage.initModality(Modality.NONE);
       Stage appStage = new Stage();
       //set the owner
       appStage.initOwner(mainStage);
       //set modality
       appStage.initModality(Modality.APPLICATION_MODAL);
       appStage.setScene(mainScreenScene);
       //show the new screen and wait
       appStage.showAndWait();
       //refresh the tables on close
       refreshTables();
       }catch(Exception e){
           System.out.println(e);
       }      
    };
    
    @FXML
    void addProductClick(ActionEvent event) throws IOException{
        //load the add products screen
      try
       {
       Scene mainScreenScene=new Scene(FXMLLoader.load(TylerBrownInventorySystem.class.getResource("/ViewController/AddModifyProduct8.fxml")));
       Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       Stage appStage = new Stage();
       //Set the stage's owner
       appStage.initOwner(mainStage);
       //set Modality
       appStage.initModality(Modality.APPLICATION_MODAL);
       appStage.setScene(mainScreenScene);
       //Show the scene and wait
       appStage.showAndWait();
       //refresh the tables
       refreshTables();
       }catch(Exception e){
           System.out.println(e);
       }
    }
    
    @FXML
    void deletePartClick(ActionEvent event){
        //display delete part confirmation and wait
        Alert alert=new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete.");
        alert.setContentText("Are you sure you want to delete "+partsDisplay.getSelectionModel().getSelectedItem().getName()+"?");
        Optional<ButtonType> result= alert.showAndWait();
        //if the user clicks OK delete the selection and refresh the tables
        if(result.get()==ButtonType.OK){
            inv.deletePart(partsDisplay.getSelectionModel().getSelectedItem());
            refreshTables();
        }
    }
    
    @FXML
    void deleteProductClick(ActionEvent event){
        //removed code for deletion of products
        /*inv.removeProduct(productsDisplay.getSelectionModel().getSelectedItem().getProductID());
        refreshTables();*/
        
        //Display an alert informing the user that products that have a part can't be deleted.
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Cannot Delete");
        alert.setContentText("Product has a part and cannot be deleted.");
        alert.showAndWait();
    }
    
    @FXML
    void exitClick(ActionEvent event){
        Alert alert=new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result= alert.showAndWait();
        //exit the program
        if(result.get()==ButtonType.OK){
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.close();
        }
    }
    
    @FXML
    void modifyPartClick(ActionEvent event){
        //get selected items part ID and save it to the global selected variable
        Part select=partsDisplay.getSelectionModel().getSelectedItem();
        if(select==null){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error");
            alert.setContentText("No part selected.");
            alert.showAndWait();
            return;
        }
        selected=select.getPartID();
        //open modify part
        try
       {
       Scene mainScreenScene=new Scene(FXMLLoader.load(TylerBrownInventorySystem.class.getResource("/ViewController/AddModifyPart8.fxml")));
       Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       Stage appStage = new Stage();
       //set stage owner
       appStage.initOwner(mainStage);
       //set Modality
       appStage.initModality(Modality.APPLICATION_MODAL);
       appStage.setScene(mainScreenScene);
       //show and wait
       appStage.showAndWait();
       //refresh the tables
       refreshTables();
       }catch(Exception e){
           System.out.println(e);
       }
    }
    
    @FXML
    void modifyProductClick(ActionEvent event){
        //get the selected Items product ID and save it to the global selected variable
        Product select=productsDisplay.getSelectionModel().getSelectedItem();
        if(select==null){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error");
            alert.setContentText("No product selected.");
            alert.showAndWait();
            return;
        }
        selected=select.getProductID();
        try
       {
        //open the modify products screen
       Scene mainScreenScene=new Scene(FXMLLoader.load(TylerBrownInventorySystem.class.getResource("/ViewController/AddModifyProduct8.fxml")));
       Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       Stage appStage = new Stage();
       //set the stage's owner
       appStage.initOwner(mainStage);
       //set modality
       appStage.initModality(Modality.APPLICATION_MODAL);
       appStage.setScene(mainScreenScene);
       //show and wait
       appStage.showAndWait();
       //refresh tables
       refreshTables();
       }catch(Exception e){
           System.out.println(e);
       }
    }    
    
    @FXML
    void searchPartClick(ActionEvent event){
        Part searched=inv.lookupPart(Integer.parseInt(partsSearchBox.getText()));
        partsDisplay.getSelectionModel().select(searched);
    }
    
    @FXML
    void searchProductClick(ActionEvent event){
        Product search=inv.lookupProduct(Integer.parseInt(productsSearchBox.getText()));
        productsDisplay.getSelectionModel().select(search);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //create starter part and product
        Part p=new Inhouse(1, "Part 7577", 72.50, 7, 1, 19, 7);
        Product pr=new Product(1, "Product7577", 89.6, 8, 1, 12, p);
        
        //add starter part and product to the inventory
        inv.addPart(p);
        inv.addProduct(pr);
        
        //populate the parts table and set column association
        partList=FXCollections.observableArrayList(inv.getAllParts());
        partsDisplay.setItems(partList);       
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        inventoryColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        
        //populate the products table and set column association
        productList=FXCollections.observableArrayList(inv.getAllProducts());
        productsDisplay.setItems(productList);
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
    }
    
    public void refreshTables(){
        //refresh parts display
        partsDisplay.getItems().clear();
        partList=FXCollections.observableArrayList(inv.getAllParts());
        partsDisplay.setItems(partList);
        //refresh products display
        productsDisplay.getItems().clear();
        productList=FXCollections.observableArrayList(inv.getAllProducts());
        productsDisplay.setItems(productList);
    }
}
