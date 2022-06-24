/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import interfaceDAO.IAccountDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author admin
 */
@WebServlet(name = "AdminEditAccount", urlPatterns = {"/AdminEditAccount"})
public class AdminEditAccount extends HttpServlet {


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
         try {
            int aid = Integer.parseInt(request.getParameter("aid"));
            
            IAccountDAO accountDAO = new AccountDAO();
            
              ArrayList<Account> listPage = accountDAO.getAccountByID(aid);
              request.setAttribute("listPage", listPage);
              request.getRequestDispatcher("view/admin/editAccount.jsp").forward(request, response);
        } catch (Exception ex) {
            request.getRequestDispatcher("error/error.jsp").forward(request, response);
        }
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
                    request.setCharacterEncoding("UTF-8");
                    int aid = Integer.parseInt(request.getParameter("uid").trim());
                    String fullname = request.getParameter("fullname").trim();
                    String dob = request.getParameter("dob");
                    int gender = Integer.parseInt(request.getParameter("gender").trim());
                    String address = request.getParameter("address").trim();
                    IAccountDAO accountDAO = new AccountDAO();
                    Account account = new Account(aid, fullname,address,dob,gender);
                      int listPage = accountDAO.updateAccount(account);
                      if(listPage !=0){
                          response.sendRedirect("AdminViewAccountController");
                      }else{
                          ArrayList<Account> data = accountDAO.getAccountByID(aid);
                          request.setAttribute("listPage", data);
                          request.setAttribute("message", "Sorry!");
                          request.getRequestDispatcher("view/admin/editAccount.jsp").forward(request, response);
                      }
                      
                      
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
