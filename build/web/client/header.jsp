<%-- 
    Document   : header
    Created on : Mar 15, 2020, 12:03:46 PM
    Author     : Khanh Xinh Tuoi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" rel="stylesheet" href="./css/header.css">
<div class="header">
    <h1>The SuShi Restaurant</h1>
    <span>Welcome to this website</span>
</div>
<div class="menu">
    <a id="Home" href="home?currentPage=1" class="
       ${check eq 'home'?"setBold":""}
       ">Home</a>
    <a id="Menu" href="menu?currentPage=1" class="
       ${check eq 'menu'?"setBold":""}
       ">Menu and Price list</a>
    <a id="Info" href="find" class="
       ${check eq 'find'?"setBold":""}
       ">Find us</a>
</div>