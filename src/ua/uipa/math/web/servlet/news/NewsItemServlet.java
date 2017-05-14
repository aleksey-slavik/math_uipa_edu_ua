package ua.uipa.math.web.servlet.news;

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
import ua.uipa.math.db.entity.NewsItem;
import ua.uipa.math.exception.DBException;

public class NewsItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if(lang == null){
			String id = request.getParameter("id");
			session.setAttribute("lang", "en");
			response.sendRedirect("news_item?id=" + id);
		}
		else
		getNewsItemRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getNewsItemRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		String newsId = request.getParameter("id");
		int id = Integer.parseInt(newsId);
		try {
			NewsItem news = DBManager.getInstance().getNewsById(id, request);
			out.print(Template.setTitle(news.getTitle()));
			out.print(Template.setHeader(request));
			out.print(Template.setMenu(request));
			out.print("<div class='Information'>");
			List<Image> images = DBManager.getInstance().getNewsImageById(news.getId());
			out.print("<p class='news_title'>"+ news.getTitle() + "</p>" +
					"<p align='justify' class='news_text'>" + news.getText() + "</p>");
			out.print("<div class='gallery'>");
			for(int i = 0; i < images.size(); i++){
				out.println("<a tabindex='" + (i + 1) + "'><img src='"+ request.getContextPath() + images.get(i).getPath() + "'></a>");
			}
			out.print("</div></div>");
		} catch (DBException e) {
			e.printStackTrace();
		}
		out.print(Template.setFooter());
		out.close();
	}
}
