<%
Cookie[] cookies = null;
cookies = request.getCookies();
for(Cookie cookie:cookies)
{
    if(cookie.getName().equals("LTEUsername"))
    {
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.sendRedirect("starter.jsp");
    }
}
%>