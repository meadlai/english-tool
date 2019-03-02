package com.meadvoa.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Print", urlPatterns = "/print")
public class Print extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String text = "Sorry, get nothing :(";
		String lang = request.getParameter("lang");
		String url = request.getParameter("url");
		if(url == null) {
			out.println("Please enter the url!");
			return;
		}
		switch (lang) {
		case "zh":
			try {
				text = HTMLExtract.handleDetail(url, 1, 1);
			} catch (Exception e) {
			}
			break;
		case "en":
			try {
				text = HTMLExtract.handleDetail(url, 0, 1);
			} catch (Exception e) {
			}
			break;
		default:
			try {
				text = HTMLExtract.handleDetail(url, 2, 1);
			} catch (Exception e) {
			}
			break;
		}
		
		out.println(text);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
