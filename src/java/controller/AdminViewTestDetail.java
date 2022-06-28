/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TestDAO;
import interfaceDAO.ITestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Test;
import model.TypeTest;

/**
 *
 * @author zz0da
 */
@WebServlet(name = "AdminViewTestDetail", urlPatterns = {"/admin_test_detail"})
public class AdminViewTestDetail extends HttpServlet {

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
            out.println("<title>Servlet AdminViewTestDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminViewTestDetail at " + request.getContextPath() + "</h1>");
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
            String id = request.getParameter("id");
            TestDAO testDAO = new TestDAO();
            Test test = testDAO.get_test_by_id(id);
            request.setAttribute("test", test);

            ArrayList<TypeTest> list_type_test = testDAO.list_type_test();
            request.setAttribute("list_type_test", list_type_test);
            request.getRequestDispatcher("view/admin/test_detail.jsp").forward(request, response);
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
            int id = 0;
            if (!request.getParameter("id").equals("") ) {
                id = Integer.parseInt(request.getParameter("id"));
            }

            String name = request.getParameter("name");
            int type_id = Integer.parseInt(request.getParameter("type_id"));
            int cost_price = 0;
            int sell_price = 0;
            boolean valid = true;
            try {
                cost_price = Integer.parseInt(request.getParameter("cost_price"));
            } catch (Exception e) {
                request.setAttribute("cost_price_fail", "Số tiền bạn nhập sai! Vui lòng nhập lại số tiền");
                valid = false;
            }

            try {
                sell_price = Integer.parseInt(request.getParameter("sell_price"));

            } catch (Exception e) {
                request.setAttribute("sell_price_fail", "Số tiền bạn nhập sai! Vui lòng nhập lại số tiền");
                valid = false;
            }
            TestDAO testDAO = new TestDAO();
            boolean is_active = request.getParameter("is_active").equals("1");
            String form = request.getParameter("form");
            if (id == 0) {
                if (!valid) {
                    ArrayList<TypeTest> list_type_test = testDAO.list_type_test();
                    request.setAttribute("list_type_test", list_type_test);
                    request.getRequestDispatcher("view/admin/test_detail.jsp").forward(request, response);
                    return;
                }
                Test test_new = new Test();
                test_new.setType_id(type_id);
                test_new.setName(name);
                test_new.setCost_price(cost_price);
                test_new.setSell_price(sell_price);
                test_new.setIs_active(is_active);
                test_new.setForm(form);

                testDAO.insert_test(test_new);
                request.setAttribute("success", "!!! Thêm Xét Nghiệm Thành Công !!!");
                ArrayList<Test> list_test = testDAO.get_list_test();
                request.setAttribute("list_test", list_test);
                request.getRequestDispatcher("view/admin/adminViewTest.jsp").forward(request, response);

            } else {

                Test test = testDAO.get_test_by_id(Integer.toString(id));
                request.setAttribute("test", test);

                ArrayList<TypeTest> list_type_test = testDAO.list_type_test();
                request.setAttribute("list_type_test", list_type_test);

                if (!valid) {
                    request.getRequestDispatcher("view/admin/test_detail.jsp").forward(request, response);
                    return;
                }
                Test test_update = new Test();
                test_update.setId(id);
                test_update.setType_id(type_id);
                test_update.setName(name);
                test_update.setCost_price(cost_price);
                test_update.setSell_price(sell_price);
                test_update.setIs_active(is_active);
                test_update.setForm(form);

                testDAO.update_test(test_update);
                request.setAttribute("success", "!!! Cập nhật Xét Nghiệm Thành Công !!!");
                request.getRequestDispatcher("view/admin/test_detail.jsp").forward(request, response);
            }
        } catch (Exception e) {
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
