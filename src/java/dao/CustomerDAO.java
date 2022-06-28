/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.DBConnection;
import interfaceDAO.ICustomerDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Customer;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import model.Export;
import model.Test;
import model.TypeTest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author zz0da
 */
public class CustomerDAO extends DBConnection implements ICustomerDAO {

    @Override
    public Customer get_customer_detail(String id) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Customer cus join CusRes cusres on cus.id = cusres.cus_id  where id = ?";

        try {
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            //assign data to books
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFullname(rs.getString("fullname"));
                customer.setPhone(rs.getString("phone"));
                String gender = rs.getBoolean("gender") ? "Nam" : "Nu";

                customer.setGender(gender);
                customer.setJob(rs.getString("job"));
                customer.setAddress(rs.getString("address"));
                customer.setDob(rs.getString("dob"));
                customer.setCountry(rs.getString("country"));
                customer.setDescription(rs.getString("description"));
                String cstatus = "Đang chờ";
                if (rs.getString("status").trim().equals("Doing")) {
                    cstatus = "Đang khám";
                } else if (rs.getString("status").trim().equals("Done")) {
                    cstatus = "Đã khám";
                }
                customer.setStatus(cstatus);
                customer.setCode("HE" + rs.getInt("code"));
                customer.setCreated_by(rs.getString("created_by")); // Thêm tên Bác Sĩ
                customer.setCreated_at(rs.getString("created_at"));
                customer.setTest_result(rs.getString("test_result"));
                customer.setExamination_card(rs.getString("examination_card"));
                customer.setTime_return(rs.getString("time_return"));
                customer.setList_test(rs.getString("list_test"));
                customer.setNote(rs.getString("note"));
                return customer;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }

