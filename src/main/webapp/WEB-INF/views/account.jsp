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
<c:if test="${updated eq 'ok'}">
    <h1><fmt:message key="common.update.ok"/></h1>
</c:if>
<h3><fmt:message key="user.info.title"/></h3>
<form:form commandName="user">
    <table class="list" style="width:600px">
        <tr>
            <th colspan="3">
                <fmt:message key="user.info.subtitle"/>
            </th>
        </tr>
        <tbody>
        <tr class="odd">
            <td style="width: 200px;">
                <fmt:message key="user.info.first.name"/>
            </td>
            <td>
                <form:input path="firstName" size="15" maxlength="60"/>
            </td>
            <td>
                <form:errors path="firstName" cssClass="error"/>
            </td>
        </tr>
        <tr class="even">
            <td>
                <fmt:message key="user.info.last.name"/>
            </td>
            <td>
                <form:input path="lastName" size="15" maxlength="60"/>
            </td>
            <td>
                <form:errors path="lastName" cssClass="error"/>
            </td>
        </tr>
        <tr class="odd">
            <td>
                <fmt:message key="user.info.email"/>
            </td>
            <td>
                <form:input path="email" size="25" maxlength="100"/>
            </td>
            <td>
                <form:errors path="email" cssClass="error"/>
            </td>
        </tr>
        <tr class="even">
            <td>
                <fmt:message key="user.info.date.format"/>
            </td>
            <td>
                <form:select path="dateFormat">
                    <form:option value="MM/dd/yyyy">mm/dd/yyyy</form:option>
                    <form:option value="MM/dd/yy">mm/dd/yy</form:option>
                    <form:option value="dd/MM/yyyy">dd/mm/yyyy</form:option>
                    <form:option value="dd/MM/yy">dd/mm/yy</form:option>
                </form:select>
            </td>
            <td></td>
        </tr>
        <tr class="odd">
            <td>
                <fmt:message key="user.info.password"/>
            </td>
            <td>
                <form:password path="password" size="15" maxlength="32"/>
            </td>
            <td>
                <form:errors path="password" cssClass="error"/>
            </td>
        </tr>
        <tr class="even">
            <td>
                <fmt:message key="user.info.verifypassword"/>
            </td>
            <td>
                <form:password path="verifyPassword" size="15" maxlength="32"/>
            </td>
            <td>
                <form:errors path="verifyPassword" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td colspan="3" style="padding-left: 250px;">
                <br/>
                <a class="button" href="javascript:submitForm();"
                   onclick="this.blur();"><span><fmt:message key="form.submit"/></span></a>
                <br/><br/>
            </td>
        </tr>
        </tbody>
    </table>
</form:form>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>