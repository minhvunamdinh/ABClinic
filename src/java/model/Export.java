/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Alienware
 */
public class Export {
    int id;
    String name;
    String unit;
    int quantity;
    float price;
    String healthinpay;
    String exemptions;
    String result;

    public Export() {
    }

    public Export(int id, String name, String unit, int quantity, float price, String healthinpay, String exemptions, String result) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.healthinpay = healthinpay;
        this.exemptions = exemptions;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getHealthinpay() {
        return healthinpay;
    }

    public void setHealthinpay(String healthinpay) {
        this.healthinpay = healthinpay;
    }

    public String getExemptions() {
        return exemptions;
    }

    public void setExemptions(String exemptions) {
        this.exemptions = exemptions;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
}