        return null;
    }

    @Override
    public ArrayList<Customer> list_customer(String id, String status, int currentPage, int recordsPerpage) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Customer> list_customer = new ArrayList<>();
        String sql = " from Customer cus join CusRes cusres on cus.id = cusres.cus_id where 1=1 \n";
        if (id != null) {
            sql = sql + "and created_by = ?";
        }
        if (status != "") {
            sql = sql + " and cus.status = ?";
        }
        String query = "With count AS( SELECT *, ROW_NUMBER() OVER ( ORDER BY created_at)  as RowNumber " + sql
                + ") select * from count\n"
                + "Where RowNumber Between ? and ?";
        try {
            con = super.open();
            ps = con.prepareStatement(query);
            int i = 0;
            if (id != "") {

                ps.setString(++i, id);

            }
            if (status != "") {
                ps.setString(++i, status);

            }
            int start = currentPage * recordsPerpage - recordsPerpage + 1;
            int end = recordsPerpage * currentPage;
            ps.setInt(++i, start);
            ps.setInt(++i, end);
            rs = ps.executeQuery();
            //assign data to books
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFullname(rs.getString("fullname"));
                customer.setPhone(rs.getString("phone"));
                String gender = rs.getBoolean("gender") ? "Nam" : "Nu";
                customer.setGender(gender);
                customer.setJob(rs.getString("job"));
                customer.setAddress(rs.getString("address"));
                customer.setDob(rs.getString("dob"));
                customer.setCountry(rs.getString("country"));
                customer.setDescription(rs.getString("description"));
                String cstatus = "Đang chờ";
                if (rs.getString("status").trim().equals("Doing")) {
                    cstatus = "Đang khám";
                } else if (rs.getString("status").trim().equals("Done")) {
                    cstatus = "Đã khám";
                }
                customer.setStatus(cstatus);
                customer.setCode("HE" + rs.getInt("code"));
                customer.setCreated_by(rs.getString("created_by")); // Thêm tên Bác Sĩ
                customer.setCreated_at(rs.getString("created_at"));
                customer.setTest_result(rs.getString("test_result"));
                customer.setExamination_card(rs.getString("examination_card"));
                customer.setTime_return(rs.getString("time_return"));
                customer.setList_test(rs.getString("list_test"));
                customer.setNote(rs.getString("note"));

                list_customer.add(customer);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }

        return list_customer;
    }

    @Override
    public int getNumberOfRows(String id, String status) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer numOfRows = 0;
        String sql = " select * from Customer cus join CusRes cusres on cus.id = cusres.cus_id where 1 = 1 \n";
        if (id != null) {
            sql = sql + "and created_by = ?";
        }
        if (status != "") {
            sql = sql + " and cus.status = ?";
        }
        try {
            con = super.open();
            ps = con.prepareStatement(sql);
            int i = 0;
            if (id != "") {
                ps.setString(++i, id);

            }
            if (status != "") {
                ps.setString(++i, status);

            }
            rs = ps.executeQuery();
            while (rs.next()) {
                numOfRows++;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return numOfRows;
    }

    @Override
    public int updateCustomer(Customer customer) throws Exception {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " UPDATE [dbo].[Customer]\n"
                + "   SET [fullname] = ?\n"
                + "      ,[gender] =?\n"
                + "      ,[job] = ?\n"
                + "      ,[address] =?\n"
                + "      ,[dob] = ?\n"
                + "      ,[country] = ?\n"
                + "      ,[description] = ?\n"
                + " WHERE id=?\n";

        try {
            //open connection
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setString(1, customer.getFullname());
            ps.setString(2, customer.getGender());
            ps.setString(3, customer.getJob());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getDob());
            ps.setString(6, customer.getCountry());
            ps.setString(7, customer.getDescription());
            ps.setInt(8, customer.getId());
            result = ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return result;
    }

    @Override
    public int updateCustomerResult(Customer customer) throws Exception {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "UPDATE [dbo].[CusRes]\n"
                + "   SET \n"
                + "      [test_result] = ?\n"
                + "      ,[examination_card] = ?\n"
                + "      ,[time_return] = ?\n"
                + "      ,[list_test] = ?\n"
                + "      ,[note] = ?\n"
                + " WHERE cus_id = ?";

        try {
            //open connection
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setString(1, customer.getTest_result());
            ps.setString(2, customer.getExamination_card());
            ps.setString(3, customer.getTime_return());
            ps.setString(4, customer.getList_test());
            ps.setString(5, customer.getNote());
            ps.setInt(6, customer.getId());

            result = ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return result;
    }

    public int insertNewCustomer(Customer customer) throws Exception {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO dbo.Customer\n"
                + "        ( [fullname] ,\n"
                + "          [phone] ,\n"
                + "          [gender] ,\n"
                + "          [job] ,\n"
                + "          [address] ,\n"
                + "          [dob] ,\n"
                + "          [country] ,\n"
                + "          [description] ,\n"
                + "          [status]\n"
                + "        )\n"
                + "VALUES  ( ? ,? ,? ,? ,? ,? ,? ,? ,?)";
        System.out.println(sql);
        try {
            //open connection
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setString(1, customer.getFullname());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getGender());
            ps.setString(4, customer.getJob());
            ps.setString(5, customer.getAddress());
            ps.setString(6, customer.getDob());
            ps.setString(7, customer.getCountry());
            ps.setString(8, customer.getDescription());
            ps.setString(9, customer.getStatus());

            result = ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return result;
    }

    public List<Customer> getListCustomer() throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> listCustomer = new ArrayList<>();
        String sql = "select * from Customer cus join CusRes cusres on cus.id = cusres.cus_id";

        try {
            con = super.open();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //assign data to books
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFullname(rs.getString("fullname"));
                customer.setPhone(rs.getString("phone"));
                String gender = rs.getBoolean("gender") ? "Nam" : "Nu";
                customer.setGender(gender);
                customer.setJob(rs.getString("job"));
                customer.setAddress(rs.getString("address"));
                customer.setDob(rs.getString("dob"));
                customer.setCountry(rs.getString("country"));
                customer.setDescription(rs.getString("description"));
                String cstatus = "Đang chờ";
                if (rs.getString("status").trim().equals("Doing")) {
                    cstatus = "Đang khám";
                } else if (rs.getString("status").trim().equals("Done")) {
                    cstatus = "Đã khám";
                }
                customer.setStatus(cstatus);
                customer.setCode("HE" + rs.getInt("code"));
                customer.setCreated_by(rs.getString("created_by")); // Thêm tên Bác Sĩ
                customer.setCreated_at(rs.getString("created_at"));
                customer.setTest_result(rs.getString("test_result"));
                customer.setExamination_card(rs.getString("examination_card"));
                customer.setTime_return(rs.getString("time_return"));
                customer.setList_test(rs.getString("list_test"));
                customer.setNote(rs.getString("note"));
                listCustomer.add(customer);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return listCustomer;
    }

    public List<Customer> getListCustomerByName(String name, String status, String cusid) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> listCustomer = new ArrayList<>();
        String sql = "";
        if (cusid.equals("")) {
            sql = "select * from Customer cus join CusRes cusres on cus.id = cusres.cus_id WHERE cus.status Like '%" + status + "%' AND cus.fullname LIKE '%" + name + "%'";
        } else {
            sql = "select * from Customer cus join CusRes cusres on cus.id = cusres.cus_id WHERE cus.status Like '%" + status + "%' AND cus.fullname LIKE '%" + name + "%' AND cusres.cus_id like '%" + cusid + "%'";
        }

        try {
            con = super.open();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //assign data to books
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFullname(rs.getString("fullname"));
                customer.setPhone(rs.getString("phone"));
                String gender = rs.getBoolean("gender") ? "Nam" : "Nu";
                customer.setGender(gender);
                customer.setJob(rs.getString("job"));
                customer.setAddress(rs.getString("address"));
                customer.setDob(rs.getString("dob"));
                customer.setCountry(rs.getString("country"));
                customer.setDescription(rs.getString("description"));
                String cstatus = "Đang chờ";
                if (rs.getString("status").trim().equals("Doing")) {
                    cstatus = "Đang khám";
                } else if (rs.getString("status").trim().equals("Done")) {
                    cstatus = "Đã khám";
                }
                customer.setStatus(cstatus);
                customer.setCode("HE" + rs.getInt("code"));
                customer.setCreated_by(rs.getString("created_by")); // Thêm tên Bác Sĩ
                customer.setCreated_at(rs.getString("created_at"));
                customer.setTest_result(rs.getString("test_result"));
                customer.setExamination_card(rs.getString("examination_card"));
                customer.setTime_return(rs.getString("time_return"));
                customer.setList_test(rs.getString("list_test"));
                customer.setNote(rs.getString("note"));
                listCustomer.add(customer);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return listCustomer;
    }

    public int insertNewCustomerResult(Customer customer) throws Exception {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO dbo.CusRes\n"
                + "        (  code ,created_by ,created_at ,test_result ,examination_card ,time_return ,cus_id ,list_test ,note)\n"
                + "VALUES  ( ? ,? ,? ,? ,? ,? ,? ,? ,?)";
        try {
            //open connection
            con = super.open();
            ps = con.prepareStatement(sql);
            ps.setString(1, customer.getId() + "");
            ps.setString(2, customer.getCreated_by());
            ps.setString(3, java.time.LocalDate.now() + "");//
            ps.setString(4, customer.getTest_result());
            ps.setString(5, customer.getExamination_card());
            ps.setString(6, customer.getTime_return());
            ps.setString(7, customer.getCode() + "");
            ps.setString(8, "");
            ps.setString(9, customer.getNote());
            result = ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return result;
    }

    public int countCusres() throws Exception {
        int cusres = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT* FROM dbo.CusRes";
        try {
            //open connection
            con = super.open();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cusres = cusres + 1;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return cusres;
    }

    public int Lastitems() throws Exception {
        int cusres = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT TOP 1 * FROM dbo.Customer ORDER BY ID DESC";
        try {
            //open connection
            con = super.open();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Customer customer = new Customer();
            while (rs.next()) {

                customer.setId(rs.getInt("id"));
            }
            cusres = customer.getId();
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close connection
            super.close(con, ps, rs);
        }
        return cusres;
    }

    public void ExportTest(List<Test> ListTest, int id)
            throws IOException, Exception {
        CustomerDAO customer_dao = new CustomerDAO();
        TestDAO tstdao = new TestDAO();
        String folderFilePath = "C:\\Users\\Alienware\\OneDrive\\Desktop\\file_excel\\text.xlsx";
        float total = 0;
        String[] COLUMNs = {"STT", "Tên hàng hóa, dịch vụ", "Đơn vị", "Số Lượng", "Đơn giá", "BHYT chi trả", "Miễn giảm",
            "Kết Quả"};
        File file = new File(folderFilePath);
        file.getParentFile().mkdirs();
        try (Workbook workbook = new XSSFWorkbook(); OutputStream os = new FileOutputStream(folderFilePath);) {
            Sheet sheet = workbook.createSheet("CustomersEYYC");
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);
            List<TypeTest> typetest = tstdao.list_type_test();
            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }
            int type = 1;
            int rowIdx = 1;
            int index = 1;
            List<Test> listCustomers = ListTest;
            Customer customer = customer_dao.get_customer_detail(id + "");
            String[] test = customer.getList_test().split(",");
            for (int i = 0; i < test.length; i++) {
                for (Test item : listCustomers) {
                    if (item.getId() == Integer.parseInt(test[i].trim())) {
                        for (TypeTest list : typetest) {
                            if (type == list.getType_id()) {
                                Row row1 = sheet.createRow(rowIdx);
                                row1.createCell(0).setCellValue(list.getType_name());
                                rowIdx++;
                                type++;
                                break;
                            }
                        }
                        Row row = sheet.createRow(rowIdx);
                        row.createCell(0).setCellValue(index);
                        row.createCell(1).setCellValue(item.getName() != null ? String.valueOf(item.getName()) : "");
                        row.createCell(2).setCellValue("Lần");
                        row.createCell(3).setCellValue(1);
                        row.createCell(4).setCellValue(item.getSell_price() + "" != null ? item.getSell_price() + "" : "");
                        row.createCell(5).setCellValue(0);
                        row.createCell(6).setCellValue(0);
                        row.createCell(7).setCellValue(0);
                        rowIdx++;
                        index++;
                        total += item.getSell_price() * 1;
                    }
                }
            }

//        
//        String[] COLUMNs2 = { "", "", "","", "", "", "",""};
//        for (int col = 0; col < COLUMNs2.length; col++) {
//            Cell cell = headerRow.createCell(col);
//            cell.setCellValue(COLUMNs2[col]);
//            cell.setCellStyle(headerCellStyle);
//        }
            Row row = sheet.createRow(rowIdx);
            row.createCell(4).setCellValue(total);

            workbook.write(os);
        }
    }

    public static void main(String[] args) throws IOException, Exception {
        CustomerDAO cus = new CustomerDAO();
        TestDAO tstdao = new TestDAO();
        cus.ExportTest(tstdao.list_test(), 3012);
    }

}
