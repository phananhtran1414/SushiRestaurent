/*
 * HomeController.java
 * 
 * All Rights Reserved.
 * Copyright (c) 2020 FPT University
 */
package controller;

import dal.DetailDAO;
import dal.InfoDAO;
import dal.NewsDAO;
import entity.News;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HomeController class.<br>
 * 
 * <pre>
 * Class này là 1 servlet thực hiện lấy các request
 *              và dữ liệu cần thiết để đẩy lên home.jsp để xử lý
 * Trong class này sẽ tiến hành các xử lí dưới đây
 * 
 * ・processRequest()
 * 
 *
 * </pre>
 * 
 * @author FU AnhTHPHE130193
 * @version 1.0
 */ 

@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.<br/>
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
            
            if(request.getQueryString() == null){
                response.sendRedirect("home?currentPage=1");
            }
            
            boolean noProduct = false;
            String text = (String) request.getParameter("currentPage");
            if(text == null){
                text = "1";
            }
            
            if(!new DetailDAO().checkInt(text)){
                response.sendRedirect("error");
            }
            
            int currentPage = Integer.parseInt(text);
            if (currentPage > 0) {
                NewsDAO homeDao = new NewsDAO();
                InfoDAO infoDao = new InfoDAO();
                
                //int totalPage = homeDao.getTotalPages();
                List<News> arrHome = homeDao.getDataList(currentPage);
                if (arrHome.isEmpty()) {
                    noProduct = true;
                }
                
                request.setAttribute("maxPage", homeDao.getTotalPages());
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("noProduct", noProduct);
                request.setAttribute("arrHome", arrHome);
                request.setAttribute("urlCover", infoDao.getInforFindPage().get("imgHome"));
                request.setAttribute("check", "home");
                
                request.getRequestDispatcher("./client/home.jsp").forward(request, response);
            } else{
                response.sendRedirect("error");
            }
        }catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
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
