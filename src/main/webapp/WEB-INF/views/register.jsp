<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8"
         contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>Tudu Lists</title>
    <jsp:include page="../fragments/header_head.jsp"/>
</head>
<body id="main">
<div id="banner"></div>
<div id="container">
    <jsp:include page="../fragments/header_body.jsp"/>
    <div id="content" style="width:500px; padding-left: 200px">
        <h1><fmt:message key="register.title"/></h1>

        <div>
            <ul>
                <li>
                    <fmt:message key="register.info.1"/>
                </li>
                <li>
                    <fmt:message key="register.info.2"/>
                </li>
            </ul>
        </div>
        <form:form commandName="user">
            <table class="list" style="width:450px">
                <tr>
                    <th colspan="3">
                        <fmt:message key="register.subtitle"/>
                    </th>
                </tr>
                <tbody>
                <tr class="odd">
                    <td>
                        <fmt:message key="user.info.login"/> *
                    </td>
                    <td>
                        <form:input path="login" size="20" maxlength="50"/>
                    </td>
                    <td>
                        <form:errors path="login" />
                    </td>
                </tr>
                <tr class="even">
                    <td>
                        <fmt:message key="user.info.first.name"/> *
                    </td>
                    <td>
                        <form:input path="firstName" size="15" maxlength="60"/>
                    </td>
                    <td>
                        <form:errors path="firstName"/>
                    </td>
                </tr>
                <tr class="odd">
                    <td>
                        <fmt:message key="user.info.last.name"/> *
                    </td>
                    <td>
                        <form:input path="lastName" size="15" maxlength="60"/>
                    </td>
                    <td>
                        <form:errors path="lastName"/>
                    </td>
                </tr>
                <tr class="even">
                    <td>
                        <fmt:message key="user.info.email"/>
                    </td>
                    <td>
                        <form:input path="email" size="25" maxlength="100"/>
                    </td>
                    <td>
                    </td>
                </tr>
                <tr class="odd">
                    <td>
                        <fmt:message key="user.info.password"/> *
                    </td>
                    <td>
                        <form:password path="password" size="15" maxlength="32"/>
                    </td>
                    <td>
                        <form:errors path="password"/>
                    </td>
                </tr>
                <tr class="even">
                    <td>
                        <fmt:message key="user.info.verifypassword"/> *
                    </td>
                    <td>
                        <form:password path="verifyPassword" size="15" maxlength="32"/>
                    </td>
                    <td>
                        <form:errors path="verifyPassword"/>
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
            <br/>
        </form:form>
    </div>
    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>
</html>
