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
import model.Customer;

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

}
