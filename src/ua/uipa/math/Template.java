package ua.uipa.math;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.uipa.math.db.entity.Image;

public class Template {

	public static final String TITLE_NEWS_UA = "Новини";
	public static final String TITLE_NEWS_RU = "Новости";
	public static final String TITLE_NEWS_EN = "News";
	public static final String TITLE_DEPARTMENT_UA = "Кафедра";
	public static final String TITLE_DEPARTMENT_RU = "Кафедра";
	public static final String TITLE_DEPARTMENT_EN = "Department";
	public static final String TITLE_DEPARTMENT_ABOUT_UA = "Про Кафедру";
	public static final String TITLE_DEPARTMENT_ABOUT_RU = "Про Кафедру";
	public static final String TITLE_DEPARTMENT_ABOUT_EN = "About of the Department";
	public static final String TITLE_DEPARTMENT_STAFF_UA = "Склад Кафедри";
	public static final String TITLE_DEPARTMENT_STAFF_RU = "Состав Кафедры";
	public static final String TITLE_DEPARTMENT_STAFF_EN = "Staff of the Department";
	public static final String TITLE_DEPARTMENT_HISTORY_UA = "Історія Кафедри";
	public static final String TITLE_DEPARTMENT_HISTORY_RU = "История Кафедры";
	public static final String TITLE_DEPARTMENT_HISTORY_EN = "History of the Department";
	public static final String TITLE_DEPARTMENT_SCHEDULE_UA = "Розклад Кафедри";
	public static final String TITLE_DEPARTMENT_SCHEDULE_RU = "Расписание Кафедры";
	public static final String TITLE_DEPARTMENT_SCHEDULE_EN = "Schedule of the Department";
	public static final String TITLE_DEPARTMENT_GUIDANCE_UA = "Профорієнтаційна робота";
	public static final String TITLE_DEPARTMENT_GUIDANCE_RU = "Профориентационная работа";
	public static final String TITLE_DEPARTMENT_GUIDANCE_EN = "Career guidance";
	public static final String TITLE_SCIENCE_UA = "Наукова діяльність";
	public static final String TITLE_SCIENCE_RU = "Научная Деятельность";
	public static final String TITLE_SCIENCE_EN = "Research Activities";
	public static final String TITLE_SCIENCE_AREA_UA = "Основні напрямки роботи";
	public static final String TITLE_SCIENCE_AREA_RU = "Основные направления работы";
	public static final String TITLE_SCIENCE_AREA_EN = "Main areas of works";
	public static final String TITLE_SCIENCE_PUBLICATIONS_UA = "Публікації";
	public static final String TITLE_SCIENCE_PUBLICATIONS_RU = "Публикации";
	public static final String TITLE_SCIENCE_PUBLICATIONS_EN = "Publications";
	public static final String TITLE_SCIENCE_CONFERENCES_UA = "Конференції";
	public static final String TITLE_SCIENCE_CONFERENCES_RU = "Конференции";
	public static final String TITLE_SCIENCE_CONFERENCES_EN = "Conferences";
	public static final String TITLE_SCIENCE_CENTER_UA = "Міжвідомчий центр";
	public static final String TITLE_SCIENCE_CENTER_RU = "Межведомственный центр";
	public static final String TITLE_SCIENCE_CENTER_EN = "Interdepartmental Center";
	public static final String TITLE_STUDENTS_UA = "Студентам";
	public static final String TITLE_STUDENTS_RU = "Студентам";
	public static final String TITLE_STUDENTS_EN = "Students";
	public static final String TITLE_ENTRANTS_UA = "Абітурієнтам";
	public static final String TITLE_ENTRANTS_RU = "Абитуриентам";
	public static final String TITLE_ENTRANTS_EN = "Entrants";
	public static final String TITLE_ENTRANTS_FEEDBACK_UA = "Зворотній зв'язок";
	public static final String TITLE_ENTRANTS_FEEDBACK_RU = "Обратная связь";
	public static final String TITLE_ENTRANTS_FEEDBACK_EN = "Feedback";
	public static final String TITLE_ENTRANTS_SPESIALITY_UA = "Спеціальність";
	public static final String TITLE_ENTRANTS_SPESIALITY_RU = "Специальность";
	public static final String TITLE_ENTRANTS_SPESIALITY_EN = "Speciality";
	public static final String TITLE_ENTRANTS_RULES_UA = "Правила прийому";
	public static final String TITLE_ENTRANTS_RULES_RU = "Правила прийома";
	public static final String TITLE_ENTRANTS_RULES_EN = "Admission rules";
	public static final String TITLE_ENTRANTS_PROGRAM_UA = "Освітня програма";
	public static final String TITLE_ENTRANTS_PROGRAM_RU = "Образовательная программа";
	public static final String TITLE_ENTRANTS_PROGRAM_EN = "Educational program";
	public static final String TITLE_ENTRANTS_DOCS_UA = "Документи для вступу";
	public static final String TITLE_ENTRANTS_DOCS_RU = "Документы для поступления";
	public static final String TITLE_ENTRANTS_DOCS_EN = "Admission documents";
	public static final String TITLE_ENTRANTS_EXAM_UA = "Вступні випробування";
	public static final String TITLE_ENTRANTS_EXAM_RU = "Вступительные экзамены";
	public static final String TITLE_ENTRANTS_EXAM_EN = "Admission exams";
	public static final String TITLE_ENTRANTS_FEE_UA = "Вартість навчання";
	public static final String TITLE_ENTRANTS_FEE_RU = "Стоимость обучения";
	public static final String TITLE_ENTRANTS_FEE_EN = "Education cost";
	public static final String TITLE_POSTGRAD_UA = "Аспірантура";
	public static final String TITLE_POSTGRAD_RU = "Аспирантура";
	public static final String TITLE_POSTGRAD_EN = "Postgraduate School";
	public static final String TITLE_CONTACTS_UA = "Контакти";
	public static final String TITLE_CONTACTS_RU = "Контакты";
	public static final String TITLE_CONTACTS_EN = "Contacts";	

