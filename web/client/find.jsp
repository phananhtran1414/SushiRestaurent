<%-- 
    Document   : find
    Created on : Mar 16, 2020, 11:33:13 AM
    Author     : Khanh Xinh Tuoi
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Find us</title>
        <link type="text/css" rel="stylesheet" href="./css/layout.css">
    </head>
    <body>
        <div id="mainBox" class="fontCalibri">
        <!--<div id="menu" data-page="Info"></div>-->  
        <%@include file="header.jsp" %>
            <div class="content">
                <div class="main-content">
                    <div class="content-left">
                        <h2>Find us</h2>
                        <div class="info">
                            <div class="contact">
                                <h3>Address and Contact</h3>
                                <p>${info["address"]}</p>
                            </div>
                            <div class="tel-mail">
                                <p>
                                    <span>Tel: </span>
                                    ${info["tel"]}
                                </p>
                                <p>
                                    <span>Email: </span>
                                    ${info["mail"]}
                                </p>
                            </div>
                        </div>
                        <div class="opening">
                            <h3>Opening Hours</h3>
                            <p>${info["Monday"]} </p>
                            <p>${info["Tuesday"]} </p>
                            <p>${info["Wednesday"]} </p>
                            <p>${info["Thursday"]} </p>
                            <p>${info["Friday"]} </p>
                            <p>${info["Saturday"]} </p>
                            <p>${info["Sunday"]} </p>
                        </div>
                        <div class="map">
                            <div class="border-map">
                                <img src="${info["imgMap"]}" />
                            </div>
                        </div>
                    </div>
                    <%@include file="adv.jsp" %>
                </div>
                <%@include file="footer.jsp" %>
            </div>
        
  
        </div>
    </body>
</html>