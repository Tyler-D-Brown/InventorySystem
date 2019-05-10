/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Barid
 */
public class Inventory {
    private ArrayList<Product> Products=new ArrayList<>();
    private ArrayList<Part> allParts=new ArrayList<>();
    
    public void addProduct(Product add){
        Products.add(add);
    }
    
    public boolean removeProduct(int ID){
        //find a specific part by ID and remove it.
        for(int i=0; i<Products.size(); i++)
        {
            if(ID==Products.get(i).getProductID())
            {
                Products.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public Product lookupProduct(int ID){
        //lookup and return a product by ID
        for(int i=0; i<Products.size(); i++)
        {
            if(ID==Products.get(i).getProductID())
                return Products.get(i);
        }
        return null;
    }
    
    public void updateProduct(Product p){
        //find the speicified product and update it.
        for(int i=0; i<Products.size(); i++)
        {
            if(p.getProductID()==Products.get(i).getProductID())
            {
                Products.set(i, p);
            }
        }
    }
    
    public void addPart(Part add){
        allParts.add(add);
    }
    
    public boolean deletePart(Part p){
        //find a specified part and delete it.
        for(int i=0; i<allParts.size(); i++)
        { 
            if(p.getPartID()==allParts.get(i).getPartID())
            {
                allParts.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public Part lookupPart(int ID){
        //find a specified part by ID and return it
        for(int i=0; i<allParts.size(); i++)
        {
            if(ID==allParts.get(i).getPartID())
                return allParts.get(i);
        }
        return null;
    }
    
    public void updatePart(int ID, Part p){
        //find a specified part and replace it with the supplied part.
        for(int i=0; i<allParts.size(); i++)
        {
            if(p.getPartID()==allParts.get(i).getPartID())
            {
                allParts.set(i, p);
            }
        }
    }
    
    public ArrayList<Part> getAllParts(){
        //return the array list of parts
        return allParts;
    }
    
    public ArrayList<Product> getAllProducts(){
        //return the array list of products
        return Products;
    }
    
    public int findFirstFreeProductID(){
        //find first unused product ID
        boolean cont=true;
        int winner=1;
        do{
            for(int i=0; i<Products.size(); i++)
            {
                if(Products.get(i).getProductID()==winner)
                {
                    cont=true;
                    winner++;
                    break;
                }else 
                {
                    cont=false;
                    
                }
            }    
        }while(cont==true);
        return winner;
    }
    
    public int findFirstFreePartID(){
        //find first unused part ID
        boolean cont=true;
        int winner=1;
        do{
            for(int i=0; i<allParts.size(); i++)
            {
                if(allParts.get(i).getPartID()==winner)
                {
                    cont=true;
                    winner++;
                }else 
                {
                    cont=false;
                }
            }    
        }while(cont==true);
        return winner;
    }
}
