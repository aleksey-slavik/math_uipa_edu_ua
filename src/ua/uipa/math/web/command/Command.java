package ua.uipa.math.web.command;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.uipa.math.exception.AppException;

public abstract class Command implements Serializable {

	private static final long serialVersionUID = -2979164222808641881L;

	public abstract String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, AppException;

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}
