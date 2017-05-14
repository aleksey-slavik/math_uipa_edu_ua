package ua.uipa.math.web.servlet.contacts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;

public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTACT_HEADER_UA = "Контакти";
	private static final String CONTACT_HEADER_RU = "Контакты";
	private static final String CONTACT_HEADER_EN = "Contacts";
	private static final String CONTACT_PHONE_UA = "Телефони:";
	private static final String CONTACT_PHONE_RU = "Телефоны:";
	private static final String CONTACT_PHONE_EN = "Phones:";

	private static final String[] CONTACT_ADDRESS_UA = { "Адреса:", "61003, Харків, вул. Університетська, 16,",
			"Кафедра Вищої та Прикладної Математики,", "Корпус 4, поверх 2, каб. 218, 219" };
	private static final String[] CONTACT_ADDRESS_RU = { "Адресс:", "61003, Харьков, ул. Университетская, 16,",
			"Кафедра Высшей и Прикладной Математики,", "корпус 4, этаж 2, каб. 218, 219" };
	private static final String[] CONTACT_ADDRESS_EN = { "Address:", "61003, Kharkiv, Universitetska str., 16,",
			"Department of Higher and Applied Mathematics,", "housing 4, floor 2, room. 218, 219" };

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("contacts");
		} else
			getContactRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getContactRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(Template.setTitle(getTitleValue(request)));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		out.print(setContactInfo(request));
		out.print(Template.setFooter());
		out.close();
	}

	private String setContactInfo(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = getContactHull(CONTACT_HEADER_RU, CONTACT_PHONE_RU, CONTACT_ADDRESS_RU);
			break;
		case "en":
			res = getContactHull(CONTACT_HEADER_EN, CONTACT_PHONE_EN, CONTACT_ADDRESS_EN);
			break;
		default:
			res = getContactHull(CONTACT_HEADER_UA, CONTACT_PHONE_UA, CONTACT_ADDRESS_UA);
		}
		return res;
	}

	private String getContactHull(String header, String phone, String[] address) {
		return "<h2>" + header + "</h2>" + "<div class='main_text'>" + "<table><tr><td>"
				+ "<div class='main_text_contact'>" + "<h4>" + address[0] + "</h4><ol class='zebra'>" + 
				"<li>" + address[1] + "</li>"
				+ "<li>" + address[2] + "</li>" 
				+ "<li>" + address[3] + "</li></ol>" + "<h4>E-mail:</h4>" + "<ol class='zebra'>"
				+ "<li>academ_mail@ukr.net</li>" + "<li>math@uipa.edu.ua</li>" + "</ol>" + "<h4>" + phone + "</h4>"
				+ "<ol class='zebra'>" + "<li>771-05-45</li>" + "<li>733-78-72</li>" + "<li>733-78-32</li>"
				+ "<li>733-78-30</li>" + "</ol>" + "</div>" + "</td><td>" + "<div class='map'>"
				+ "<iframe src='https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d762.635531053725!2d36.23017371338058!3d49.98904836569368!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4127a0f6d4a71653%3A0x95bf6b9f518a8c74!2z0KPQvdGW0LLQtdGA0YHQuNGC0LXRgtGB0YzQutCwINCy0YPQu9C40YbRjywgMTYsINCl0LDRgNC60ZbQsiwg0KXQsNGA0LrRltCy0YHRjNC60LAg0L7QsdC70LDRgdGC0YwsINCj0LrRgNCw0LjQvdCw!5e0!3m2!1sru!2sru!4v1470651699423' width='500' height='400' frameborder='0' style='border:0' allowfullscreen></iframe>"
				+ "</div>" + "</td></tr></table>" + "</div>";
	}

	private String getTitleValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = Template.TITLE_CONTACTS_RU;
			break;
		case "en":
			res = Template.TITLE_CONTACTS_EN;
			break;
		default:
			res = Template.TITLE_CONTACTS_UA;
		}
		return res;
	}
}
