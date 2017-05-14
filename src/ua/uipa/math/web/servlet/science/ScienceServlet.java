package ua.uipa.math.web.servlet.science;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;
import ua.uipa.math.db.DBManager;
import ua.uipa.math.db.entity.Science;
import ua.uipa.math.exception.DBException;

public class ScienceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String SCIENCE_AREA_UA = "Основні напрями роботи";
	private static final String SCIENCE_AREA_RU = "Основные направления работы";
	private static final String SCIENCE_AREA_EN = "Main areas of work";
	private static final String SCIENCE_THEME_UA = "Науково-дослідні теми";
	private static final String SCIENCE_THEME_RU = "Научно-исследовательские темы";
	private static final String SCIENCE_THEME_EN = "Scientific research topics";
	private static final String SCIENCE_DETAILS_UA = "Детальніше▼";
	private static final String SCIENCE_DETAILS_RU = "Подробнее▼";
	private static final String SCIENCE_DETAILS_EN = "More▼";

	private static final String AREA = "area";
	private static final String THEME = "theme";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("science");
		} else
			getSciencetRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getSciencetRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(Template.setTitle(getTitleValue(request)));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		out.print(setScienceInfo(request));
		out.print(Template.setFooter());
		out.close();
	}

	private String setScienceInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = getScienceHull(request, SCIENCE_AREA_RU, SCIENCE_THEME_RU, SCIENCE_DETAILS_RU);
			break;
		case "en":
			res = getScienceHull(request, SCIENCE_AREA_EN, SCIENCE_THEME_EN, SCIENCE_DETAILS_EN);
			break;
		default:
			res = getScienceHull(request, SCIENCE_AREA_UA, SCIENCE_THEME_UA, SCIENCE_DETAILS_UA);
		}
		return res;
	}

	private String getScienceHull(HttpServletRequest request, String area, String theme, String details) {
		String res = "";
		try {
			List<Science> areas = DBManager.getInstance().getScienceItemsByType(AREA, request);
			List<Science> themes = DBManager.getInstance().getScienceItemsByType(THEME, request);
			res += "<h2>" + area + "</h2>"
					+ "<div class='main_text'>" + "<span>" + "<ol class='first'>";
			for (int i = 0; i < areas.size(); i++)
				res += "<li>" + (i + 1) + ". " + areas.get(i).getTitle() + "</li>";
			res += "</ol>" + "</span>" + "<h2>" + theme + "</h2>"
					+ "<span>" + "<ol class='first'>";
			for (int i = 0; i < themes.size(); i++)
				res += "<li>" + (i + 1) + ". " + themes.get(i).getTitle() + "</li>";
			res += "</ol></span></div>";
		} catch (DBException e) {
			e.printStackTrace();
		}
		return res;
	}

	private String getTitleValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = Template.TITLE_SCIENCE_RU;
			break;
		case "en":
			res = Template.TITLE_SCIENCE_EN;
			break;
		default:
			res = Template.TITLE_SCIENCE_UA;
		}
		return res;
	}
}
