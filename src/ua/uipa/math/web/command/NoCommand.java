package ua.uipa.math.web.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.uipa.math.Path;
import ua.uipa.math.exception.AppException;

public class NoCommand extends Command {

	private static final long serialVersionUID = -2557414157166106438L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, AppException {
		return Path.PAGE_ERROR;
	}

}
