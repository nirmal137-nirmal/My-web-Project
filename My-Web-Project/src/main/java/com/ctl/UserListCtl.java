package com.ctl;



import java.io.IOException;
import java.util.List;

import com.bean.UserBean;
import com.model.UserModel;
import com.util.ServletUtility;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserListCtl.do")
public class UserListCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		int pageNo = 1;
		int pageSize = 5;

		List<UserBean> list = model.search(bean, pageNo, pageSize);
		System.out.println("list size = " + list.size());

		request.setAttribute("list", list);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);

		/*
		 * RequestDispatcher rd = request.getRequestDispatcher("UserListView.jsp");
		 * rd.forward(request, response);
		 */
		ServletUtility.forward("UserListView.jsp", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		int pageNo = 1;
		int pageSize = 5;

		if (op.equals("delete")) {
			String[] ids = request.getParameterValues("ids");

			if (ids != null && ids.length > 0) {

				for (String id : ids) {

					try {
						model.Delete(Integer.parseInt(id));
						request.setAttribute("succMsg", "Record deleted successfully");

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				request.setAttribute("errorMsg", " Please select at least one record to delete");
			}
		}

		if (op.equals("previous")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageNo--;
		}

		if (op.equals("next")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageNo++;
		}

		List<UserBean> list = model.search(bean, pageNo, pageSize);
		System.out.println("list size = " + list.size());

		request.setAttribute("list", list);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);

		/*
		 * RequestDispatcher rd = request.getRequestDispatcher("UserListView.jsp");
		 * rd.forward(request, response);
		 */
		
		ServletUtility.forward("UserListView.jsp", request, response);

	}

}