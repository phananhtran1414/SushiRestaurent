<%-- 
    Document   : error
    Created on : Mar 15, 2020, 12:03:19 PM
    Author     : Khanh Xinh Tuoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <link type="text/css" rel="stylesheet" href="./css/layout.css">
    </head>
    <body>
        <div id="mainBox">
            <%@include file="header.jsp" %>
            <div class="content">
                <div class="main-content">
                    <div class="content-left">
                        <h3>Error !!! Please click <a href="home">here</a> to go back home page.</h3>
                    </div>
                    <%@include file="adv.jsp" %>
                </div>
            <%@include file="footer.jsp" %> 
            </div>
            
        </div>
    </body>
</html>
