/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import interfaceDAO.IAccountDAO;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author admin
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class loginController extends HttpServlet {

   

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
            Cookie[] cookie = request.getCookies();
            String userName = "";
            String passWord = "";

            if (cookie != null) { // if get cookie get value
                for (Cookie cookie1 : cookie) {
                    if (cookie1.getName().equals("Username")) {
                        //get cookie equal username
                        userName = cookie1.getValue();
                    }
                    if (cookie1.getName().equals("Password")) {
                        //get cookie equal password
                        passWord = cookie1.getValue();
                    }
                }

                IAccountDAO accountDAO = new AccountDAO();
                Account account = accountDAO.checkAccountByUsernameAndPassword(userName, passWord);
                if (account != null && account.getIs_active() == 1) {// if get account success

                    request.getSession().setAttribute("account", account);
                    request.setAttribute("Message", "ok! ");
//                    response.sendRedirect("home");
                } else {
                    request.setAttribute("user", null);
                    request.setAttribute("pass", null);
                    request.getRequestDispatcher("view/login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("user", null);
                request.setAttribute("pass", null);
                request.getRequestDispatcher("view/login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("error", "Sorry! Error occurred, THAT PAGE DOESN'T EXIST OR IS UNAVABLE.");
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
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password");
            String remember = request.getParameter("remember");

            Pattern pattern = Pattern.compile("^[0-9]|[@. #%&*|]+$");
            Pattern patternCheck = Pattern.compile("[A-Za-z0-9]{250,}");
            Pattern pattern1 = Pattern.compile("^[@. #%&*|]$");
            Matcher matcher = pattern.matcher(username);
            Matcher matcherCheck = patternCheck.matcher(username);
            Matcher matcher1 = pattern1.matcher(password);

            if (matcher.find() || matcherCheck.find()) {//if username contain special characters
                request.setAttribute("Message", "Please check User Name again!!! ");
                request.getRequestDispatcher("view/login.jsp").forward(request, response);
            } else if (matcher1.find()) {//if password contain special characters
                request.setAttribute("Message", "Don't use special character in Password!!! ");
                request.getRequestDispatcher("view/login.jsp").forward(request, response);
            }
            IAccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.checkAccountByUsernameAndPassword(username, password);
           

            if (account != null ) { // check account null or not
                 int isActive = account.getIs_active();
                if((isActive ==1)){
                    request.getSession().setAttribute("account", account);
                    if (remember != null) {// check user clicked remember account
                        Cookie user = new Cookie("Username", account.getUsername());
                        Cookie pass = new Cookie("Password", password.trim());
                        user.setMaxAge(60 * 60 * 24 * 7);
                        pass.setMaxAge(60 * 60 * 24 * 7);
                        response.addCookie(user);
                        response.addCookie(pass);
                    }
                    request.setAttribute("Message", "ok! ");
                }else{
                    request.setAttribute("Message", "Account not active! ");
                }
                
                request.getRequestDispatcher("view/login.jsp").forward(request, response);
//                response.sendRedirect("home");
            } else { // if account null
                request.setAttribute("user", username);
                request.setAttribute("pass", password);
                request.setAttribute("Message", "Please check your username or password!!! ");
                request.getRequestDispatcher("view/login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("error", "Sorry! Error occurred, THAT PAGE DOESN'T EXIST OR IS UNAVABLE.");
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
