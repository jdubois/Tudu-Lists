<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Page non trouv&eacute;e</title>
    <jsp:include page="../fragments/header_head.jsp"/>
</head>
<body id="main">
<div id="banner"></div>
<div id="container">
    <jsp:include page="../fragments/header_body.jsp"/>
    <div id="content">
        <h1>Page non trouv&eacute;e!</h1>
        <p style="text-align: center;">
            La page que vous avez demand&eacute;e n'a pas &eacute;t&eacute; trouv&eacute;e.
            <br/>
            <br/>
            [ <a href="${context}/">Retour &agrave; la page principale de Responcia</a> ]
        </p>
    </div>
    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>
</html>
