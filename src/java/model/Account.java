/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author admin
 */
public class Account {
    private int id;
    private String username;
    private String password;
    private int role_id;
    private int is_active;
    private String fullname;
    private String address;
    private String phone;
    private String email;
    private String dob;
    private int gender;

    public Account() {
    }
    
    public Account(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account(int id, int is_active) {
        this.id = id;
        this.is_active = is_active;
    }
    

    public Account(int id, String fullname, String address, String dob, int gender) {
        this.id = id;
        this.fullname = fullname;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
    }
    
    

    public Account(int id, String username, String password, int role_id, int is_active, String fullname, String address, String phone, String email, String dob, int gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
        this.is_active = is_active;
        this.fullname = fullname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
    
    
    public Account(String username, String password, int role_id, int is_active) {
        this.username = username;
        this.password = password;
        this.role_id = role_id;
        this.is_active = is_active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    
    
}
