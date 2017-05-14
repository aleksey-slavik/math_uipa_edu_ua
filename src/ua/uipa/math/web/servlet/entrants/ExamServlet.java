package ua.uipa.math.web.servlet.entrants;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;

public class ExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String[] INFO_UA = {
			"Для абітурієнтів фахових спеціальностей передбачається складання фахового іспиту. Для абітурієнтів не фахових спеціальностей передбачається складання окрім фахового іспиту ще і додаткового.",
			"Програма, характеристика та критерії оцінювання завдань фахового вступного випробування",
			"Програма, характеристика та критерії оцінювання завдань додаткового вступного випробування",
			"До переліку фахових спеціальностей відносяться:",
			"6(7).040201 Математика;",
			"6(7).040202 Механіка;",
			"6(7).040205 Статистика;",
			"6(7).040301 Прикладна математика;",
			"6(7).040302 Інформатика."
	};
	private static String[] INFO_RU = {
			"Для абитуриентов профильных специальностей предусматривается проведение профильного экзамена. Для абитуриентов не профильных специальностей предусматривается проведение помимо профильного экзамена еще и дополнительного.",
			"Программа, характеристика и критерии оценивания заданий профильного вступительного экзамена",
			"Программа, характеристика и критерии оценивания заданий дополнительного вступительного экзамена",
			"К перечню профильных специальностей относятся:",
			"6(7).040201 Математика;",
			"6(7).040202 Механика;",
			"6(7).040205 Статистика;",
			"6(7).040301 Прикладна математика;",
			"6(7).040302 Информатика."
	};
	private static String[] INFO_EN = {
			"For applicants professional specialties supposed compilation professional exam. For applicants not supposed professional specialties assembly except the professional exam also additional.",
			"The program description and criteria of evaluation tasks of professional entrance exam",
			"The program description and criteria of evaluation tasks additional entrance exam",
			"The list of professional specialties includes:",
			"6(7).040201 Mathematics;",
			"6(7).040202 Mechanics;",
			"6(7).040205 Statistics;",
			"6(7).040301 Applied mathematics;",
			"6(7).040302 Informatics."
	};
	private static String PROGRAM1_LINK = "https://drive.google.com/open?id=0B0sJ0TJI-9nPZTBBTEt3UXhLZUE";
	private static String PROGRAM2_LINK = "https://drive.google.com/open?id=0B0sJ0TJI-9nPMWd4eXdpZExWOHc";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("entrants_exams");
		} else
			getExamRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getExamRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(Template.setTitle(getTitleValue(request)));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		out.print(RuleInfo(request));
		out.print(Template.setFooter());
		out.close();
	}

	private String RuleInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = RuleHull(INFO_RU);
			break;
		case "en":
			res = RuleHull(INFO_EN);
			break;
		default:
			res = RuleHull(INFO_UA);
		}
		return res;
	}

	private String RuleHull(String[] info) {
		String res = "";
		res += "<div class='Information'>" + 
				"<p align='justify' class='Name'>" + info[0] + "</p>" + 
				"<p align='justify' class='Name'><a href='" + PROGRAM1_LINK + "' target='_blank'>" + info[1] + "</a></p>" +
				"<p align='justify' class='Name'><a href='" + PROGRAM2_LINK + "' target='_blank'>" + info[2] + "</a></p>" +
				"<p align='justify' class='Name'>" + info[3] + "</p>" + 
				"<ul><li><div class='Name'>" + info[4] + "</div></li>" + 
				"<li><div class='Name'>" + info[5] + "</div></li>" + 
				"<li><div class='Name'>" + info[6] + "</div></li>" + 
				"<li><div class='Name'>" + info[7] + "</div></li>" + 
				"<li><div class='Name'>" + info[8] + "</div></li></ul>";
		res += "</div>";
		return res;
	}

	private String getTitleValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = Template.TITLE_ENTRANTS_EXAM_RU;
			break;
		case "en":
			res = Template.TITLE_ENTRANTS_EXAM_EN;
			break;
		default:
			res = Template.TITLE_ENTRANTS_EXAM_UA;
		}
		return res;
	}
}
