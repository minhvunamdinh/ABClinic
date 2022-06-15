/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author zz0da
 */
public class Test {

    private int id;
    private int type_id;
    private String name;
    private int cost_price;
    private int sell_price;
    private boolean is_active;
    private String form;

    public Test() {
    }

    public Test(int id, int type_id, String name, int cost_price, int sell_price, boolean is_active, String form) {
        this.id = id;
        this.type_id = type_id;
        this.name = name;
        this.cost_price = cost_price;
        this.sell_price = sell_price;
        this.is_active = is_active;
        this.form = form;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost_price() {
        return cost_price;
    }

    public void setCost_price(int cost_price) {
        this.cost_price = cost_price;
    }

    public int getSell_price() {
        return sell_price;
    }

    public void setSell_price(int sell_price) {
        this.sell_price = sell_price;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

}
