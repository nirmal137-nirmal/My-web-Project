package com.ctl;

import java.io.IOException;

import com.util.ServletUtility;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("*.do")
public class FrontCtl implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		HttpSession session = request.getSession();

		if (session.getAttribute("user") == null) {
			request.setAttribute("errorMsg", "you session has been expired please re-login :(");
			/*
			 * RequestDispatcher rd = request.getRequestDispatcher("LoginView.jsp");
			 * rd.forward(request, response);
			 */
			ServletUtility.forward("LoginView.jsp", request, response);

		} else {
			chain.doFilter(request, response); // call next filter or controller in the chain if session.user exist.
		}

	}

	@Override
	public void destroy() {

	}

}