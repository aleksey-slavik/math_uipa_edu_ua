package ua.uipa.math.web.command.ua;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.uipa.math.Path;
import ua.uipa.math.exception.AppException;
import ua.uipa.math.web.command.Command;

public class ConferencesCommand_UA extends Command {

	private static final long serialVersionUID = -3206511685799966007L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, AppException {
		return Path.PAGE_CONFERENCES_UA;
	}

}
