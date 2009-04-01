<%@ page language="java" errorPage="/WEB-INF/jsp/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/header.jsp"%>

<div align="center">
 <h3><fmt:message key="register.ok.title"/></h3>
 <p style="text-align:center">
  <fmt:message key="register.ok.text.1">
   <fmt:param value="${login}"/>
  </fmt:message>
  <a href="${ctx}/welcome.action"><fmt:message key="register.ok.text.link"/></a>
  <fmt:message key="register.ok.text.2"/>
 </p>
</div>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
