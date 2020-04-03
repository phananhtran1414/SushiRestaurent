<%-- 
    Document   : menu
    Created on : Mar 16, 2020, 11:33:26 AM
    Author     : Khanh Xinh Tuoi
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Page</title>
       <link type="text/css" rel="stylesheet" href="./css/layout.css">
    </head>
    <body >
        <div id="mainBox" class="fontCalibri">
        <!--<div id="menu" data-page="Menu"></div>-->
            <%@include file="header.jsp" %>
            <div class="content">
                <div class="main-content">
                    <div class="content-left">
                        <h2>Menu and Price list</h2>
                        
                        <c:if test="${noProduct eq true}">
                        <h3>No Product</h3>                     
                        </c:if>
                        
                        <c:forEach var="i" items="${arrMenu}">
                            <div class="menu-container">
                                <div class="order">
                                    <span>Menu ${i.getId()}</span>
                                    <span class="float-right">Price</span>
                                </div>
                                <div class="title">
                                    <span>${i.getName()}</span>
                                    <span class="float-right">$${i.getPrice()}</span>
                                </div>
                                <div class="descriptment">
                                    <span>${i.getContent()}</span>
                                </div>
                            </div>
                        </c:forEach>              
                        
                        <div id="numberPagination">
                            <c:forEach begin="1" end="${maxPage}" var="i">
                                <a class="${currentPage==i?"activation":"deactivation"}" href="menu?currentPage=${i}">${i}</a>
                            </c:forEach>
                        </div>
                        
                    </div>
                    <%@include file="adv.jsp" %>
                </div>
                <%@include file="footer.jsp" %>
            </div>
        

        </div>
    </body>
</html>

