package ua.uipa.math.web.servlet.departm;

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
import ua.uipa.math.db.entity.GuidanceItem;
import ua.uipa.math.db.entity.Image;
import ua.uipa.math.exception.DBException;

public class GuidanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			session.setAttribute("lang", "en");
			response.sendRedirect("guidance");
		} else
			getGuidanceItemRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getGuidanceItemRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		out.print(Template.setTitle(getTitleValue(request)));
		out.print(Template.setHeader(request));
		out.print(Template.setMenu(request));
		try {
			List<GuidanceItem> items = DBManager.getInstance().getGuidanceListItems(request);
			out.print("<div class='Information'>");
			for (GuidanceItem item : items) {
				List<Image> images = DBManager.getInstance().getGuidanceImageById(item.getId());
				out.print("<p style='cursor:pointer; font-family:franklin gothic medium;font-size: 18px' onClick='expandit(this)'><strong id='Name_F'>" 
						+ item.getTitle() + "▼</strong></p><span style='display:none' style=&{head};>");
				out.print("<p class= 'under'><p align='justify' class='Name'>" + item.getText() + "</p></p>");
				out.print("<p class= 'under'><div class='gallery'>");
				for (int i = 0; i < images.size(); i++) {
					out.println("<a tabindex='" + (i + 1) + "'><img src='" 
							+ request.getContextPath() 
							+ images.get(i).getPath() + "'></a>");
				}
				out.print("</div></p></span>");
			}
			out.print("</div>" + "<script language='JavaScript1.2' src='script/expand.js'></script>");
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
			res = Template.TITLE_DEPARTMENT_GUIDANCE_RU;
			break;
		case "en":
			res = Template.TITLE_DEPARTMENT_GUIDANCE_EN;
			break;
		default:
			res = Template.TITLE_DEPARTMENT_GUIDANCE_UA;
		}
		return res;
	}
}
