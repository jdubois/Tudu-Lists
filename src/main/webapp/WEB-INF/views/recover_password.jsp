<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8"
         contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
    <title>Tudu Lists</title>
    <jsp:include page="../fragments/header_head.jsp"/>
</head>
<body id="main">
<jsp:include page="../fragments/header_body.jsp"/>
<h1><fmt:message key="recover.password.title"/></h1>
<form:form commandName="user">
    <table class="list" style="width:450px">
        <tr>
            <th colspan="3">
                <fmt:message key="recover.password.subtitle"/>
            </th>
        </tr>
        <tbody>
        <tr class="odd">
            <td style="width: 150px;">
                <fmt:message key="user.info.login"/> *
            </td>
            <td>
                <form:input path="login" size="20" maxlength="50"/>
            </td>
            <td>
                <form:errors path="login" cssClass="error" htmlEscape="false"/>
            </td>
        </tr>
        <tr>
            <td colspan="3" style="padding-left: 150px;">
                <br/>
                <a class="button" href="javascript:submitForm();"
                   onclick="this.blur();"><span><fmt:message key="form.submit"/></span></a>
                <a class="button" href="javascript:resetForm();"
                   onclick="this.blur();"><span><fmt:message key="form.reset"/></span></a>
                <br/><br/>
            </td>
        </tr>
        </tbody>
    </table>
    <br/>
</form:form>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
