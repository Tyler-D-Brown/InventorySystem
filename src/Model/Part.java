/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
/**
 *
 * @author Barid
 */
public abstract class Part{
    //private member declarations
    protected int partID;
    protected String name;
    protected double price;
    protected int inStock;
    protected int min;
    protected int max;
    //public methods
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
    public void setPartID(int ID)
    {
        partID=ID;
    }
    public int getPartID()
    {
        return partID;
    }
    abstract public int getMachineID();
    abstract public void setMachineID(int i);
    abstract public String getCompanyName();
    abstract public void setCompanyName(String s);
}
