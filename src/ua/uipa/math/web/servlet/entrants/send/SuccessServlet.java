package ua.uipa.math.web.servlet.entrants.send;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;

public class SuccessServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String SUCCESS_INFO_UA = "Ваша інформація була збережена. Ми зв'яжемося з Вами найюлижчим часом.";
	private static final String SUCCESS_INFO_RU = "Ваша информация сохранена. Мы свяжеся с Вами в ближайшее время.";
	private static final String SUCCESS_INFO_EN = "Your information has been saved. We will contact You any time soon.";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getSuccessRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void getSuccessRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(Template.setTitle(getTitleValue(request)));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		out.print(setSuccessInfo(request));
		out.print(Template.setFooter());
		out.close();
	}
	
	private String getTitleValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = Template.TITLE_ENTRANTS_RU;
			break;
		case "en":
			res = Template.TITLE_ENTRANTS_EN;
			break;
		default:
			res = Template.TITLE_ENTRANTS_UA;
		}
		return res;
	}

	private static String setSuccessInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = getSuccessHull(request, SUCCESS_INFO_RU);
			break;
		case "en":
			res = getSuccessHull(request, SUCCESS_INFO_EN);
			break;
		default:
			res = getSuccessHull(request, SUCCESS_INFO_UA);
		}
		return res;
	}
	
	private static String getSuccessHull(HttpServletRequest request, String info) {
		return "<div class='main_text'>" + 
				"<table><tr><td>" +
				"<img class='lang_image' src='"+ request.getContextPath() + "/images/success.png'/></td>" +
				"<td><p>" + info + "</p></td></tr></table>" + 
				"</div>";
	}
}
