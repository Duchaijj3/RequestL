<%-- 
    Document   : list
    Created on : Jun 25, 2025, 9:17:38 AM
    Author     : p14s
--%>
<%@ page import="java.util.List" %>
<%@ page import="model.LeaveRequest" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    List<LeaveRequest> list = (List<LeaveRequest>) request.getAttribute("list");
    User user = (User) session.getAttribute("user");
%>

<html>
<head>
    <title>Danh sách đơn nghỉ phép</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>
<h2>Danh sách đơn nghỉ phép</h2>
<table border="1" cellpadding="6">
    <tr>
        <th>STT</th>
        <th>Người tạo</th>
        <th>Từ ngày</th>
        <th>Đến ngày</th>
        <th>Lý do</th>
        <th>Trạng thái</th>
        <th>Thao tác</th>
    </tr>

<%
    int i = 1;
    for (LeaveRequest r : list) {
%>
    <tr>
        <td><%= i++ %></td>
        <td><%= r.getCreatorName() %></td>
        <td><%= r.getFromDate() %></td>
        <td><%= r.getToDate() %></td>
        <td><%= r.getReason() %></td>
        <%
            String statusText = "Không rõ";
            if (r.getStatusId() == 1) {
                statusText = "Đang xử lý";
            } else if (r.getStatusId() == 2) {
                statusText = "Đã duyệt";
            } else if (r.getStatusId() == 3) {
                statusText = "Từ chối";
            }
        %>
        <td><%= statusText %></td>
        <td>
            <% if (r.getUserId() == user.getUserId() && r.getStatusId() == 1) { %>
                <a href="${pageContext.request.contextPath}/request/modif?id=<%= r.getRequestId() %>">✏️ Sửa</a>
            <% } %>
        </td>
    </tr>
<% } %>
</table>

<br>
<a href="${pageContext.request.contextPath}/home">Trang chủ</a>
</body>
</html>