<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h1><fmt:message key="login.welcome"/></h1>
        <table>
            <tr>
                <td style="width:67%; vertical-align: top; padding-right: 20px">
                    <p><fmt:message key="login.description.1"/></p>
                    <p><fmt:message key="login.description.2"/></p>
                    <div style="padding-left: 20px">
                        <p>
                            <img src="${staticContent}/images/world.png" class="icon" alt="Web"/>&nbsp;&nbsp;<fmt:message key="login.advantages.1"/>
                        </p>
                        <p>
                            <img src="${staticContent}/images/group.png" class="icon" alt="Web"/>&nbsp;&nbsp;<fmt:message key="login.advantages.2"/>
                        </p>
                        <p>
                            <img src="${staticContent}/images/feed.png" class="icon" alt="Web"/>&nbsp;&nbsp;<fmt:message key="login.advantages.3"/>
                        </p>
                        <p>
                            <img src="${staticContent}/images/cart.png" class="icon" alt="Web"/>&nbsp;&nbsp;<fmt:message key="login.advantages.4.1"/>
                            <a href="${context}/tudu/register"><fmt:message key="login.advantages.4.link"/></a>
                            <fmt:message key="login.advantages.4.2"/>
                        </p>
                        <p>
                            <img src="${staticContent}/images/page_white_wrench.png" class="icon" alt="Web"/>&nbsp;&nbsp;<fmt:message key="login.advantages.5"/>
                        </p>
                    </div>
                </td>
                <td style="width:33%; vertical-align: top; border-left: #cccccc solid 1px;">
                    <h3><fmt:message key="login.title"/></h3>
                    <c:if test="${not empty param.login_error}">
                        <div class="error">
                            <fmt:message key="login.error.title"/>
                        </div>
                        <br/>
                    </c:if>
                    <form action="${context}/tudu/login" method="post">
                        <c:if test="${authentication eq 'failure'}">
                            <div class="error"><fmt:message key="authentication.failure"/></div>
                        </c:if>
                        <table border="0" cellspacing="2" cellpadding="3">
                            <tr>
                                <th style="text-align: left"><fmt:message key="login.login"/></th>
                                <td style="text-align: left">
                                    <input type="text"
                                           name="j_username"
                                           size="20"
                                           maxlength="50"
                                            />
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align: left"><fmt:message key="login.password"/></th>
                                <td style="text-align: left"><input type="password" name="j_password" size="20"
                                                                    maxlength="50"/></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <p>
                                        <fmt:message key="login.auto.login"/>
                                        <input type="checkbox" name="_spring_security_remember_me"/>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <th colspan="2" style="padding-left: 40px;">
                                    <a class="button" href="javascript:submitForm();"
                                        onclick="this.blur();"><span><fmt:message key="login.submit"/></span></a>
                                    <a class="button" href="javascript:resetForm();"
                                        onclick="this.blur();"><span><fmt:message key="login.reset"/></span></a>
                                </th>
                            </tr>
                        </table>
                    </form>
                    <p>
                        <fmt:message key="login.register.1"/>
                        <a href="${context}/tudu/register"><fmt:message key="login.register.link"/></a><fmt:message
                            key="login.register.2"/>
                    </p>
                    <p>
                        <fmt:message key="login.forgotten.password.1"/>
                        <a href="${context}/tudu/recoverPassword"><fmt:message key="login.forgotten.password.link"/></a>
                        <fmt:message key="login.forgotten.password.2"/>
                    </p>
                </td>
            </tr>
        </table>
    <jsp:include page="../fragments/footer.jsp"/>
</body>
</html>