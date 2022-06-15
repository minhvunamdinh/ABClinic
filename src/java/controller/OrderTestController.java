/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CustomerDAO;
import dao.TestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.Test;
import model.TypeTest;

/**
 *
 * @author zz0da
 */
@WebServlet(name = "OrderTestController", urlPatterns = {"/order_test"})
public class OrderTestController extends HttpServlet {

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
            out.println("<title>Servlet OrderTestController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderTestController at " + request.getContextPath() + "</h1>");
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
            TestDAO test_dao = new TestDAO();
            // Danh sách loại xét nghiệm
            ArrayList<TypeTest> list_type_test = test_dao.list_type_test();
            request.setAttribute("list_type_test", list_type_test);
            //Danh sách các xét nghiệm
            ArrayList<Test> list_test = test_dao.list_test();
            request.setAttribute("list_test", list_test);

            // Danh sách xét nghiệm của bệnh nhân
            String id = request.getParameter("cus_id");
            CustomerDAO customer_dao = new CustomerDAO();
            Customer customer = customer_dao.get_customer_detail(id);
            request.setAttribute("customer", customer);

            request.getRequestDispatcher("view/doctor/order_test.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", e);
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

            // Danh sách xét nghiệm của bệnh nhân
            String id = request.getParameter("cus_id");
            CustomerDAO customer_dao = new CustomerDAO();
            Customer customer = customer_dao.get_customer_detail(id);
            //update Customer
            String test = "";
            String[] list_test = request.getParameterValues("test");
            if (list_test != null) {
                test = String.join(", ", list_test) + ",";

            }
            test = " " + test;
            customer.setList_test(test);
            String note = request.getParameter("note");
            customer.setNote(note);
            customer_dao.updateCustomerResult(customer);
            response.sendRedirect("customer_detail?id=" + customer.getId());
        } catch (Exception e) {
            request.setAttribute("error", e);
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
