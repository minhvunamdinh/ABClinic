/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.DBConnection;
import interfaceDAO.ITestDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Test;
import model.TypeTest;

/**
 *
 * @author zz0da
 */
public class TestDAO extends DBConnection implements ITestDAO {

    @Override
    public ArrayList<TypeTest> list_type_test() throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TypeTest> list_type_test = new ArrayList<>();
        String sql = "select * from TypeTest ORDER BY type_id";

        try {
            con = super.open();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //assign data to books
            while (rs.next()) {
                TypeTest type_test = new TypeTest();
                type_test.setType_id(rs.getInt("type_id"));
                type_test.setType_name(rs.getString("type_test_name"));
                list_type_test.add(type_test);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }

        return list_type_test;
    }

    @Override
    public ArrayList<Test> list_test() throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Test> list_test = new ArrayList<>();
        String sql = "select * from Test  where is_active = 1 ORDER BY type_id";

        try {
            con = super.open();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //assign data to books
            while (rs.next()) {
                Test test = new Test();
                test.setId(rs.getInt("id"));
                test.setType_id(rs.getInt("type_id"));
                test.setName(rs.getString("name"));
                test.setCost_price(rs.getInt("cost_price"));
                test.setSell_price(rs.getInt("sell_price"));
                test.setIs_active(rs.getBoolean("is_active"));
                test.setForm(rs.getString("form"));
                list_test.add(test);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }

        return list_test;
    }

    @Override
    public ArrayList<Test> list_test_by_customer(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
