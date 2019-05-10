/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import Model.Part;
import Model.Product;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static tylerbrowninventorysystem.TylerBrownInventorySystem.inv;
import static tylerbrowninventorysystem.TylerBrownInventorySystem.selected;

/**
 *
 * @author Barid
 */
public class AddModifyProductsController implements Initializable{
    
    //create obserable lists for the tables and a placeholder product variable
    private ObservableList<Part> addPartList;
    private ObservableList<Part> partList;
    private Product prod=new Product();
    
    @FXML
    private TextField productID;

    @FXML
    private TextField productName;

    @FXML
    private TextField productInventory;

    @FXML
    private TextField productCost;

    @FXML
    private TextField productMax;

    @FXML
    private TextField productMin;

    @FXML
    private TableView<Part> productAddDisplay;
    
    @FXML 
    private TableColumn<Part, Integer> addPartIDColumn;
    
    @FXML
    private TableColumn<Part, String> addPartNameColumn;
    
    @FXML 
    private TableColumn<Part, Integer> addInventoryColumn;
    
    @FXML 
    private TableColumn<Part, Double> addPriceColumn;

    @FXML
    private TableView<Part> productPartsDisplay;
    
    @FXML 
    private TableColumn<Part, Integer> partIDColumn;
    
    @FXML
    private TableColumn<Part, String> partNameColumn;
    
    @FXML 
    private TableColumn<Part, Integer> inventoryColumn;
    
    @FXML 
    private TableColumn<Part, Double> priceColumn;

    @FXML
    private TextField productSearchBox;

    @FXML
    void addPartClick(ActionEvent event) {
        //add the selected part to the table and refresh the table
        System.out.println(productAddDisplay.getSelectionModel().getSelectedItem().getPartID());
        prod.addAssociatedPart(productAddDisplay.getSelectionModel().getSelectedItem());
        refreshTables();
    }

    @FXML
    void cancelClick(ActionEvent event) {
       //confirm cancel and, if confirmed, exit
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> result= alert.showAndWait();
        if(result.get()==ButtonType.OK){
            selected=-1;
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.close();
        }
    }

    @FXML
    void deletePartClick(ActionEvent event) {
        //confirm part deletion
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete.");
        alert.setContentText("Are you sure you want to delete "+productPartsDisplay.getSelectionModel().getSelectedItem().getName()+"?");
        Optional<ButtonType> result= alert.showAndWait();
        if(result.get()==ButtonType.OK){
            System.out.println(productPartsDisplay.getSelectionModel().getSelectedItem().getPartID());
            prod.removeAssociatedPart(productPartsDisplay.getSelectionModel().getSelectedItem().getPartID());
            refreshTables();
        }
    }

