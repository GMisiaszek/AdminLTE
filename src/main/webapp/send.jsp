<%@page import="MyPackage.UserDAO"%>
<jsp:useBean id="user" class="MyPackage.UserBean"/>
<jsp:setProperty property="*" name="user"/>

<%
int i = UserDAO.addUser(user);
if(i>0)
{
    response.sendRedirect("added.jsp");
}
else
{
    response.sendRedirect("notadded.jsp");
}
%>