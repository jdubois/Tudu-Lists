<%@ page language="java" buffer="8kb" autoFlush="true" isThreadSafe="true" isErrorPage="false"  %>
<%@ page import="com.jamonapi.*" %>

<%@ include file="/WEB-INF/jspf/header.jsp"%>
 
<link rel="stylesheet" type="text/css" href="${ctx}/css/jamon_styles.css">

<script language="JavaScript">
<!--
    // Row highlighter
    var objClass

    function rollOnRow(obj, txt) {
        objClass = obj.className
        obj.className = "rowon";
        window.status = txt;
    }
    
    function rollOffRow(obj) {
        obj.className = objClass;
		window.status= "";
    }
// -->
</script>


<form action="JAMonAdmin.jsp" method="post">
 <table border='0' cellpadding='0' cellspacing='0' width='75%'>
  <tr>
   <td style='text-align:left;'>
    <table class='layoutmain' border='0' cellpadding='2' cellspacing='2' bgcolor='#669999'>
     <tr>
      <th><input name='Refresh' type='SUBMIT' value='Refresh'></th>
      <th><input name='Reset' type='SUBMIT' value='Reset'></th>
      <th><input name='Enable' type='SUBMIT' value='Enable'></th>
      <th><input name='Disable' type='SUBMIT' value='Disable'></th>
     </tr>
    </table>
   </td>
   <td>
   </td>
  </tr>
 </table>

<% 
String reset=request.getParameter("Reset");
String enable=request.getParameter("Enable");
String disable=request.getParameter("Disable");
String refresh=request.getParameter("Refresh");

String sortOrder=request.getParameter("sortOrder");
if  (sortOrder==null) 
  sortOrder="asc";

String sortColStr=request.getParameter("sortCol");
int sortCol=1;
if (sortColStr!=null)
  sortCol=Integer.parseInt(sortColStr);

if ("Reset".equals(reset))
    MonitorFactory.reset();
else if ("Enable".equals(enable))   
    MonitorFactory.setEnabled(true);
else if ("Disable".equals(disable)) 
    MonitorFactory.setEnabled(false);
  
%>

<%= MonitorFactory.getRootMonitor().getReport(sortCol, sortOrder) %>

</form>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
