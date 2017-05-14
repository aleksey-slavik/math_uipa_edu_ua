package ua.uipa.math.web.servlet.news;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.uipa.math.Template;
import ua.uipa.math.db.DBManager;
import ua.uipa.math.db.entity.Image;
import ua.uipa.math.db.entity.NewsItem;
import ua.uipa.math.exception.DBException;

public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int total = 3;
	private int length = 200;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if(lang == null){
			String page = request.getParameter("page");
			session.setAttribute("lang", "en");
			response.sendRedirect("news?page=" + page);
		}
		else{
			getNewsListRequest(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getNewsListRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
			List<NewsItem> all = DBManager.getInstance().getNewsItems(request);
			List<NewsItem> newsList = DBManager.getInstance().getNewsItems(total, id, request);
			for (NewsItem item : newsList) {
				Image image = Template.checkImage(DBManager.getInstance().getSimpleNewsImageById(item.getId()));
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
				Date date = new Date(item.getDate());
				out.print("<nav id='table'>");
				out.print("<a href='news_item?id=" + item.getId() + "'>");
				out.print("<table class='news_list'>");
				out.print("<tr class='news_item'>" + 
						"<td rowspan='3'>" + 
						"<div class='image_container'><center>" +
						"<img class='news_image' src='" + request.getContextPath() + image.getPath() + "'/></center></div></td>" + 
						"<td><p class='news_title'>" + item.getTitle() + "</p></td></tr>" + 
						"<td><p class='news_date'>" + dateFormat.format(date) + "</p></td></tr>" + 
						"<td><p align='justify' class='news_text'>" + getText(item.getText()) + "</p></td></tr></tr>");
				out.print("</table></a>");
				out.print("</nav>");
			}
			out.print("<center>");
			for (int i = 0; i < getPageCount(all.size()); i++)
				out.print("<a href='news?page=" + (i + 1) + "' class='button_page'>" + (i + 1) + "</a>");
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
			res = Template.TITLE_NEWS_RU;
			break;
		case "en":
			res = Template.TITLE_NEWS_EN;
			break;
		default:
			res = Template.TITLE_NEWS_UA;
		}
		return res;
	}
	
	private String getText(String input){
		return input.length() > length ? input.substring(0, length - 1) + "..." : input;
	}
}
