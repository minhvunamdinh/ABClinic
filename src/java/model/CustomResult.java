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
public class CustomResult {
    String code;
    String created_by;
    String created_at;
    String test_result;
    String examination_card;
    String time_return;
    String cus_id;
    String list_test;
    String note;

    public CustomResult() {
    }

    public CustomResult(String code, String created_by, String created_at, String test_result, String examination_card, String time_return, String cus_id, String list_test, String note) {
        this.code = code;
        this.created_by = created_by;
        this.created_at = created_at;
        this.test_result = test_result;
        this.examination_card = examination_card;
        this.time_return = time_return;
        this.cus_id = cus_id;
        this.list_test = list_test;
        this.note = note;
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

    public String getCus_id() {
        return cus_id;
    }

    public void setCus_id(String cus_id) {
        this.cus_id = cus_id;
    }

    public String getList_test() {
        return list_test;
    }

    public void setList_test(String list_test) {
        this.list_test = list_test;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
}
