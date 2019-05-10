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
public class Outsourced extends Part{
    private String companyName;
    
    public Outsourced(int id, String n, double p, int stock, int mi, int ma, String origin){
        this.setPartID(id);
        this.setName(n);
        this.setPrice(p);
        this.setInStock(stock);
        this.setMin(mi);
        this.setMax(ma);
        this.setCompanyName(origin);
    }
    @Override
    public void setCompanyName(String name){
        companyName=name;
    }
    @Override
    public String getCompanyName(){
        return companyName;
    }
    @Override
    public void setMachineID(int i){
        
    }
    public int getMachineID(){
        return -1;
    }
}
