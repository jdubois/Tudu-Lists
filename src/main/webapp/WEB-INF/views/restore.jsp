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
<h3><fmt:message key="restore.title"/></h3>
<form:form commandName="restoreTodoListModel" enctype="multipart/form-data">
    <form:errors/>
    <form:hidden path="listId"/>
    <table class="list" style="width:600px">
        <tr>
            <th colspan="2">
                <fmt:message key="restore.subtitle"/>
            </th>
        </tr>
        <tbody>
        <tr class="odd">
            <td>
                <fmt:message key="restore.file"/>
            </td>
            <td>
                <br/>
                <input type="file" name="backupFile"/>
                <br/><form:errors path="backupFile" cssClass="error"/><br/>
            </td>
        </tr>
        <tr class="even">
            <td>
                <fmt:message key="restore.choice"/>
            </td>
            <td>
                <br/>
                <form:radiobutton path="restoreChoice" value="create"/>
                <fmt:message key="restore.choice.create"/>
                <br/><br/>
                <form:radiobutton path="restoreChoice" value="replace"/>
                <fmt:message key="restore.choice.replace">
                    <fmt:param value="${todoList.name}"/>
                </fmt:message>
                <br/><br/>
                <form:radiobutton path="restoreChoice" value="merge"/>
                <fmt:message key="restore.choice.merge">
                    <fmt:param value="${todoList.name}"/>
                </fmt:message>
                <br/><form:errors path="restoreChoice" cssClass="error"/><br/>
            </td>
        </tr>
         <tr>
            <td colspan="2" style="padding-left: 250px;">
                <br/>
                <a class="button" href="javascript:submitForm();"
                   onclick="this.blur();"><span><fmt:message key="form.submit"/></span></a>
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
