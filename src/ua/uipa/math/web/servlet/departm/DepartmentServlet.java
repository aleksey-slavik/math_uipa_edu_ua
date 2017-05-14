package ua.uipa.math.web.servlet.departm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;

public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("department");
		} else
			getDepartmentRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getDepartmentRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(Template.setTitle(getTitleValue(request)));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		out.print(setDepartmentInfo(request));
		out.print(Template.setFooter());
		out.close();
	}

	private String setDepartmentInfo(HttpServletRequest request) {
		return getDepartmentHull(request);
	}

	private String getDepartmentHull(HttpServletRequest request) {
		return "<script type='text/javascript' src='//use.typekit.net/vue1oix.js'></script>"
				+ "<script type='text/javascript'>try{Typekit.load();}catch(e){}</script>"
				+ "<script src='script/modernizr.js'></script>" + "<script src='script/jquery.js'></script>"
				+ "<script src='script/jquery.pictureflip.js'></script>" + "<script type='text/javascript'>"
				+ "$(document).ready(function() {" + "$('#flipbook').pictureflip({time : 1000, start : 1});" + "});"
				+ "</script>" + "<div id='container' class='tk-chaparral-pro'>" + "<div id='flipbook'>"
				+ "<div id='image-1'></div>" + "<div id='image-2'></div>" + "<div id='image-3'></div>" + "</div>"
				+ "</div>";
	}

	private String getTitleValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = Template.TITLE_DEPARTMENT_RU;
			break;
		case "en":
			res = Template.TITLE_DEPARTMENT_EN;
			break;
		default:
			res = Template.TITLE_DEPARTMENT_UA;
		}
		return res;
	}
}
