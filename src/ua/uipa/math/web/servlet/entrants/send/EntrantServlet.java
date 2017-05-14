package ua.uipa.math.web.servlet.entrants.send;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;
import ua.uipa.math.db.DBManager;
import ua.uipa.math.exception.DBException;

public class EntrantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String ENTRANTS_INFO_UA = "Залиште інформацію про себе для зворотнього зв'язку";
	public static final String ENTRANTS_INFO_RU = "Оставьте информацию о себе для обратной связи";
	public static final String ENTRANTS_INFO_EN = "Leave information about themselves for feedback";
	public static final String ENTRANTS_BUTTON_UA = "Відправити";
	public static final String ENTRANTS_BUTTON_RU = "Отправить";
	public static final String ENTRANTS_BUTTON_EN = "Send";
	public static final String ENTRANTS_NAME_UA = "Ім'я";
	public static final String ENTRANTS_NAME_RU = "Имя";
	public static final String ENTRANTS_NAME_EN = "Name";
	public static final String ENTRANTS_PHONE_UA = "Телефон";
	public static final String ENTRANTS_PHONE_RU = "Телефон";
	public static final String ENTRANTS_PHONE_EN = "Phone";
	public static final String ENTRANTS_EMAIL = "Email";
	public static final String ENTRANTS_MESSAGE_UA = "Звертаю Вашу увагу, що пункти позначені зірочкою, обов'язкові для заповнення.";
	public static final String ENTRANTS_MESSAGE_RU = "Обращаю Ваше внимание, что пункты отмеченные звездочкой, обязательны для заполнения.";
	public static final String ENTRANTS_MESSAGE_EN = "Call Your attention that the items marked with an asterisk are required.";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if(lang == null){
			session.setAttribute("lang", "en");
			response.sendRedirect("entrants");
		}
		else
		getEntrantsRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getEntrantsRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(Template.setTitle(getTitleValue(request)));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		out.print(setEntrantsInfo(request));
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
	
	private static String setEntrantsInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = getEntrantsHull(request,
					ENTRANTS_BUTTON_RU,
					ENTRANTS_MESSAGE_RU,
					ENTRANTS_NAME_RU, 
					ENTRANTS_PHONE_RU, 
					ENTRANTS_EMAIL);
			break;
		case "en":
			res = getEntrantsHull(request,
					ENTRANTS_BUTTON_EN,
					ENTRANTS_MESSAGE_EN,
					ENTRANTS_NAME_EN, 
					ENTRANTS_PHONE_EN, 
					ENTRANTS_EMAIL);
			break;
		default:
			res = getEntrantsHull(request, 
					ENTRANTS_BUTTON_UA,
					ENTRANTS_MESSAGE_UA,
					ENTRANTS_NAME_UA, 
					ENTRANTS_PHONE_UA, 
					ENTRANTS_EMAIL);
		}
		return res;
	}
	
	private static String getEntrantsHull(HttpServletRequest request, String button, String message, String name, String phone, String email) {
		String info = null;
		try {
			info = DBManager.getInstance().getEntrantData(request);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "<div class='Information'>" + 
				"<p class='Name'>" + info + "</p>" + 
				"<div class='down'>" + 
				"<a href='#?w=500' rel='popup_contact' class='button poplight'><button type='submit'>" + button + "</button></a>" + 
				"</div>" + 
				"</div>" +
				"<div id='popup_contact' class='popup_block'>" +
				"<div class='note'>" +
				"<p>" + message + "</p>" +
				"</div>" +
				"<form id='form' action='send' method='post'>" +
				"<fieldset>" +
				"<p class='first'>" + 
				"<label for='name'>" + name + " *</label>" +
				"<input type='text' name='name' id='name' size='30' required>" +
				"</p>" +
				"<p>" + 
				"<label for='phone'>" + phone + " *</label>" +
				"<input type='text' name='phone' id='phone' size='10' required>" +
				"</p>" +
				"<p>" + 
				"<label for='email'>" + email + " *</label>" +
				"<input type='email' name='email' id='email' size='30' required>" +
				"</p>" +
				"</fieldset>" +
				"<p class='submit'><button type='submit'>" + button + "</button></p>" +
				"</form>" +
				"</div>" +
				"<script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js'></script>" +
				"<script src='script/dm-modal.js'></script>";
	}

}
