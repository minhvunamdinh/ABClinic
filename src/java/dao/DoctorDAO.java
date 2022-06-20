/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.DBConnection;
import interfaceDAO.IDoctorDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Doctor;

/**
 *
 * @author zz0da
 */
public class DoctorDAO extends DBConnection implements IDoctorDAO {

    @Override
    public void change_Customer(String id, String status) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "UPDATE Customer SET status = ? WHERE id = ?";
        try {
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, id);
            ps.executeUpdate();

        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
    }
    public List<Doctor> getAllDoctor() throws Exception{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Doctor> listDoctor = new ArrayList<>();
        String sql = "SELECT dbo.Profile.id,[user_id],fullname,[address],phone,email,dob,gender FROM dbo.Account JOIN dbo.Profile ON Profile.user_id = Account.id\n" +
                     "WHERE role_id = '2'";

        try {
            con = super.open();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //assign data to books
            while (rs.next()) {
                Doctor dr = new Doctor();
                dr.setId(rs.getString("id"));
                dr.setUser_id(rs.getString("user_id"));
                dr.setFullname(rs.getString("fullname"));
                dr.setAddress(rs.getString("address"));
                dr.setPhone(rs.getString("phone"));
                dr.setEmail(rs.getString("email"));
                dr.setDob(rs.getString("dob"));
                dr.setGender(rs.getString("gender"));
                listDoctor.add(dr);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return listDoctor; 
    }
}
