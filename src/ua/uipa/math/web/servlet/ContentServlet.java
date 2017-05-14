package ua.uipa.math.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.uipa.math.Path;
import ua.uipa.math.exception.AppException;
import ua.uipa.math.web.command.Command;
import ua.uipa.math.web.command.CommandContainer;

public class ContentServlet extends HttpServlet {
	
	private static final long serialVersionUID = -1448765552032625420L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter("command");
		Command command = CommandContainer.get(commandName);
		String forward = Path.PAGE_ERROR;
		try {
			forward=command.execute(request, response);
		} catch (AppException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		request.getRequestDispatcher(forward).forward(request, response);
	}
}
