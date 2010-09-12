<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div id="content" style="width:500px; padding-left: 250px">
        <h1><fmt:message key="recover.password.title"/></h1>
        <form:form commandName="user">
            <c:if test="${success eq 'true'}">
                <span class="success"><fmt:message key="recover.password.success"/></span>
            </c:if>
            <c:if test="${message ne null}">
                <span class="error"><fmt:message key="${message}"/></span>
            </c:if>

            <table class="list" style="width:350px">
                <tr>
                    <th colspan="2">
                        <fmt:message key="recover.password.subtitle"/>
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
                </tr>
                <tr>
                    <td colspan="2" style="padding-left: 80px;">
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
    </div>
    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>
</html>
