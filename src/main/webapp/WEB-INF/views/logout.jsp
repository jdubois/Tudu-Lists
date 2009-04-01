<%@ page language="java" errorPage="/WEB-INF/jsp/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/header.jsp"%>

<h3><fmt:message key="logout.title"/></h3>

<div align="center">
 <fmt:message key="logout.description"/>
 <br/>
 <br/>
 <a href="${ctx}/welcome.action"><fmt:message key="logout.reconnect"/></a>
</div>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
