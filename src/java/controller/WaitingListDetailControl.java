/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;

/**
 *
 * @author Alienware
 */
@WebServlet(name = "WaitingListDetailControl", urlPatterns = {"/WaitingListDetailControl"})
public class WaitingListDetailControl extends HttpServlet {

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
        CustomerDAO customerDao = new CustomerDAO();
        String cusid = request.getParameter("cusid");
        request.getSession().setAttribute("listcustomer", customerDao.getListCustomerByName("","",cusid));
        request.getRequestDispatcher("view/recep/waiting_list_detail.jsp").forward(request, response);
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
            Logger.getLogger(WaitingListDetailControl.class.getName()).log(Level.SEVERE, null, ex);
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

            String id = request.getParameter("id");
            String fullname = request.getParameter("fullname").trim();
            String gender = request.getParameter("gender").trim();
            String job = request.getParameter("job").trim();
            String address = request.getParameter("address").trim();
            String dob = request.getParameter("dob").trim();
            String country = request.getParameter("country").trim();
            String description = request.getParameter("description").trim();
            String examination_card = request.getParameter("examination_card").trim();
            String test_result = request.getParameter("test_result").trim();
            String time_return = request.getParameter("time_return").trim();
            //set customer by id
            CustomerDAO cus_dao = new CustomerDAO();
            Customer customer_update = cus_dao.get_customer_detail(id);
            customer_update.setId(Integer.parseInt(id));
            customer_update.setFullname(fullname);
            customer_update.setGender(gender);
            customer_update.setJob(job);
            customer_update.setAddress(address);
            customer_update.setDob(dob);
            customer_update.setCountry(country);
            customer_update.setDescription(description);
            customer_update.setExamination_card(examination_card);
            customer_update.setTest_result(test_result);
            customer_update.setTime_return(time_return);
            cus_dao.updateCustomer(customer_update);
            cus_dao.updateCustomerResult(customer_update);
            response.sendRedirect("view/recep/waiting_list");
        } catch (Exception e) {
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
