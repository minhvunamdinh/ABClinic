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
        if (id == null || id.equals("null") || id.isEmpty()) {
            try {
                Customer cus = customerDao.get_customer_detail(id);
                request.setAttribute("id", "");
                request.setAttribute("fullname", "");
                request.setAttribute("phone", "");
                request.setAttribute("age", "");
                request.setAttribute("email", "");
                request.setAttribute("country", "");
                request.setAttribute("dob", "");
                request.setAttribute("job", "");
                request.setAttribute("gender", "");
                request.getSession().setAttribute("listdoctor", doctorDao.getAllDoctor());
            } catch (Exception ex) {
                Logger.getLogger(AddNewCustomerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("view/recep/insertorder.jsp").forward(request, response);
        } else {
            Customer cus = customerDao.get_customer_detail(id);
            request.setAttribute("id", cus.getId());
            request.setAttribute("fullname", cus.getFullname());
            request.setAttribute("phone", cus.getPhone());
            request.setAttribute("age", cus.getDob());
            request.setAttribute("email", cus.getFullname());
            request.setAttribute("country", cus.getCountry());
            request.setAttribute("dob", cus.getDob());
            request.setAttribute("job", cus.getJob());
            request.setAttribute("gender", cus.getGender());
            request.setAttribute("customer", cus);
            try {
                request.getSession().setAttribute("listdoctor", doctorDao.getAllDoctor());
            } catch (Exception ex) {
                Logger.getLogger(AddNewCustomerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("view/recep/insertorder.jsp").forward(request, response);
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
        try {
            String id = request.getParameter("id");
            HttpSession session = request.getSession();
            DoctorDAO doctorDao = new DoctorDAO();
            CustomerDAO customerDao = new CustomerDAO();
            String phone = request.getParameter("phone");
            String fullname = request.getParameter("fullname");
            String country = request.getParameter("country");
            String age = request.getParameter("age");
            String email = request.getParameter("email");
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String job = request.getParameter("job");
            String status = request.getParameter("status");
            String doctor = request.getParameter("doctor");
            Customer customer = new Customer(customerDao.countCusres(), fullname, phone, gender, job, dob, "", country, status, "Waiting", customerDao.Lastitems()+"", "", "", "", "", "", "");
            try {
                customerDao.insertNewCustomer(customer);
                customerDao.insertNewCustomerResult(customer);
                customer.setCreated_by(doctor);
                request.getSession().setAttribute("listcustomer", customerDao.getListCustomerByName("","",""));
                request.getRequestDispatcher("view/recep/waiting_list.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(AddNewCustomerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
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
