/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceDAO;

import java.util.ArrayList;
import model.Account;
import model.User;

/**
 *
 * @author vudm
 */
public interface IAccountDAO {
    public void registerAccount(Account account) throws Exception;
    
    public void registerProfile(User user) throws Exception;
    
    public ArrayList<Account> selectIdAccount() throws Exception;
    
    public boolean isUsernameExist(String username) throws Exception;
    
    public Account checkAccountByUsernameAndPassword(String username, String password) throws Exception;
}