	public static final String HEADER_UA = "Кафедра Вищої та Прикладної Математики";
	public static final String HEADER_RU = "Кафедра Высшей и Прикладной Математики";
	public static final String HEADER_EN = "Department of Higher and Applied Mathematics";
	
	public static String setTitle(String title) {
		return /*"<!DOCTYPE html>"
				+*/ "<html>" + "<head>" + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />" 
				+ "<title>" + title + "</title>"
				+ "<link rel='shortcut icon' href='images/Tab.png' type='image/png' />"
				+ "<link rel='stylesheet' href='style/menu.css' type='text/css' media='all' />"
				+ "<link rel='stylesheet' href='style/table.css' type='text/css' media='all' />"
				+ "<link rel='stylesheet' href='style/form.css' type='text/css' media='all' />"
				+ "<link rel='stylesheet' href='style/contact.css' type='text/css' media='all' />"
				+ "<link rel='stylesheet' href='style/poster.css' type='text/css' media='all' />"
				+ "</head>" + "<body>";
	}

	public static String setHeader(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String uri = request.getServletPath() + "?" + request.getQueryString();
		String res = "";
		switch (lang) {
		case "ru":
			res = getHeaderHull(HEADER_RU, uri);
			break;
		case "en":
			res = getHeaderHull(HEADER_EN, uri);
			break;
		default:
			res = getHeaderHull(HEADER_UA, uri);
		}
		return res;
	}

