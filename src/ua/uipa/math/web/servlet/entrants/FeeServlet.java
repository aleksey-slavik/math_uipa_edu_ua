package ua.uipa.math.web.servlet.entrants;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;

public class FeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String INFO_UA = "Вартість навчання в магістратурі для студентів денної форми навчання складатиме 7800 грн. на рік, для заочної форми навчання - 7000 грн на рік";
	private static String INFO_RU = "Стоимость обучения в магистратуре для студентов дневной формы обучения будет составлять 7800 грн. в год, для заочной формы обучения - 7000 грн. в год";
	private static String INFO_EN = "Tuition fees at the Master for Daytime form education student will amount 7800 UAH per year for correspondence form of education student - 7000 UAH per year";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("entrants_fee");
		} else
			getFeeRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getFeeRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

	private String RuleHull(String info) {
		String res = "";
		res += "<div class='Information'>" + 
				"<p align='justify' class='Name'>" + info + "</p>";
		res += "</div>";
		return res;
	}

	private String getTitleValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = Template.TITLE_ENTRANTS_FEE_RU;
			break;
		case "en":
			res = Template.TITLE_ENTRANTS_FEE_EN;
			break;
		default:
			res = Template.TITLE_ENTRANTS_FEE_UA;
		}
		return res;
	}
}
