package ua.uipa.math.web.servlet.postgrad;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;

public class RuleServlet extends HttpServlet {

	private static final long serialVersionUID = -4596737147574886851L;
	private static String[] INFO_UA = {
			"Кафедра вищої та прикладної математики проводить прийом до аспірантури та докторантури. Правила прийому до УІПА затверджені Вченою радою УІПА і Ви можете з ними ознайомитися за посиланнями:",
			"Правила прийому до УІПА 2017",
			"Додатки до правил прийому:",
			"Додаток 2. Правила прийому до аспірантури та докторантури Української інженерно-педагогічної академії",
			"Більше інформації про правила прийому до аспірантури та докторантури можна знайти на ",
			"сайті академії"
	};
	private static String[] INFO_RU = {
			"Кафедра высшей и прикладной математики проводит прием в аспирантуру и докторантуру. Правила приема в УИПА утверждены Ученым советом УИПА и Вы можете с ними ознакомиться по ссылкам:",
			"Правила приема в УИПА 2017",
			"Приложения к правилам приема:",
			"Приложение 2. Правила приема в аспирантуру и докторантуру Украинской инженерно-педагогической академии",
			"Больше информации о правилах приема в аспирантуру и докторантуру можно найти на",
			"cайте академии"
	};
	private static String[] INFO_EN = {
			"Department of Higher and Applied Mathematics spends admission to postgraduate and doctoral studies. Rules for admission to the UIPA are approved by the UIPA Academic Council and you can read them through the links:",
			"Rules for admission to UIPA 2017",
			"Additions to the admission rules:",
			"Addition2. Admission to postgraduate and doctoral of Ukrainian Engineering and Pedagogics Academy",
			"More information on the rules of admission can be found on the ",
			"website of the Academy"
	};
	private static String RULE_LINK = "https://drive.google.com/file/d/0B-9AhczpgmCINF95d0x0bWZ1cEk/view?usp=sharing";
	private static String ADDITION1_LINK = "https://drive.google.com/file/d/0B-9AhczpgmCIZWdnY19FcTFPUk0/view?usp=sharing";
	private static String ACADEM_LINK = "http://uipa.edu.ua/ua/applicant/ymovi-priyomy?showall=&start=1";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("postgrad_rules");
		} else
			getRuleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getRuleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
				"<p align='justify' class='Name'><a href='" + RULE_LINK + "' target='_blank'>" + info[1] + "</a></p>" + 
				"<p align='justify' class='Name'>" + info[2] + "</p>" + 
				"<p align='justify' class='Name'><a href='" + ADDITION1_LINK + "' target='_blank'>" + info[3] + "</a></p>" +
				"<p align='justify' class='Name'>" + info[4] + "<a href='" + ACADEM_LINK + "' target='_blank'>" + info[5] + "</a></p>";
		res += "</div>";
		return res;
	}

	private String getTitleValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = Template.TITLE_ENTRANTS_RULES_RU;
			break;
		case "en":
			res = Template.TITLE_ENTRANTS_RULES_EN;
			break;
		default:
			res = Template.TITLE_ENTRANTS_RULES_UA;
		}
		return res;
	}
}
