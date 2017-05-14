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

public class ConfItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if(lang == null){
			String id = request.getParameter("id");
			session.setAttribute("lang", "en");
			response.sendRedirect("conf_item?id=" + id);
		}
		else{
			getConfItemRequest(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void getConfItemRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		String confId = request.getParameter("id");
		int id = Integer.parseInt(confId);
		try {
			ConfItem conf = DBManager.getInstance().getConfById(id, request);
			out.print(Template.setTitle(conf.getTitle()));
			out.print(Template.setHeader(request));
			out.print(Template.setMenu(request));
			out.print("<div class='Information'>");
			List<Image> images = DBManager.getInstance().getConfImageById(conf.getId());
			out.print("<p class='news_title'>"+ conf.getTitle() + "</p>" +
					"<p align='justify' class='news_text'>" + conf.getText() + "</p>");
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
