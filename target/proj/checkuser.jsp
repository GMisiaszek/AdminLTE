<%@page import="MyPackage.UserDAO"%>
<jsp:useBean id="usr" class="MyPackage.UserBean"/>
<jsp:setProperty property="*" name="usr"/>

<%
String userName = "";
userName = UserDAO.getUser(usr.getEmail(),usr.getPassword());
if(!userName.equals(""))
{
    Cookie LTEUserName = new Cookie("LTEUsername",userName.replaceAll("\\s+",""));
    response.addCookie(LTEUserName);

    response.sendRedirect("starter.jsp");
}
else
{
    response.sendRedirect("notadded.jsp");
}
%>