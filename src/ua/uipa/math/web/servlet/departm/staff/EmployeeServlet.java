package ua.uipa.math.web.servlet.departm.staff;

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
import ua.uipa.math.db.entity.Department;
import ua.uipa.math.db.entity.Image;
import ua.uipa.math.db.entity.Science;
import ua.uipa.math.exception.DBException;

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String EDUCATION_UA = "Освіта:";
	private static final String EDUCATION_RU = "Образование:";
	private static final String EDUCATION_EN = "Education:";
	private static final String EMAIL = "Email:";
	private static final String CV = "CV:";
	private static final String PUBLICATIONS_UA = "Вибрані Публукації:";
	private static final String PUBLICATIONS_RU = "Избранные Публикации:";
	private static final String PUBLICATIONS_EN = "Selected Publications";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if(lang == null){
			String id = request.getParameter("id");
			session.setAttribute("lang", "en");
			response.sendRedirect("employee?id=" + id);
		}
		else
			getEmployeeRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void getEmployeeRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		String itemId = request.getParameter("id");
		int id = Integer.parseInt(itemId);
		Department item = null;
		try {
			item = DBManager.getInstance().getEmployeeById(id, request);
		} catch (DBException e) {
			e.printStackTrace();
		}
		out.print(Template.setTitle(item.getName()));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		out.print(EmployeeInfo(item, request));
		out.print(Template.setFooter());
		out.close();
	}
	
	private String EmployeeInfo(Department item, HttpServletRequest request){
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = EmployeeHull(item, request,
					EDUCATION_RU,
					EMAIL,
					CV,
					PUBLICATIONS_RU);
			break;
		case "en":
			res = EmployeeHull(item, request,
					EDUCATION_EN,
					EMAIL,
					CV,
					PUBLICATIONS_EN);
			break;
		default:
			res = EmployeeHull(item, request,
					EDUCATION_UA,
					EMAIL,
					CV,
					PUBLICATIONS_UA);
		}
		return res;
	}
	
	private String EmployeeHull(Department item, HttpServletRequest request, String education, String email, String cv, String publ) {
		String res = "";
		try {
			Image image = DBManager.getInstance().getStaffImageById(item.getId());
			List<Science> works = DBManager.getInstance().getWorksById(item.getId(), request);
			res += "<div class='Information'>" +
					"<img class='leftimg' src='"+ request.getContextPath() + image.getPath() + "'>" +
					"<h2>" + item.getName() + "</h2>" +
					"<p class='Name'><strong>" + item.getPhd() + "</strong></p>" +
					"<p id='Name_F'><strong>" + education + "</strong></p>" +
					"<p  align='justify' class='Name'>" + item.getEducation() + "</p>" +
					"<p id='Name_F'><strong>" + email + "</strong></p>" +
					"<p class='Name'>" + item.getEmail() + "</p>" +
					"<p id='Name_F'><strong>" + cv + "</strong></p>" +
					"<p align='justify' class='Name'>" + item.getInfo() + "</p>" +
					"<p id='Name_F'><strong>" + publ + "</strong></p>";
			for(int i = 0; i < works.size(); i++)
				res += "<p align='justify' class='Name'>" + (i + 1) + ". " + works.get(i).getTitle() + "</p>";
			res += "</div>";
		} catch (DBException e) {
			e.printStackTrace();
		}
		return res;
	}
}
