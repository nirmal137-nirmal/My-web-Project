package com.ctl;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//doGet method handle HTTP GET request (HTTP GET request is a default request)
//doPost method handle HTTP POST request (When you submit request with parameter and form data when call HTTP POST request)

@WebServlet("/WelcomeCtl") //Wildcard Mapping 
public class WelcomeCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("WelcomeView.jsp");
		rd.forward(request, response); // forward method used to forward same request to it's own view

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("WelcomeView.jsp");
		rd.forward(request, response);
	}

}
