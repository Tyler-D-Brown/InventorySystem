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
public class Inhouse extends Part{
    private int machineID;
    public Inhouse(int id, String n, double p, int stock, int mi, int ma, int maid){
        this.setPartID(id);
        this.setName(n);
        this.setPrice(p);
        this.setInStock(stock);
        this.setMin(mi);
        this.setMax(ma);
        this.setMachineID(maid);
    }
    
    @Override
    public void setMachineID(int ID){
        machineID=ID;
    }
    @Override
    public int getMachineID(){
        return machineID;
    }
    @Override
    public void setCompanyName(String s){
        
    }
    @Override
    public String getCompanyName(){
        return null;
    }
    
}
