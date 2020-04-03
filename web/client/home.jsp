<%-- 
    Document   : home
    Created on : Mar 15, 2020, 12:04:03 PM
    Author     : Khanh Xinh Tuoi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HomePage</title>
        <link type="text/css" rel="stylesheet" href="./css/layout.css">
    </head>
    <body>
        <div id="mainBox" class="fontCalibri">       
            <%@include file="header.jsp" %>
            <div class="content">
                <div class="main-content">
                    <div class="content-left">
                        <div class="img-border">
                            <img src="${urlCover}" alt="">
                        </div>
                        
                        <c:if test="${noProduct eq true}">
                        <h3>No Product</h3>                     
                        </c:if>
                        
                        <c:forEach var="i" items="${arrHome}">
                            <div class="border-post">
                                <span><a href="detail?id=${i.getId()}">${i.getTitle()}</a></span>
                                <div class="post">
                                    <div class="left-post">
                                        <a href="detail?id=${i.getId()}"><img src="${i.getImgLink()}" alt=""></a>
                                    </div>
                                    <div class="right-post">
                                        <p>${i.getContent()}</p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        
                        <div id="numberPagination">
                            <c:forEach begin="1" end="${maxPage}" var="i">
                                <a class="${i==currentPage?"activation":"deactivation"}" href="home?currentPage=${i}">${i}</a>
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
