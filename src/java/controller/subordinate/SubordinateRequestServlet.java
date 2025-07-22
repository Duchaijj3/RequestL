/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.subordinate;

/**
 *
 * @author saiki
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

// =========================
// controller/SubordinateRequestServlet.java (FIX chính xác)
// =========================


import dal.RequestForLeaveDBContext;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.accesscontrol.LeaveRequest;
import model.accesscontrol.User;

@WebServlet("/request/subordinates")
public class SubordinateRequestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        if (u == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        RequestForLeaveDBContext dal = new RequestForLeaveDBContext();
        List<LeaveRequest> list = null; // list rong truoc demo

        // Nếu là admin: lấy đơn của tất cả user có role != admin
        if (u.getRole().equalsIgnoreCase("admin")) {
            try {
                list = dal.getRequestsOfAllNonAdmins();
            } catch (Exception ex) {
                Logger.getLogger(SubordinateRequestServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Nếu là leader: lấy đơn của nhân viên cấp dưới
        else if (u.getRole().equalsIgnoreCase("leader")) {
            try {
                list = dal.getRequestsOfSubordinates(u.getUserId());
            } catch (Exception ex) {
                Logger.getLogger(SubordinateRequestServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Nếu là nhân viên: chuyển về trang của họ
        else {
            response.sendRedirect("home.jsp");
            return;
        }

        request.setAttribute("list", list);
        request.getRequestDispatcher("/view/subordinate_requests.jsp").forward(request, response);
    }
}