/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.User;

/**
 *
 * @author zz0da
 */
@WebServlet(name = "CustomerDetailController", urlPatterns = {"/customer_detail"})
public class CustomerDetailController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerDetailController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
            HttpSession session = request.getSession();
            User user_session = (User) session.getAttribute("user");
            request.setAttribute("user", user_session);
            String id = request.getParameter("id");
            CustomerDAO customer_dao = new CustomerDAO();
            Customer customer = customer_dao.get_customer_detail(id);
            request.setAttribute("customer", customer);

            request.getRequestDispatcher("view/customer_detail.jsp").forward(request, response);
        } catch (Exception e) {
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
            Customer customer_update = new Customer();
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
            response.sendRedirect("customer_detail?id="+id);
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
