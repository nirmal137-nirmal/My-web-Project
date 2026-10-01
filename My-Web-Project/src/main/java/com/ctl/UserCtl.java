package com.ctl;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.bean.UserBean;
import com.model.UserModel;
import com.util.ServletUtility;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/UserCtl.do")
public class UserCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("in do get method");

		/*
		 * RequestDispatcher rd = request.getRequestDispatcher("UserView.jsp");
		 * rd.forward(request, response); // forward method used to forward same request
		 * to it's own view
		 */
		ServletUtility.forward("UserView.jsp", request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String dob = request.getParameter("dob");

//		System.out.println(firstName + "\n" + lastName + "\n" + login + "\n" + password + "\n" + dob);

		try {

			bean.setFirstName(firstName);
			bean.setLastName(lastName);
			bean.setLoginId(login);
			bean.setPassword(password);
			bean.setDob(sdf.parse(dob));

			model.add(bean);
			request.setAttribute("succMsg", "user saved successfully");

		} catch (Exception e) {
			//request.setAttribute("errorMsg", e.getMessage());
			ServletUtility.setErrorMessage(e.getMessage(), request);
			e.printStackTrace();
		}

		/*
		 * RequestDispatcher rd = request.getRequestDispatcher("UserView.jsp");
		 * rd.forward(request, response);
		 */

		ServletUtility.forward("UserView.jsp", request, response);
	}

}
