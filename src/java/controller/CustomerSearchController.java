/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CustomerDAO;
import dao.DoctorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alienware
 */
@WebServlet(name = "CustomerSearchController", urlPatterns = {"/CustomerSearchController"})
public class CustomerSearchController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerDAO customerDao = new CustomerDAO();
        try {
            request.getSession().setAttribute("listcustomer", customerDao.getListCustomerByName("", "Done"));
        } catch (Exception ex) {
            Logger.getLogger(CustomerSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("view/listsearchcustomer.jsp").forward(request, response);
    }

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
        processRequest(request, response);
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
        HttpSession session = request.getSession();
        CustomerDAO customerDao = new CustomerDAO();
        String search = request.getParameter("search");
        String typesearch = request.getParameter("searching");
        if (typesearch.equalsIgnoreCase("1")) {
            try {
                request.getSession().setAttribute("listcustomer", customerDao.getListCustomerByName(search, "Done"));
            } catch (Exception ex) {
                Logger.getLogger(CustomerSearchController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("view/listsearchcustomer.jsp").forward(request, response);
        }else{
            try {
                request.getSession().setAttribute("listcustomer", customerDao.getListCustomerByName(search, ""));
            } catch (Exception ex) {
                Logger.getLogger(CustomerSearchController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("view/customer_list_recep.jsp").forward(request, response);
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
