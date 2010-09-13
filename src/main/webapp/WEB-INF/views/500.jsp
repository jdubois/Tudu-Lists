<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" isErrorPage="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Erreur technique!</title>
    <jsp:include page="../fragments/header_head.jsp"/>
</head>
<body id="main">
<div id="banner"></div>
<div id="container">
    <jsp:include page="../fragments/header_body.jsp"/>
    <div id="content">
        <%
            if (exception != null) {
                exception.printStackTrace();
            }
        %>
        <h1>Une erreur s'est produite</h1>
        <p style="text-align: center;">
            L'&eacute;quipe technique de Responcia a &eacute;t&eacute; pr&eacute;venue et va r&eacute;soudre
            ce probl&egrave;me le plus rapidement possible.
            <br/>
            <br/>
            [ <a href="${context}/">Retour &agrave; la page principale de Responcia</a> ]
        </p>
    </div>
    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>
</html>
