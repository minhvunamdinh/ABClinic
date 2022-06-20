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
import model.Customer;
import model.Doctor;

/**
 *
 * @author Alienware
 */
@WebServlet(name = "AddNewCustomerControl", urlPatterns = {"/addnewcus"})
public class AddNewCustomerControl extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
            DoctorDAO doctorDao = new DoctorDAO();
            CustomerDAO customerDao = new CustomerDAO();
        if (id == null || id.equals("null")) {
            try {
                request.getSession().setAttribute("listcustomer", customerDao.getListCustomer());
                request.getSession().setAttribute("listdoctor", doctorDao.getAllDoctor());
            } catch (Exception ex) {
                Logger.getLogger(AddNewCustomerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("view/insertorder.jsp").forward(request, response);
        } else {
            Customer cus = customerDao.get_customer_detail(id);
            request.setAttribute("fullname", cus.getFullname());
            request.setAttribute("phone", cus.getPhone());
            request.setAttribute("age", cus.getDob());
            request.setAttribute("email", cus.getFullname());
            request.setAttribute("country", cus.getCountry());
            request.setAttribute("dob", cus.getDob());
            request.setAttribute("job", cus.getJob());
            request.setAttribute("gender", cus.getGender());
             try {
                request.getSession().setAttribute("listcustomer", customerDao.getListCustomer());
                request.getSession().setAttribute("listdoctor", doctorDao.getAllDoctor());
            } catch (Exception ex) {
                Logger.getLogger(AddNewCustomerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("view/insertorder.jsp").forward(request, response);
        }

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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddNewCustomerControl.class.getName()).log(Level.SEVERE, null, ex);
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
        String phone = request.getParameter("phone");
        String fullname = request.getParameter("fullname");
        System.out.println(fullname);
        String country = request.getParameter("country");
        String age = request.getParameter("age");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String job = request.getParameter("job");
        String status = request.getParameter("status");
        CustomerDAO cus = new CustomerDAO();
        try {
            cus.insertNewCustomer(new Customer(0, fullname, phone, gender, job, dob, "", country, status, "Waiting", "", "", "", "", "", "", ""));
        } catch (Exception ex) {
            Logger.getLogger(AddNewCustomerControl.class.getName()).log(Level.SEVERE, null, ex);
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
