package ua.uipa.math.web.servlet.students;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;

public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String[] INFO_UA = {"Корисні посилання:", "Дистанційна освіта Української інженерно-педагогічної академії"};
	public static final String[] INFO_RU = {"Полезные ссылки:", "Дистанционное образование Украинской инженерно-педагогической академии"};
	public static final String[] INFO_EN = {"Selected links:", "Distance education of Ukrainian Engineering Pedagogics Academy"};
	public static final String LINK = "http://do.uipa.edu.ua/course/index.php?categoryid=12";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("students");
		} else
			getStudentRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getStudentRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(Template.setTitle(getTitleValue(request)));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		out.print(setStudentInfo(request));
		out.print(Template.setFooter());
		out.close();
	}

	private String setStudentInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = getStudentsHull(INFO_RU);
			break;
		case "en":
			res = getStudentsHull(INFO_EN);
			break;
		default:
			res = getStudentsHull(INFO_UA);
		}
		return res;
	}

	private String getStudentsHull(String[] info) {
		return "<div class='Information'><p align='justify' class='Name'>" + info[0] + "</p>" +
				"<p align='justify' class='Name'><a href=" + LINK + ">" + info[1] + "</a></p></div>";
	}

	private String getTitleValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = Template.TITLE_STUDENTS_RU;
			break;
		case "en":
			res = Template.TITLE_STUDENTS_EN;
			break;
		default:
			res = Template.TITLE_STUDENTS_UA;
		}
		return res;
	}
}
