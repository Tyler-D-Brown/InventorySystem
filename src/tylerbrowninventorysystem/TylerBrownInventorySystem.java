/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tylerbrowninventorysystem;

import Model.Inventory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Barid
 */
public class TylerBrownInventorySystem extends Application{
   
    //global static variables to store the Inventory object and store the selected item for modifying parts.
    public static Inventory inv = new Inventory();
    public static int selected=-1;
    
   @Override
   public void start(Stage PrimaryStage) throws Exception{
       
       //load the mainscreen
       Scene scene=null;
       try
       {
       scene=new Scene(FXMLLoader.load(TylerBrownInventorySystem.class.getResource("/ViewController/MainScreen8.fxml")));
       }catch(Exception e){
           System.out.println(e);
       }
       //show the main screen
       PrimaryStage.setScene(scene);
       PrimaryStage.show();
   }
   
   
}
