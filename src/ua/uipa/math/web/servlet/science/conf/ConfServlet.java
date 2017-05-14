package ua.uipa.math.web.servlet.science.conf;

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
import ua.uipa.math.db.entity.ConfItem;
import ua.uipa.math.db.entity.Image;
import ua.uipa.math.exception.DBException;

public class ConfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int total = 3;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if(lang == null){
			String page = request.getParameter("page");
			session.setAttribute("lang", "en");
			response.sendRedirect("conference?page=" + page);
		}
		else{
			getConfRequest(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void getConfRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(Template.setTitle(getTitleValue(request)));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		String pageId = request.getParameter("page");
		int id = Integer.parseInt(pageId);
		if (id == 1) {
			id = 0;
		} else {
			id--;
			id = id * total;
		}
		try {
			List<ConfItem> all = DBManager.getInstance().getConfItems(request);
			List<ConfItem> items = DBManager.getInstance().getConfItems(total, id, request);
			for (ConfItem item : items) {
				Image image = Template.checkImage(DBManager.getInstance().getSimpleConfImageById(item.getId()));
				out.print("<nav id='table'>");
				out.print("<a href='conf_item?id=" + item.getId() + "'>");
				out.print("<table class='news_list'>");
				out.print("<tr class='news_item'>" + "<td rowspan='2'>" 
						+ "<div class='image_container'><center>"
						+ "<img class='news_image' src='" + request.getContextPath() + image.getPath() + "'/></center></div></td>" 
						+ "<td><p class='news_title'>" + item.getTitle() + "</p></td></tr>"
						+ "<td><p align='justify' class='news_text'>" + item.getText() + "</p></td></tr></tr>");
				out.print("</table>");
				out.print("</a>");
				out.print("</nav>");
			}
			out.print("<center>");
			for (int i = 0; i < getPageCount(all.size()); i++)
				out.print("<a href='conference?page=" + (i + 1) + "' class='button_page'>" + (i + 1) + "</a>");
			out.print("</center>");
		} catch (DBException e) {
			e.printStackTrace();
		}
		out.print(Template.setFooter());
		out.close();
	}
	
	private int getPageCount(int listLength) {
		if (listLength % total == 0)
			return listLength / total;
		else
			return listLength / total + 1;
	}
	
	private String getTitleValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		String res = "";
		switch (lang) {
		case "ru":
			res = Template.TITLE_SCIENCE_CONFERENCES_RU;
			break;
		case "en":
			res = Template.TITLE_SCIENCE_CONFERENCES_EN;
			break;
		default:
			res = Template.TITLE_SCIENCE_CONFERENCES_UA;
		}
		return res;
	}

}
