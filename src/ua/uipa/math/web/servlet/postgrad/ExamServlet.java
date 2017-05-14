package ua.uipa.math.web.servlet.postgrad;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;

public class ExamServlet extends HttpServlet {

	private static final long serialVersionUID = 2241982176789599384L;
	private static String[] INFO_UA = {
			"Згідно із ",
			"правилами прийому до аспірантури та докторантури",
			" вступні випробування до аспірантури складаются з:",
			"вступне випробування з іноземної мови;",
			"вступне випробування з філософії;",
			"вступне випробування із спеціальності 113-Прикладна математика.",
			"Вступники, які на час вступу до аспірантури склали всі або декілька кандидатських іспитів, звільняються від відповідних вступних випробувань до аспірантури і їм зараховуються оцінки кандидатських іспитів.",
			"Питання до вступного іспиту до аспірантури зі спеціальності 113-Прикладна математика",
			"Програма, характеристика та критерії оцінювання завдань вступного іспиту до аспірантури зі спеціальності 113-Прикладна математика"
	};
	private static String[] INFO_RU = {
			"В соответствии с ",
			"правилами приема в аспирантуру и докторантуру",
			" вступительные экзамены в аспирантуру состоят из:",
			"вступительный экзамен по иностранному языку ",
			"вступительный экзамен по философии",
			"вступительный экзамен по специальности 113-Прикладная математика",
			"Поступающие, которые на время поступления в аспирантуру сдали все или несколько кандидатских экзаменов, освобождаются от соответствующих вступительных экзаменов в аспирантуру и им засчитываются оценки кандидатских экзаменов.",
			"Вопрос к вступительному экзамену в аспирантуру по специальности 113-Прикладная математика",
			"Программа, характеристика и критерии оценивания заданий вступительного экзамена в аспирантуру по специальности 113-Прикладная математика"
	};
	private static String[] INFO_EN = {
			"According to ",
			"the rules of admission to postgraduate and doctoral",
			" entrance examinations for post-graduate studies consist of:",
			"entrance examination in a foreign language;",
			"entrance examination in philosophy;",
			"entrance examination in specialty 113 Applied Mathematics.",
			"Applicants who at the time of admission to graduate school passed all or some candidate examinations are exempt from the relevant entrance exams to graduate school and they counted candidate assessment exams.",
			"Questions for the entrance exam to postgraduate specialty 113 Applied Mathematics",
			"The program, description and criteria of evaluation tasks to graduate school entrance exam in the specialty Applied Mathematics 113"
	};
	private static String RULE_LINK = "https://drive.google.com/file/d/0B-9AhczpgmCIX01obEFEZ3BkVGs/view?usp=sharing";
	private static String QUESTIONS_LINK = "https://drive.google.com/open?id=0B0sJ0TJI-9nPNUhaNHEyUDBfb3M";
	private static String PROGRAM_LINK = "https://drive.google.com/open?id=0B0sJ0TJI-9nPamEyOWJpeWFYY00";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("postgrad_exams");
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
				"<p align='justify' class='Name'>" + info[0] + 
				"<a href='" + RULE_LINK + "' target='_blank'>" + info[1] + "</a>" + info[2] + "</p>" + 
				"<ul><li><div class='Name'>" + info[3] + "</div></li>" + 
				"<li><div class='Name'>" + info[4] + "</div></li>" + 
				"<li><div class='Name'>" + info[5] + "</div></li>" + 
				"<li><div class='Name'>" + info[6] + "</div></li></ul>" +
				"<p align='justify' class='Name'><a href='" + QUESTIONS_LINK + "' target='_blank'>" + info[7] + "</a></p>" +
				"<p align='justify' class='Name'><a href='" + PROGRAM_LINK + "' target='_blank'>" + info[8] + "</a></p>";
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
