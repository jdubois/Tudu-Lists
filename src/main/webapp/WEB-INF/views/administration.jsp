<%@ page language="java" errorPage="/WEB-INF/jsp/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/header.jsp"%>

<div align="center">
 <h3><fmt:message key="administration.title"/></h3>
 <c:if test="${success eq 'true'}">
  <span class="success"><fmt:message key="form.success"/></span>
 </c:if>
 <table>
  <tr>
   <td style="width: 150px; vertical-align: top; border: 1px solid #C0C0C0">
    <div id="menuDiv" style="min-height: 400px">
     <table id="menuTable">
      <thead>
       <tr>
        <td style="font-weight: bold;">Actions</td>
       </tr>
       <tr>
        <td>
         [ <a href="${ctx}/secure/admin/administration.action?method=display&page=configuration">
          <fmt:message key="administration.menu.configuration"/>
         </a> ]
        </td>
       </tr>
       <tr>
        <td>
         [ <a href="${ctx}/secure/admin/administration.action?method=display&page=users">
          <fmt:message key="administration.menu.users"/>
         </a> ]
        </td>
       </tr>
       <tr>
        <td>
         [ <a href="${ctx}/secure/admin/administration.action?method=display&page=database">
          <fmt:message key="administration.menu.database"/>
         </a> ]
        </td>
       </tr>
      </thead>
     </table>
    </div>
   </td>
   <td style="width:10px"></td>
   <td style="width: 100%; vertical-align: top; text-align: center; border: 1px solid #C0C0C0">
    <div style="min-height: 400px" align="center">
    <c:if test="${page eq 'users'}">
      <html:form action="/secure/admin/administration">
      <p>
       <html:hidden property="page" value="users"/>
       <html:hidden property="method" value="cancel"/>
       <html:hidden property="login" value=""/>
       <fmt:message key="administration.number.of.users"/> : <b>${numberOfUsers}</b>
       <br/><br/>
       <fmt:message key="administration.user.login"/> :
       <html:text property="loginStart" size="30" maxlength="200"/>
       <html:submit onclick="document.forms[0].elements['method'].value='searchUser';">
        <fmt:message key="form.submit"/>
       </html:submit>
      </p>
      <c:if test="${not empty users}">
       <p>
        <c:set var="row" value="0"/>
        <table class="list">
         <tr>
          <th>
           <fmt:message key="user.info.login"/>
          </th>
          <th>
           <fmt:message key="user.info.first.name"/>
          </th>
          <th>
           <fmt:message key="user.info.last.name"/>
          </th>
          <th>
           <fmt:message key="administration.user.enabled"/>
          </th>
          <th>
           <fmt:message key="todos.actions"/>
          </th>
         </tr>
        <c:forEach var="user" items="${users}">
         <c:set var="row" value="${row + 1}"/>
         <c:set var="trStyle" value="${row % 2 eq 0 ? 'even' : 'odd'}"/>
         <tr class="${trStyle}">
          <td>
           ${user.login}
          </td>
          <td>
           ${user.firstName}
          </td>
          <td>
           ${user.lastName}
          </td>
          <td style="text-align: center">
           ${user.enabled}
          </td>
          <td style="text-align: center">
           <c:if test="${user.enabled}">
            <html:submit onclick="document.forms[0].elements['method'].value='disableUser';document.forms[0].elements['login'].value='${user.login}';">
             Disable
            </html:submit>
           </c:if>
           <c:if test="${not user.enabled}">
            <html:submit onclick="document.forms[0].elements['method'].value='enableUser';document.forms[0].elements['login'].value='${user.login}';">
             Enable
            </html:submit>
           </c:if>
          </td>
         </tr>
        </c:forEach>
        </table>
       </p>
      </c:if>
     </html:form> 
    </c:if>

    <c:if test="${page eq 'configuration' or page == null}">
     <html:form action="/secure/admin/administration">
     <html:errors/>
     <html:hidden property="page" value="configuration"/>
     <html:hidden property="method" value="cancel"/>
     <table class="list" style="width:450px">
      <tr>
       <th colspan="2">
        <fmt:message key="administration.configuration"/>
       </th>
      </tr>
      <tbody>
       <tr class="odd">
        <td>
         <fmt:message key="administration.configuration.static.path"/>
        </td>
        <td>
         <html:text property="propertyStaticPath" size="30" maxlength="200"/>
        </td>
       </tr>
	   <tr class="even">
        <td>
         <fmt:message key="administration.configuration.google.analytics.key"/>
        </td>
        <td>
         <html:text property="googleAnalyticsKey" size="30" maxlength="200"/>
        </td>
       </tr>
       <tr class="odd">
        <td>
         <fmt:message key="administration.configuration.email.host"/>
        </td>
        <td>
         <html:text property="smtpHost" size="30" maxlength="200"/>
        </td>
       </tr>
       <tr class="even">
        <td>
         <fmt:message key="administration.configuration.email.port"/>
        </td>
        <td>  
	     <html:text property="smtpPort" size="30" maxlength="200"/>
        </td>
       </tr>
       <tr class="odd">
        <td>
         <fmt:message key="administration.configuration.email.user"/>
        </td>
        <td>
         <html:text property="smtpUser" size="30" maxlength="200"/>
        </td>
       </tr>
       <tr class="even">
        <td>
         <fmt:message key="administration.configuration.email.password"/>
        </td>
        <td>
         <html:password property="smtpPassword" size="30" maxlength="200"/>
        </td>
       </tr>
       <tr class="odd">
        <td>
         <fmt:message key="administration.configuration.email.from"/>
        </td>
        <td>
         <html:text property="smtpFrom" size="30" maxlength="200"/>
        </td>
       </tr>
      </tbody>
     </table>
      <br/>
      <html:submit onclick="document.forms[0].elements['method'].value='updateConfiguration';">
       <fmt:message key="form.submit"/>
      </html:submit>
      <html:submit><fmt:message key="form.cancel"/></html:submit>
     </html:form>
    </c:if>
 
    <c:if test="${page eq 'database'}">
     <h3><fmt:message key="administration.database.dump"/></h3>
     <html:form action="/secure/admin/administration">
      <html:hidden property="method" value="dumpDatabase"/>
      <html:submit>
       <fmt:message key="form.submit"/>
      </html:submit>
     </html:form>
    </c:if>
   </div>
   </td>
  </tr>
 </table>
</div>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
