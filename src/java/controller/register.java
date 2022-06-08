/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import interfaceDAO.IAccountDAO;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.User;

/**
 *
 * @author admin
 */
@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/register.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            IAccountDAO accountDAO = new AccountDAO();//implement interface
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password").trim();
            int role = Integer.parseInt(request.getParameter("role"));

            String fullname = request.getParameter("fullname").trim();
            String address = request.getParameter("address").trim();
            String email = request.getParameter("email").trim();
            String phone = request.getParameter("phone").trim();
            String dob = request.getParameter("dob");
            int gender = Integer.parseInt(request.getParameter("gender"));
           
//            String cfPassword = request.getParameter("cfPassword").trim();
            boolean checkUsername = accountDAO.isUsernameExist(username);
            if(!checkUsername){
                Account account = new Account(username, password, role, 1);
            accountDAO.registerAccount(account);
            
            int accountId = 0;
             ArrayList<Account> a =accountDAO.selectIdAccount();
             for (Account ac : a) {
                accountId = ac.getId();
            }
            
            if (accountId != 0) {
                User user = new User(accountId, fullname, address, phone, email, dob, gender);
                accountDAO.registerProfile(user);
                request.setAttribute("message", "ok");
            }
            }else{
                 request.setAttribute("message", "Username Exsit!");
            }
           
            

            request.getRequestDispatcher("view/register.jsp").forward(request, response);

        } catch (Exception ex) {
            request.getRequestDispatcher("error/error.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
