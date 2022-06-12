/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.DBConnection;
import interfaceDAO.ICustomerDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Customer;

/**
 *
 * @author zz0da
 */
public class CustomerDAO extends DBConnection implements ICustomerDAO {

    @Override
    public Customer get_customer_detail(String id) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Customer cus join CusRes cusres on cus.id = cusres.cus_id  where id = ?";

        try {
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            //assign data to books
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFullname(rs.getString("fullname"));
                customer.setPhone(rs.getString("phone"));
                String gender = "Khác";
                if (rs.getBoolean("gender")) {
                    gender = rs.getBoolean("gender") ? "Nam" : "Nữ";
                }
                customer.setGender(gender);
                customer.setJob(rs.getString("job"));
                customer.setAddress(rs.getString("address"));
                customer.setDob(rs.getString("dob"));
                customer.setCountry(rs.getString("country"));
                customer.setDescription(rs.getString("description"));
                String cstatus = "Đang chờ";
                if (rs.getString("status").trim().equals("Doing")) {
                    cstatus = "Đang khám";
                } else if (rs.getString("status").trim().equals("Done")) {
                    cstatus = "Đã khám";
                }
                customer.setStatus(cstatus);
                customer.setCode("HE" + rs.getInt("code"));
                customer.setCreated_by(rs.getString("created_by")); // Thêm tên Bác Sĩ
                customer.setCreated_at(rs.getString("created_at"));
                customer.setTest_result(rs.getString("test_result"));
                customer.setExamination_card(rs.getString("examination_card"));
                customer.setTime_return(rs.getString("time_return"));
                return customer;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }

        return null;
    }

    @Override
    public ArrayList<Customer> list_customer(String id, String status, int currentPage, int recordsPerpage) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Customer> list_customer = new ArrayList<>();
        String sql = " from Customer cus join CusRes cusres on cus.id = cusres.cus_id where 1=1 \n";
        if (id != null) {
            sql = sql + "and created_by = ?";
        }
        if (status != "") {
            sql = sql + " and cus.status = ?";
        }
        String query = "With count AS( SELECT *, ROW_NUMBER() OVER ( ORDER BY created_at)  as RowNumber " + sql 
                + ") select * from count\n"
                + "Where RowNumber Between ? and ?";
        try {
            con = super.open();
            ps = con.prepareStatement(query);
            int i = 0;
            if (id != "") {
                
                ps.setString(++i, id);

            }
            if (status != "") {
                ps.setString(++i, status);

            }
            int start = currentPage * recordsPerpage - recordsPerpage + 1;
            int end = recordsPerpage * currentPage;
            ps.setInt(++i, start);
            ps.setInt(++i, end);
            rs = ps.executeQuery();
            //assign data to books
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFullname(rs.getString("fullname"));
                customer.setPhone(rs.getString("phone"));
                String gender = "Khác";
                if (rs.getBoolean("gender")) {
                    gender = rs.getBoolean("gender") ? "Nam" : "Nữ";
                }
                customer.setGender(gender);
                customer.setJob(rs.getString("job"));
                customer.setAddress(rs.getString("address"));
                customer.setDob(rs.getString("dob"));
                customer.setCountry(rs.getString("country"));
                customer.setDescription(rs.getString("description"));
                String cstatus = "Đang chờ";
                if (rs.getString("status").trim().equals("Doing")) {
                    cstatus = "Đang khám";
                } else if (rs.getString("status").trim().equals("Done")) {
                    cstatus = "Đã khám";
                }
                customer.setStatus(cstatus);
                customer.setCode("HE" + rs.getInt("code"));
                customer.setCreated_by(rs.getString("created_by")); // Thêm tên Bác Sĩ
                customer.setCreated_at(rs.getString("created_at"));
                customer.setTest_result(rs.getString("test_result"));
                customer.setExamination_card(rs.getString("examination_card"));
                customer.setTime_return(rs.getString("time_return"));

                list_customer.add(customer);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }

        return list_customer;
    }

    @Override
    public int getNumberOfRows(String id, String status) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer numOfRows = 0;
        String sql = " select * from Customer cus join CusRes cusres on cus.id = cusres.cus_id where 1 = 1 \n";
        if (id != null) {
            sql = sql + "and created_by = ?";
        }
        if (status != "") {
            sql = sql + " and cus.status = ?";
        }
        try {
            con = super.open();
            ps = con.prepareStatement(sql);
            int i = 0;
            if (id != "") {
                ps.setString(++i, id);

            }
            if (status != "") {
                ps.setString(++i, status);

            }
            rs = ps.executeQuery();
            while (rs.next()) {
                numOfRows++;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return numOfRows;
    }

}
