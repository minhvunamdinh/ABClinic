/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceDAO;

import java.util.ArrayList;
import model.Customer;

/**
 *
 * @author zz0da
 */
public interface ICustomerDAO {
    public Customer get_customer_detail(String id) throws Exception;
    public ArrayList<Customer> list_customer(String id,String status,int currentPage, int recordsPerpage) throws Exception;
     public int getNumberOfRows(String id, String status) throws Exception;
}
