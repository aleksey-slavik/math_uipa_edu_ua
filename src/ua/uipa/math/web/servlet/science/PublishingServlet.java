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
import ua.uipa.math.db.entity.Image;
import ua.uipa.math.db.entity.Science;
import ua.uipa.math.exception.DBException;

public class PublishingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PAPER_UA = "Закордонні публікації";
	private static final String PAPER_RU = "Заграничные публикации";
	private static final String PAPER_EN = "Foreign publications";
	private static final String MONOGRAPHY_UA = "Монографії";
	private static final String MONOGRAPHY_RU = "Монографии";
	private static final String MONOGRAPHY_EN = "Monographs";
	private static final String PATENT_UA = "Патенти";
	private static final String PATENT_RU = "Патенты";
	private static final String PATENT_EN = "Patents";
	private static final String DETAILS_UA = "Детальніше▼";
	private static final String DETAILS_RU = "Подробнее▼";
	private static final String DETAILS_EN = "More▼";

	private static final String PAPER = "paper";
	private static final String MONOGRAPHY = "monography";
	private static final String PATENT = "patent";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("publishings");
		} else
			getPublishingsRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getPublishingsRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(Template.setTitle(getTitleValue(request)));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		out.print(getPublishingsInfo(request));
		out.print(Template.setFooter());
		out.close();
	}

	private String getTitleValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = Template.TITLE_SCIENCE_PUBLICATIONS_RU;
			break;
		case "en":
			res = Template.TITLE_SCIENCE_PUBLICATIONS_EN;
			break;
		default:
			res = Template.TITLE_SCIENCE_PUBLICATIONS_UA;
		}
		return res;
	}

	private String getPublishingsInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = getPublishingsHull(request, PAPER_RU, MONOGRAPHY_RU, PATENT_RU, DETAILS_RU);
			break;
		case "en":
			res = getPublishingsHull(request, PAPER_EN, MONOGRAPHY_EN, PATENT_EN, DETAILS_EN);
			break;
		default:
			res = getPublishingsHull(request, PAPER_UA, MONOGRAPHY_UA, PATENT_UA, DETAILS_UA);
		}
		return res;
	}

	private String getPublishingsHull(HttpServletRequest request, String paper, String monography, String patent,
			String details) {
		String res = "";
		try {
			List<Science> papers = DBManager.getInstance().getScienceItemsByType(PAPER, request);
			List<Science> monographs = DBManager.getInstance().getScienceItemsByType(MONOGRAPHY, request);
			List<Science> patents = DBManager.getInstance().getScienceItemsByType(PATENT, request);
			List<Image> paperImages = DBManager.getInstance().getScienceImageByType(PAPER);
			List<Image> monographImages = DBManager.getInstance().getScienceImageByType(MONOGRAPHY);
			List<Image> patentImages = DBManager.getInstance().getScienceImageByType(PATENT);
			res += "<script type='text/javascript' src='script/expand.js'></script>" + "<h2>" + paper + "</h2>"
					+ "<div class='main_text'>" + "<div class='gallery'>";
			for (int i = 0; i < paperImages.size(); i++)
				res += "<a tabindex='" + (i + 1) + "'><img src='" + request.getContextPath()
						+ paperImages.get(i).getPath() + "'></a>";
			res += "</div>" + "<p style='cursor:pointer' onClick='expandit(this)'>" + details + "</p>"
					+ "<span style='display:none' style=&{head};>" + "<ol class='first'>";
			for (int i = 0; i < papers.size(); i++)
				res += "<li>" + (i + 1) + ". " + papers.get(i).getTitle() + "</li>";
			res += "</ol>" + "</span>" + "<h2>" + monography + "</h2>" + "<div class='gallery'>";
			for (int i = 0; i < monographImages.size(); i++)
				res += "<a tabindex='" + (i + 1) + "'><img src='" + request.getContextPath()
						+ monographImages.get(i).getPath() + "'></a>";
			res += "</div>" + "<p style='cursor:pointer' onClick='expandit(this)'>" + details + "</p>"
					+ "<span style='display:none' style=&{head};>" + "<ol class='first'>";
			for (int i = 0; i < monographs.size(); i++)
				res += "<li>" + (i + 1) + ". " + monographs.get(i).getTitle() + "</li>";
			res += "</ol>" + "</span>" + "<h2>" + patent + "</h2>" + "<div class='gallery'>";
			for (int i = 0; i < patentImages.size(); i++)
				res += "<a tabindex='" + (i + 1) + "'><img src='" + request.getContextPath()
						+ patentImages.get(i).getPath() + "'></a>";
			res += "</div>" + "<p style='cursor:pointer' onClick='expandit(this)'>" + details + "</p>"
					+ "<span style='display:none' style=&{head};>" + "<ol class='first'>";
			for (int i = 0; i < patents.size(); i++)
				res += "<li>" + (i + 1) + ". " + patents.get(i).getTitle() + "</li>";
			res += "</ol>" + "</span>" + "</div>";
		} catch (DBException e) {
			e.printStackTrace();
		}
		return res;
	}
}
