/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.rfl;


import dal.RequestForLeaveDBContext;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import model.accesscontrol.LeaveRequest;
import model.accesscontrol.User;

@WebServlet("/request/list")
public class RequestListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        RequestForLeaveDBContext dao = new RequestForLeaveDBContext();
        List<LeaveRequest> list = dao.getRequestsForUser(user);

        req.setAttribute("list", list);
        req.getRequestDispatcher("/view/request_list.jsp").forward(req, resp);
    }
}