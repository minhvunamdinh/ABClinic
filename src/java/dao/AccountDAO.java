/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.DBConnection;
import interfaceDAO.IAccountDAO;
import interfaceDAO.IEncryptPasswordDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import model.Account;
import model.User;

/**
 *
 * @author vudm
 */
public class AccountDAO extends DBConnection implements IAccountDAO {

    @Override
    public void registerAccount(Account account) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO [Account] ([username], [password], [role_id], [is_active],[salt])\n"
                + " VALUES(?,?,?,?,?)";
        try {

            con = super.open();
            ps = con.prepareStatement(sql);

            ps.setString(1, account.getUsername());
            IEncryptPasswordDAO encryptPasswordDAO = new EncryptPasswordDAO();
            byte[] salt = encryptPasswordDAO.getSalt();

            String encryptedPassword = encryptPasswordDAO.encryptPassword(account.getPassword(), salt);

            ps.setString(2, encryptedPassword);
            ps.setInt(3, account.getRole_id());
            ps.setInt(4, account.getIs_active());
            ps.setString(5, Base64.getEncoder().encodeToString(salt));
            ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
    }

    @Override
    public void registerProfile(User user) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO [Profile] ([user_id], [fullname], [address], [phone],[email],[dob],[gender])\n"
                + " VALUES(?,?,?,?,?,?,?)";
        try {

            con = super.open();
            ps = con.prepareStatement(sql);

            ps.setInt(1, user.getUser_id());
            ps.setString(2, user.getFullname());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getDob());
            ps.setInt(7, user.getGender());
            ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
    }

    @Override
    public ArrayList<Account> selectIdAccount() throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "SELECT TOP 1 id\n "
                + "FROM\n "
                + "Account\n "
                + "ORDER  BY id DESC";

        try {
            //open connection
            con = super.open();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            //assign data to books
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));

                accounts.add(account);
            }

        } catch (SQLException ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }

        return accounts;
    }

    @Override
    public boolean isUsernameExist(String username) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from Account where username = ? ";

        PreparedStatement st;

        try {
            con = super.open();
            ps = con.prepareStatement(sql);

            ps.setString(1, username);

            rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                return false;
            }

        } catch (SQLException ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }

        return true;
    }

    @Override
    public Account checkAccountByUsernameAndPassword(String username, String password) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String sql = "select * from Account\n"
                    + " where username = ?";

            Account account = new Account();
            IEncryptPasswordDAO encryptPasswordDAO = new EncryptPasswordDAO();
            //open connection
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {//assign data to authors
                String encryptedPassword = rs.getString("password");
                String salt = rs.getString("salt");
                String pass = encryptPasswordDAO.encryptPassword(password, Base64.getDecoder().decode(salt));

                if (pass.equals(encryptedPassword)) { // check pass encrypted 
                    account.setId(rs.getInt("id"));
                    account.setUsername(rs.getString("username"));
                    account.setRole_id(rs.getInt("role_id"));
                    account.setIs_active(rs.getInt("is_active"));
                    return account;
                }
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
    public User getProfileUser(String id) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Profile pro join Account ac on ac.id = pro.user_id\n"
                + "where ac.id = ?";

        try {
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            //assign data to books
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setPhone(rs.getString("phone"));

                user.setGender(rs.getInt("gender"));

                user.setAddress(rs.getString("address"));
                user.setDob(rs.getString("dob"));
                user.setEmail(rs.getString("email"));
                String role = "Boss";
                switch (rs.getInt("role_id")) {
                    case 1:
                        role = "Boss";
                        break;
                    case 2:
                        role = "Bác Sĩ";
                        break;
                    case 3:
                        role = "Lễ Tân";
                        break;
                }
                user.setRole(role);
                return user;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }

        return null;
    }
 
}
