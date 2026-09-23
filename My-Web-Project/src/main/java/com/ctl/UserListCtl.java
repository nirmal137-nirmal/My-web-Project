package com.ctl;

import java.io.IOException;
import java.util.List;

import com.bean.UserBean;
import com.model.UserModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserListCtl")
public class UserListCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		int pageNo = 1;
		int pageSize = 5;
		
		List<UserBean> list = model.search(bean, pageNo, pageSize);
		
		System.out.println("list size =" + list.size());
		
		request.setAttribute("list", list);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);

		RequestDispatcher rd = request.getRequestDispatcher("UserListView.jsp");
		rd.forward(request, response);
		

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
