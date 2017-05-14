package ua.uipa.math.web.servlet.entrants;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;

public class RuleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String[] INFO_UA = {
			"Українська інженерно-педагогічна академія оголошує про початок вступної кампанії 2017 року. Правила прийому до УІПА затверджені Вченою радою УІПА і Ви можете з ними ознайомитися за посиланнями:",
			"Правила прийому до УІПА 2017",
			"Додатки до правил прийому:",
			"Додаток 1. Перелік спеціальностей (спеціалізацій, освітніх програм), за якими оголошується прийом на навчання, ліцензовані обсяги та нормативні терміни навчання",
			"Додаток 5. Перелік напрямів для прийому на навчання осіб, які здобули ступінь бакалавра або освітньо-кваліфікаційного рівень спеціаліста, для здобуття ступеня магістра за спорідненою спеціальністю",
			"Більше інформації про правила прийому можна знайти на ",
			"сайті академії"
	};
	private static String[] INFO_RU = {
			"Украинская инженерно-педагогическая академия объявляет о начале вступительной кампании 2017 года. Правила приема в УИПА утверждены Ученым советом УИПА и Вы можете с ними ознакомиться по ссылкам:",
			"Правила приема в УИПА 2017",
			"Приложения к правилам приема:",
			"Приложение 1. Перечень специальностей (специализаций, образовательных программ), по которым объявляется прием на обучение, лицензированные объемы и нормативные сроки обучения",
			"Приложение 5. Перечень направлений для приема на обучение лиц, которые получили степень бакалавра или образовательно-квалификационного уровня специалиста, для получения степени магистра по родственной специальности",
			"Больше информации о правилах приема можно найти на ",
			"сайте академии"
	};
	private static String[] INFO_EN = {
			"The Ukrainian Engineering and Pedagogical Academy announces the launch of the 2017 introductory campaign. Rules for admission to the UIPA are approved by the UIPA Academic Council and you can read them through the links:",
			"Rules for admission to UIPA 2017",
			"Additions to the admission rules:",
			"Addition1. The list of specialties (specializations, educational programs), according to which the admission for training, licensed volumes and standard terms of training",
			"Addition 5. List of directions for admission for training of persons who have received a bachelor's degree or an educational and qualification level of a specialist for obtaining a master's degree in a related specialty",
			"More information on the rules of admission can be found on the ",
			"website of the Academy"
	};
	private static String RULE_LINK = "https://drive.google.com/file/d/0B-9AhczpgmCINF95d0x0bWZ1cEk/view?usp=sharing";
	private static String ADDITION1_LINK = "https://drive.google.com/file/d/0B-9AhczpgmCIV1QyTHdLYm9UbFk/view?usp=sharing";
	private static String ADDITION2_LINK = "https://drive.google.com/file/d/0B-9AhczpgmCIRF8xT2J3eHlfTGs/view?usp=sharing";
	private static String ACADEM_LINK = "http://uipa.edu.ua/ua/applicant/ymovi-priyomy";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("entrants_rules");
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
				"<p align='justify' class='Name'><a href='" + ADDITION2_LINK + "' target='_blank'>" + info[4] + "</a></p>" +
				"<p align='justify' class='Name'>" + info[5] + "<a href='" + ACADEM_LINK + "' target='_blank'>" + info[6] + "</a></p>";
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
