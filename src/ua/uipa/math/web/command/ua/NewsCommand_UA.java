package ua.uipa.math.web.command.ua;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.uipa.math.Path;
import ua.uipa.math.db.DBManager;
import ua.uipa.math.db.entity.NewsItem;
import ua.uipa.math.exception.AppException;
import ua.uipa.math.web.command.Command;

public class NewsCommand_UA extends Command{

	private static final long serialVersionUID = 3395688715521511957L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response){
		
		return Path.PAGE_NEWS_UA;
	}

}
