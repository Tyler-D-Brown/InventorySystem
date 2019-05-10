/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import Model.Inhouse;
import Model.Outsourced;
import Model.Part;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import static tylerbrowninventorysystem.TylerBrownInventorySystem.inv;
import static tylerbrowninventorysystem.TylerBrownInventorySystem.selected;
/**
 *
 * @author Tyler Brown
 */
public class AddModifyPartController implements Initializable {
    @FXML
    private RadioButton inHouse;

    @FXML
    private RadioButton outsourced;

    @FXML
    private Label machineIDcompanyName;

    @FXML
    private TextField partID;

    @FXML
    private TextField partName;

    @FXML
    private TextField partInv;

    @FXML
    private TextField partPrice;

    @FXML
    private TextField partMax;

    @FXML
    private TextField machineIDcompanyNamebox;

    @FXML
    private TextField partMin;
    
    @FXML
    void inHouseRadial(ActionEvent event){
        machineIDcompanyName.setText("Machine ID");
    }
    
    @FXML
    void outsourcedRadial(ActionEvent event){
        machineIDcompanyName.setText("Company Name");
    }
    
    @FXML
    void partCancelClick(ActionEvent event){
        //clear the selected variable
        selected=-1;
        
        //send confirmation of cancel
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
    void partSaveClick(ActionEvent event){
        //check to ensure input viability and display alerts
        boolean valid=true;
        int current=0,max=0,min=0;
        try{
            current=Integer.parseInt(partInv.getText());
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
            Float.parseFloat(partPrice.getText());
            valid=true;
        }
        catch(Exception e)
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("error in price");
            alert.setContentText("Invalid price entry");
            alert.showAndWait();
            valid=false;
            System.out.println(e);
            return;
        }
        try{
            max=Integer.parseInt(partMax.getText());
            valid=true;
        }
        catch(Exception e)
        {
            valid=false;
            System.out.println(e);
            return;
        }
        try{
            min=Integer.parseInt(partMin.getText());
            valid=true;
        }
        catch(Exception e)
        {
            valid=false;
            System.out.println(e);
            return;
        }
        if(partName.getText().isEmpty()){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("error in name");
                alert.setContentText("Invalid name entry");
                alert.showAndWait();
                valid=false;
            }
        if("Machine ID".equals(machineIDcompanyName.getText()))
        {
            try{
            Integer.parseInt(partID.getText());
            valid=true;
            }
            catch(Exception e)
            {
            valid=false;
            System.out.println(e);
            return;
            }
        }
        if(max>current&&min<current&&min<max)
        {
            valid=true;
        }else{
            valid=false; 
            if(min>max){
                Alert alert=new Alert(AlertType.INFORMATION);
                alert.setHeaderText("error in inventory");
                alert.setContentText("Max inventory is less than the minimum");
                alert.showAndWait();
            }else if(max<current){
                Alert alert=new Alert(AlertType.INFORMATION);
                alert.setHeaderText("error in inventory");
                alert.setContentText("Inventory exceeds maximum allowed.");
                alert.showAndWait();
            }else if(min>current){
                Alert alert=new Alert(AlertType.INFORMATION);
                alert.setHeaderText("error in inventory");
                alert.setContentText("Inventory is below minimum");
                alert.showAndWait();
            }
        }
        //if input is valid check to see if it's a new part or a modified part and save it
        if(valid==true){
            if(selected==-1){
                if(inHouse.selectedProperty().get()){
                    inv.addPart(new Inhouse(Integer.parseInt(partID.getText()),partName.getText(),Float.parseFloat(partPrice.getText()),Integer.parseInt(partInv.getText()),
                        Integer.parseInt(partMin.getText()),Integer.parseInt(partMax.getText()),Integer.parseInt(machineIDcompanyNamebox.getText())));
                }else
                {
                    inv.addPart(new Outsourced(Integer.parseInt(partID.getText()),partName.getText(),Float.parseFloat(partPrice.getText()),Integer.parseInt(partInv.getText()),
                        Integer.parseInt(partMin.getText()),Integer.parseInt(partMax.getText()),machineIDcompanyNamebox.getText()));
                }
            }else{
                if(inHouse.selectedProperty().get()){
                    inv.updatePart(selected, new Inhouse(Integer.parseInt(partID.getText()),partName.getText(),Float.parseFloat(partPrice.getText()),Integer.parseInt(partInv.getText()),
                        Integer.parseInt(partMin.getText()),Integer.parseInt(partMax.getText()),Integer.parseInt(machineIDcompanyNamebox.getText())));    
                }else{
                    inv.updatePart(selected, new Outsourced(Integer.parseInt(partID.getText()),partName.getText(),Float.parseFloat(partPrice.getText()),Integer.parseInt(partInv.getText()),
                        Integer.parseInt(partMin.getText()),Integer.parseInt(partMax.getText()),machineIDcompanyNamebox.getText()));
                }
            }
            selected=-1;
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.close();
        }
    }
    //partID.setDisable(true);
    public void initialize(URL url, ResourceBundle rb){
        //check to see if this is a new part or a modified part and handle accordingly
        if(selected==-1){
            inHouse.setSelected(true);
            partID.setText(Integer.toString(inv.findFirstFreePartID()));
            partID.setEditable(false);
        }else{
            Part p=inv.lookupPart(selected);
            partID.setText(Integer.toString(p.getPartID()));
            partID.setEditable(false);
            partName.setText(p.getName());
            partPrice.setText(Double.toString(p.getPrice()));
            partInv.setText(Integer.toString(p.getInStock()));
            partMin.setText(Integer.toString(p.getMin()));
            partMax.setText(Integer.toString(p.getMax()));
            if(null==p.getCompanyName()){
                machineIDcompanyNamebox.setText(Integer.toString(p.getMachineID()));
                inHouse.setSelected(true);
            }else{
                machineIDcompanyNamebox.setText(p.getCompanyName());
                outsourced.setSelected(true);
            }
        }
    }
}