    @FXML
    void saveClick(ActionEvent event) {
        //validate user input
        int current=0,max=0,min=0;
        float prodPrice=0, partsPrice=0;
        boolean valid=true;
            try{
                current=Integer.parseInt(productInventory.getText());
                valid=true;
            }
            catch(Exception e)
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("error in inventory");
                alert.setContentText("Invalid inventory entry");
                alert.showAndWait();
                valid=false;
                System.out.println(e);
                return;
            }
            try{
                prodPrice=Float.parseFloat(productCost.getText());
                valid=true;
            }
            catch(Exception e)
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("error in cost");
                alert.setContentText("Invalid cost entry");
                alert.showAndWait();
                valid=false;
                System.out.println(e);
                return;
            }
            try{
                max=Integer.parseInt(productMax.getText());
                valid=true;
            }
            catch(Exception e)
            {
                valid=false;
                System.out.println(e);
                return;
            }
            try{
                min=Integer.parseInt(productMin.getText());
                valid=true;
            }
            catch(Exception e)
            {
                valid=false;
                System.out.println(e);
                return;
            }
            partList=FXCollections.observableArrayList(productPartsDisplay.getItems());
            for(int i=0;i<partList.size();i++){
                partsPrice+=(float) partList.get(i).getPrice();
            }
            if(productName.getText().isEmpty()){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("error in name");
                alert.setContentText("Invalid name entry");
                alert.showAndWait();
                valid=false;
            }
        if(max>current&&min<current&&min<max)
        {
            valid=true;
        }else{
            valid=false; 
            if(min>max){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("error in inventory");
                alert.setContentText("Max inventory is less than the minimum");
                alert.showAndWait();
            }else if(max<current){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("error in inventory");
                alert.setContentText("Inventory exceeds maximum allowed.");
                alert.showAndWait();
            }else if(min>current){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("error in inventory");
                alert.setContentText("Inventory is below minimum");
                alert.showAndWait();
            }
        }
        if(partList.isEmpty())
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("error in parts");
            alert.setContentText("Product does not have a part.");
            alert.showAndWait();
            valid=false;
        }
        if(prodPrice<partsPrice){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("error in price");
            alert.setContentText("Product costs more to make then set price.");
            alert.showAndWait();
            valid=false;
        }
        //if input is valid check to see if this is a new product or an existing product and save
        if(valid)
        {
            if(selected==-1)
            {
                //Product(int ID, String productName, double p, int stock, int mi, int ma, Part part)
                prod.setInStock(Integer.parseInt(productInventory.getText()));
                prod.setMax(Integer.parseInt(productMax.getText()));
                prod.setMin(Integer.parseInt(productMin.getText()));
                prod.setName(productName.getText());
                prod.setPrice(Float.parseFloat(productCost.getText()));
                prod.setProductID(Integer.parseInt(productID.getText()));
                inv.addProduct(prod);
                System.out.println("product added");
            }else
            {
                //Product(int ID, String productName, double p, int stock, int mi, int ma, Part part)
                prod.setInStock(Integer.parseInt(productInventory.getText()));
                prod.setMax(Integer.parseInt(productMax.getText()));
                prod.setMin(Integer.parseInt(productMin.getText()));
                prod.setName(productName.getText());
                prod.setPrice(Float.parseFloat(productCost.getText()));
                prod.setProductID(Integer.parseInt(productID.getText()));
                inv.updateProduct(prod);
                System.out.println("Product updated");
            }
            selected=-1;
        
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.close();
        }
        
    }

    @FXML
    void searchClick(ActionEvent event) {
        Part searched=inv.lookupPart(Integer.parseInt(productSearchBox.getText()));
        productAddDisplay.getSelectionModel().select(searched);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //populate the tables
        addPartList=FXCollections.observableArrayList(inv.getAllParts());
        productAddDisplay.setItems(addPartList);       
        addPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        addPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        addInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        System.out.println("parts table populated");    
        
        
        
        //check to see if this is an existing part or a new part and handle accordingly.
        if(selected==-1)
        {
            //prod=null;
            productID.setText(Integer.toString(inv.findFirstFreeProductID()));
            productID.setEditable(false);
            productInventory.setText(Integer.toString(0));
            productCost.setText(Double.toString(0));
        }else
        {
            prod=inv.lookupProduct(selected);
            productID.setText(Integer.toString(prod.getProductID()));
            productID.setEditable(false);
            productName.setText(prod.getName());
            productInventory.setText(Integer.toString(prod.getInStock()));
            productCost.setText(Double.toString(prod.getPrice()));
            productMax.setText(Integer.toString(prod.getMax()));
            productMin.setText(Integer.toString(prod.getMin()));
        }
        if(prod==null){
            partList=null;
        }else{
            partList=FXCollections.observableArrayList(prod.getAllParts());
        }
        productPartsDisplay.setItems(partList);       
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        inventoryColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        System.out.println("parts table populated");
        
        
    }
    
    void refreshTables(){
        addPartList=FXCollections.observableArrayList(inv.getAllParts());
        productAddDisplay.setItems(addPartList);
        
        partList=FXCollections.observableArrayList(prod.getAllParts());
        productPartsDisplay.setItems(partList);
    }
}
