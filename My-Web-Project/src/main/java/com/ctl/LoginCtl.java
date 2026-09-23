package com.ctl;

import java.io.IOException;

import com.bean.UserBean;
import com.model.UserModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginCtl")
public class LoginCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		if (op != null) {
			HttpSession session = request.getSession();
			session.invalidate();
		}
		RequestDispatcher rd = request.getRequestDispatcher("LoginView.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		String loginid = request.getParameter("login"); // get kiya parameter ko 
		String password = request.getParameter("password");
		HttpSession session = request.getSession();

		try {

			bean = model.authenticate(loginid, password);

			if (bean != null) {
				session.setAttribute("user", bean); // user yaha key hai or bean yaha value hai
				response.sendRedirect("WelcomeCtl"); // sendRedirect method is used to generate to new request. 
				return; 							// or by default do get method run hoti hai to 
				// request.setAttribute("succMsg", "Login Successfull");

			} else {
				request.setAttribute("errorMsg", "Invalid Login or Password");
			}

		} catch (Exception e) {
			// e.printStackTrace();
			// request.setAttribute("err", e.getMessage());
		}

		RequestDispatcher rd = request.getRequestDispatcher("LoginView.jsp");
		rd.forward(request, response);
	}

}
