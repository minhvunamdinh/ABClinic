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
import java.util.Arrays;
import java.util.List;
import model.Customer;
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

    @Override
    public ArrayList<Test> get_list_test() throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Test> list_test = new ArrayList<>();
        String sql = "select * from Test test join TypeTest type_test on test.type_id = type_test.type_id ";

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
                test.setType_test_name(rs.getString("type_test_name"));
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

    public void change_status_test(String id, String status) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "UPDATE Test SET is_active = ? WHERE id = ?";
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

    @Override
    public Test get_test_by_id(String id) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Test test join TypeTest type_test on test.type_id = type_test.type_id  where test.id = ?";

        try {
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            //assign data to books
            while (rs.next()) {
                Test test = new Test();
                test.setId(rs.getInt("id"));
                test.setName(rs.getString("name"));
                test.setType_id(rs.getInt("type_id"));
                test.setCost_price(rs.getInt("cost_price"));
                test.setSell_price(rs.getInt("sell_price"));
                test.setIs_active(rs.getBoolean("is_active"));
                test.setForm(rs.getString("form"));
                test.setType_test_name(rs.getString("type_test_name"));
                return test;
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
    public int update_test(Test test) throws Exception {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "UPDATE [dbo].[Test]\n"
                + "   SET [type_id] = ?\n"
                + "      ,[name] = ?\n"
                + "      ,[cost_price] = ?\n"
                + "      ,[sell_price] = ?\n"
                + "      ,[is_active] = ?\n"
                + "      ,[form] = ?"
                + " WHERE id=?\n";

        try {
            //open connection
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setInt(1, test.getType_id());
            ps.setString(2, test.getName());
            ps.setInt(3, test.getCost_price());
            ps.setInt(4, test.getSell_price());
            ps.setInt(5, test.isIs_active() ? 1 : 0);
            ps.setString(6, test.getForm());
            ps.setInt(7, test.getId());

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
    public int insert_test(Test test) throws Exception {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO [dbo].[Test]\n"
                + "           ([type_id]\n"
                + "           ,[name]\n"
                + "           ,[cost_price]\n"
                + "           ,[sell_price]\n"
                + "           ,[is_active]\n"
                + "           ,[form])\n"
                + "     VALUES (?,?,?,?,?,?)";

        try {
            //open connection
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setInt(1, test.getType_id());
            ps.setString(2, test.getName());
            ps.setInt(3, test.getCost_price());
            ps.setInt(4, test.getSell_price());
            ps.setInt(5, test.isIs_active() ? 1 : 0);
            ps.setString(6, test.getForm());

            result = ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return result;

>>>>>>> 5c4f51128eed1fdf5bb806202e87b68e94d1dd90
    }

}
