/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceDAO;

import java.util.ArrayList;
import model.Test;
import model.TypeTest;

/**
 *
 * @author zz0da
 */
public interface ITestDAO {

    public ArrayList<TypeTest> list_type_test() throws Exception;

    public ArrayList<Test> list_test() throws Exception;

    public ArrayList<Test> get_list_test() throws Exception;

    public ArrayList<Test> list_test_by_customer(String id) throws Exception;

    public void change_status_test(String id, String status) throws Exception;
    
    public Test get_test_by_id(String id) throws Exception;
    
    public int update_test(Test test) throws Exception;
    
    public int insert_test(Test test) throws Exception;
    
}
