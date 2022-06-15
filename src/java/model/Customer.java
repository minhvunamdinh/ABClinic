/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author zz0da
 */
public class Customer {

    private int id;
    private String fullname;
    private String phone;
    private String gender;
    private String job;
    private String dob;
    private String address;
    private String country;
    private String description;
    private String status;
    private String code;
    private String created_by;
    private String created_at;
    private String test_result;
    private String examination_card;
    private String time_return;
    private String list_test;
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public String getList_test() {
        return list_test;
    }

    public void setList_test(String list_test) {
        this.list_test = list_test;
    }
    
    public Customer() {
    }

    public Customer(int id, String fullname, String phone, String gender, String job, String dob, String address, String country, String description, String status, String code, String created_by, String created_at, String test_result, String examination_card, String time_return, String list_test) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
        this.address = address;
        this.country = country;
        this.description = description;
        this.status = status;
        this.code = code;
        this.created_by = created_by;
        this.created_at = created_at;
        this.test_result = test_result;
        this.examination_card = examination_card;
        this.time_return = time_return;
        this.list_test = list_test;
    }

    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTest_result() {
        return test_result;
    }

    public void setTest_result(String test_result) {
        this.test_result = test_result;
    }

    public String getExamination_card() {
        return examination_card;
    }

    public void setExamination_card(String examination_card) {
        this.examination_card = examination_card;
    }

    public String getTime_return() {
        return time_return;
    }

    public void setTime_return(String time_return) {
        this.time_return = time_return;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) throws ParseException {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
