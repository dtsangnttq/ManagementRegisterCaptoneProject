/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import capstone.CapstoneDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import upload.UploadDAO;

/**
 *
 * @author Mai
 */
@WebServlet(name = "ImportCapstoneController", urlPatterns = {"/ImportCapstoneController"})
public class ImportCapstoneController extends HttpServlet {

    private static String SUCCESS = "GetListTopicController";
    private static String ERROR = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            String filename = request.getParameter("filename");
            String locationFileName = "C:\\" + filename;
            UploadDAO dao = new UploadDAO();
            int check = dao.readFile_Capstone(locationFileName);
            if (check == 1) {
                List<CapstoneDTO> list = dao.getListCapstone();
                boolean checkInsertCapstone = dao.pushExcelListCap(list);
                boolean checkInsertUserCapstone = dao.pushExcelListUserCap(list);
                if (checkInsertCapstone && checkInsertUserCapstone) {
                    url = SUCCESS;
                }
            }

        } catch (Exception e) {
            log("Error at ImportController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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