	public static String setMenu(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res += getMenuHull(
					TITLE_NEWS_RU,
					TITLE_DEPARTMENT_RU,
					TITLE_DEPARTMENT_ABOUT_RU,
					TITLE_DEPARTMENT_STAFF_RU, 
					TITLE_DEPARTMENT_HISTORY_RU,
					TITLE_DEPARTMENT_SCHEDULE_RU,
					TITLE_DEPARTMENT_GUIDANCE_RU,
					TITLE_SCIENCE_RU,
					TITLE_SCIENCE_AREA_RU,
					TITLE_SCIENCE_PUBLICATIONS_RU, 
					TITLE_SCIENCE_CONFERENCES_RU,
					TITLE_SCIENCE_CENTER_RU,
					TITLE_ENTRANTS_RU,
					TITLE_ENTRANTS_SPESIALITY_RU,
					TITLE_ENTRANTS_RULES_RU,
					TITLE_ENTRANTS_PROGRAM_RU,
					TITLE_ENTRANTS_DOCS_RU,
					TITLE_ENTRANTS_EXAM_RU,
					TITLE_ENTRANTS_FEE_RU,
					TITLE_ENTRANTS_FEEDBACK_RU,
					TITLE_POSTGRAD_RU,
					TITLE_STUDENTS_RU, 
					TITLE_CONTACTS_RU);
			break;
		case "en":
			res += getMenuHull(
					TITLE_NEWS_EN,
					TITLE_DEPARTMENT_EN,
					TITLE_DEPARTMENT_ABOUT_EN,
					TITLE_DEPARTMENT_STAFF_EN, 
					TITLE_DEPARTMENT_HISTORY_EN,
					TITLE_DEPARTMENT_SCHEDULE_EN,
					TITLE_DEPARTMENT_GUIDANCE_EN,
					TITLE_SCIENCE_EN,
					TITLE_SCIENCE_AREA_EN,
					TITLE_SCIENCE_PUBLICATIONS_EN, 
					TITLE_SCIENCE_CONFERENCES_EN,
					TITLE_SCIENCE_CENTER_EN,
					TITLE_ENTRANTS_EN,
					TITLE_ENTRANTS_SPESIALITY_EN,
					TITLE_ENTRANTS_RULES_EN,
					TITLE_ENTRANTS_PROGRAM_EN,
					TITLE_ENTRANTS_DOCS_EN,
					TITLE_ENTRANTS_EXAM_EN,
					TITLE_ENTRANTS_FEE_EN,
					TITLE_ENTRANTS_FEEDBACK_EN,
					TITLE_POSTGRAD_EN,
					TITLE_STUDENTS_EN,
					TITLE_CONTACTS_EN);
			break;
		default:
			res += getMenuHull(
					TITLE_NEWS_UA,
					TITLE_DEPARTMENT_UA,
					TITLE_DEPARTMENT_ABOUT_UA,
					TITLE_DEPARTMENT_STAFF_UA, 
					TITLE_DEPARTMENT_HISTORY_UA,
					TITLE_DEPARTMENT_SCHEDULE_UA,
					TITLE_DEPARTMENT_GUIDANCE_UA,
					TITLE_SCIENCE_UA,
					TITLE_SCIENCE_AREA_UA,
					TITLE_SCIENCE_PUBLICATIONS_UA, 
					TITLE_SCIENCE_CONFERENCES_UA,
					TITLE_SCIENCE_CENTER_UA,
					TITLE_ENTRANTS_UA,
					TITLE_ENTRANTS_SPESIALITY_UA,
					TITLE_ENTRANTS_RULES_UA,
					TITLE_ENTRANTS_PROGRAM_UA,
					TITLE_ENTRANTS_DOCS_UA,
					TITLE_ENTRANTS_EXAM_UA,
					TITLE_ENTRANTS_FEE_UA,
					TITLE_ENTRANTS_FEEDBACK_UA,
					TITLE_POSTGRAD_UA,
					TITLE_STUDENTS_UA,
					TITLE_CONTACTS_UA);
		}
		return res;
	}
	
	public static String setFooter(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();
		return "<nav id='footer'>" +
				"<div class='footer'><p>&copy;" + dateFormat.format(date) + "</p></div></nav>"
				+ "</body>"
				+ "</html>";
	}

	private static String getMenuHull(String news, String departm, String about, String staff, String history, 
			String schedule, String guidance, String science, String areas, String publs, String confrs, String center, 
			String entrants, String spec, String rules, String program, String docs, String exam, String fee, 
			String feedback, String postgrad, String students, String contacts) {
		return "<div>" + 
				"<p style='text-align: center;'></p>" +
				"<nav id='menu-wrap'>" +
				"<ul id='menu'>" + 
				"<li><a href='news?page=1'>" + news + "</a></li>" + 
				"<li><a href='department'>" + departm + "</a>" + 
				"<ul>" + 
				"<li><a href='department'>" + about + "</a></li>" +
				"<li><a href='staff'>" + staff + "</a></li>" + 
				"<li><a href='history'>" + history + "</a></li>" +
				"<li><a href='http://uo.uipa.edu.ua/timeTable/chair' target='_blank'>" + schedule + "</a></li>" +
				"<li><a href='guidance'>" + guidance + "</a></li>" +
				"</ul>" + 
				"</li>" + 
				"<li><a href='science'>" + science + "</a>" + 
				"<ul>" + 
				"<li><a href='science'>" + areas + "</a></li>" +
				"<li><a href='publishings'>" + publs + "</a></li>" +
				"<li><a href='conference?page=1'>" + confrs + "</a></li>" +
				"<li><a href='center'>" + center + "</a></li>" +
				"</ul>" + 
				"</li>" + 
				"<li><a href='entrants'>" + entrants + "</a>" +
				"<ul>" + 
				"<li><a href='entrants_speciality'>" + spec + "</a></li>" +
				"<li><a href='entrants_rules'>" + rules + "</a></li>" +
				"<li><a href='https://drive.google.com/open?id=0B0sJ0TJI-9nPbFFOSmVHRFp6Unc' target='_blank'>" + program + "</a></li>" +
				"<li><a href='entrants_docs'>" + docs + "</a></li>" +
				"<li><a href='entrants_exams'>" + exam + "</a></li>" +
				"<li><a href='entrants_fee'>" + fee + "</a></li>" +
				"<li><a href='entrants'>" + feedback + "</a></li>" +
				"</ul>" + 
				"</li>" +
				"<li><a href='entrants'>" + postgrad + "</a>" +
				"<ul>" + 
				"<li><a href='postgrad_speciality'>" + spec + "</a></li>" +
				"<li><a href='postgrad_rules'>" + rules + "</a></li>" +
				"<li><a href='https://drive.google.com/open?id=0B0sJ0TJI-9nPbXJ2ekFpT0lsd00' target='_blank'>" + program + "</a></li>" +
				"<li><a href='postgrad_docs'>" + docs + "</a></li>" +
				"<li><a href='postgrad_exams'>" + exam + "</a></li>" +
				"<li><a href='#'>" + fee + "</a></li>" +
				"<li><a href='entrants'>" + feedback + "</a></li>" +
				"</ul>" + 
				"</li>" +
				"<li><a href='students'>" + students + "</a></li>" +
				"<li><a href='contacts'>" + contacts + "</a></li>" + 
				"</ul>" + 
				"</nav" +
				"</div>";
	}

	private static String getHeaderHull(String header, String link){
		return "<div class='header'><p>" + header + "</p></div>" +
				"<div class='lang_container'>" +
				"<a href='session?lang=ua&link=" + link + "'><img class='lang_image' src='images/Ukraine.png'/></a>" +
				"<a href='session?lang=ru&link=" + link + "'><img class='lang_image' src='images/Russia.png'/>" +
				"<a href='session?lang=en&link=" + link + "'><img class='lang_image' src='images/United_Kingdom.png'/>" + 
				"</div>";
	}
	
	public static Image checkImage(Image input){
		if(input == null){
			String path = "/images/Tab.png";
			Image output = new Image();
			output.setPath(path);
			return output;
		} else
			return input;
	}
}
