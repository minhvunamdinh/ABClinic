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
import java.util.Date;
import java.util.List;
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
                String gender = rs.getBoolean("gender") ? "Nam" : "Nu";

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
                customer.setList_test(rs.getString("list_test"));
                customer.setNote(rs.getString("note"));
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
                String gender = rs.getBoolean("gender") ? "Nam" : "Nu";
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
                customer.setList_test(rs.getString("list_test"));
                customer.setNote(rs.getString("note"));

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

    @Override
    public int updateCustomer(Customer customer) throws Exception {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " UPDATE [dbo].[Customer]\n"
                + "   SET [fullname] = ?\n"
                + "      ,[gender] =?\n"
                + "      ,[job] = ?\n"
                + "      ,[address] =?\n"
                + "      ,[dob] = ?\n"
                + "      ,[country] = ?\n"
                + "      ,[description] = ?\n"
                + " WHERE id=?\n";

        try {
            //open connection
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setString(1, customer.getFullname());
            ps.setString(2, customer.getGender());
            ps.setString(3, customer.getJob());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getDob());
            ps.setString(6, customer.getCountry());
            ps.setString(7, customer.getDescription());
            ps.setInt(8, customer.getId());
            result = ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return result;
    }

    @Override
    public int updateCustomerResult(Customer customer) throws Exception {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "UPDATE [dbo].[CusRes]\n"
                + "   SET \n"
                + "      [test_result] = ?\n"
                + "      ,[examination_card] = ?\n"
                + "      ,[time_return] = ?\n"
                + "      ,[list_test] = ?\n"
                + "      ,[note] = ?\n"
                + " WHERE cus_id = ?";

        try {
            //open connection
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setString(1, customer.getTest_result());
            ps.setString(2, customer.getExamination_card());
            ps.setString(3, customer.getTime_return());
            ps.setString(4, customer.getList_test());
            ps.setString(5, customer.getNote());
            ps.setInt(6, customer.getId());

            result = ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return result;
    }

    public int insertNewCustomer(Customer customer) throws Exception {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO dbo.Customer\n"
                + "        ( [fullname] ,\n"
                + "          [phone] ,\n"
                + "          [gender] ,\n"
                + "          [job] ,\n"
                + "          [address] ,\n"
                + "          [dob] ,\n"
                + "          [country] ,\n"
                + "          [description] ,\n"
                + "          [status]\n"
                + "        )\n"
                + "VALUES  ( ? ,? ,? ,? ,? ,? ,? ,? ,?)";
        System.out.println(sql);
        try {
            //open connection
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setString(1, customer.getFullname());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getGender());
            ps.setString(4, customer.getJob());
            ps.setString(5, customer.getAddress());
            ps.setString(6, customer.getDob());
            ps.setString(7, customer.getCountry());
            ps.setString(8, customer.getDescription());
            ps.setString(9, customer.getStatus());

            result = ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return result;
    }

    public List<Customer> getListCustomer() throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> listCustomer = new ArrayList<>();
        String sql = "select * from Customer cus join CusRes cusres on cus.id = cusres.cus_id";

        try {
            con = super.open();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //assign data to books
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFullname(rs.getString("fullname"));
                customer.setPhone(rs.getString("phone"));
                String gender = rs.getBoolean("gender") ? "Nam" : "Nu";
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
                customer.setList_test(rs.getString("list_test"));
                customer.setNote(rs.getString("note"));
                listCustomer.add(customer);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return listCustomer;
    }

    public List<Customer> getListCustomerByName(String name, String status) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> listCustomer = new ArrayList<>();
        String sql = "select * from Customer cus join CusRes cusres on cus.id = cusres.cus_id WHERE cus.status Like '%" + status + "%' AND cus.fullname LIKE '%" + name + "%'";

        try {
            con = super.open();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //assign data to books
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFullname(rs.getString("fullname"));
                customer.setPhone(rs.getString("phone"));
                String gender = rs.getBoolean("gender") ? "Nam" : "Nu";
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
                customer.setList_test(rs.getString("list_test"));
                customer.setNote(rs.getString("note"));
                listCustomer.add(customer);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return listCustomer;
    }

    public int insertNewCustomerResult(Customer customer) throws Exception {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO dbo.CusRes\n"
                + "        ( code ,created_by ,created_at ,test_result ,examination_card ,time_return ,cus_id ,list_test ,note)\n"
                + "VALUES  ( ? ,? ,? ,? ,? ,? ,? ,? ,?)";
        System.out.println(sql);
        try {
            //open connection
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setString(1, "1");
            ps.setString(2, "14");
            ps.setString(3, java.time.LocalDate.now()+"");
            ps.setString(4, "");
            ps.setString(5, "");
            ps.setString(6, "");
            ps.setString(7, customer.getId()+"");
            ps.setString(8, "");
            ps.setString(9, "");

            result = ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Customer customer = new Customer(3, "Tran", "0869", "1", "MV", "2000-12-12", "DB", "VN", "Dep trai", "abc", "", "", "", "", "", "", "");
        CustomerDAO cus = new CustomerDAO();
        System.out.println(cus.getListCustomerByName("B", ""));
        cus.insertNewCustomerResult(customer);
    }

}
