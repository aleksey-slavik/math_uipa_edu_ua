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
import ua.uipa.math.exception.DBException;

public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("staff");
		} else
			getStaffRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getStaffRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(Template.setTitle(getTitleValue(request)));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		try {
			List<Department> staff = DBManager.getInstance().getStaffItems(request);
			for (Department item : staff) {
				Image image = Template.checkImage(DBManager.getInstance().getStaffImageById(item.getId()));
				out.print("<nav id='table'>");
				out.print("<a href='employee?id=" + item.getId() + "'>" 
						+ "<table class='news_list'>"
						+ "<tr class='news_item'>" 
						+ "<td rowspan='2'>" 
						+ "<div class='photo_container'>"
						+ "<img class='staff_image' src='" + request.getContextPath() + image.getPath() + "'/></div></td>" 
						+ "<td><center><p class='news_title'>" + item.getName() + "</p></center></td></tr>" 
						+ "<td><center><p class='news_text'>" + item.getPhd() + "</p></center></td></tr></tr></table></a>");
				out.print("</nav>");
			}
		} catch (DBException e) {
			e.printStackTrace();
		}
		out.print(Template.setFooter());
		out.close();
	}

	private String getTitleValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = Template.TITLE_DEPARTMENT_STAFF_RU;
			break;
		case "en":
			res = Template.TITLE_DEPARTMENT_STAFF_EN;
			break;
		default:
			res = Template.TITLE_DEPARTMENT_STAFF_UA;
		}
		return res;
	}

}
