/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Barid
 */

public class Product{
    //members of the classs
    private ArrayList<Part> associatedParts=new ArrayList<Part>();
    private int productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;

    //methods
    public Product(int ID, String productName, double p, int stock, int mi, int ma, Part part){
        this.setProductID(ID);
        this.setName(productName);
        this.setPrice(p);
        this.setInStock(stock);
        this.setMin(mi);
        this.setMax(ma);
        associatedParts.add(part);
    }
    
    public Product(){
        productID=0;
        name=null;
        price=0;
        inStock=0;
        min=0;
        max=0;
    }
    
    public void setName(String n)
    {
        name=n;
    }
    public String getName()
    {
        return name;
    }
    public void setPrice(double p)
    {
        price=p;
    }
    public double getPrice()
    {
        return price;
    }
    public void setInStock(int stock)
    {
        inStock=stock;
    }
    public int getInStock()
    {
        return inStock;
    }
    public void setMin(int m)
    {
        min=m;
    }
    public int getMin()
    {
        return min;
    }
    public void setMax(int m)
    {
        max=m;
    }
    public int getMax()
    {
        return max;
    }
    public void setProductID(int ID)
    {
        productID=ID;
    }
    public int getProductID()
    {
        return productID;
    }
    public void addAssociatedPart(Part add)
    {
        associatedParts.add(add);
    }
    
    
    public boolean removeAssociatedPart(int ID)
    {
       for(int i=0; i<associatedParts.size(); i++)
        {
            if(ID==associatedParts.get(i).getPartID())
            {
                associatedParts.remove(i);
                return true;
            }
        }
        return false;
    }
    
    
    public Part lookupAssociatedPart(int ID)
    {
        for(int i=0; i<associatedParts.size(); i++)
        {
            if(ID==associatedParts.get(i).getPartID())
                return associatedParts.get(i);
        }
        return null;
    }
    
    public ArrayList<Part> getAllParts(){
        return associatedParts;
    }
}