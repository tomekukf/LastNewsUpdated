package LastNews.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LastNews.model.User;
import LastNews.service.NewsService;


@WebServlet("/add")
public class NewsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getUserPrincipal() != null) {
			request.getRequestDispatcher("WEB-INF/add.jsp").forward(request, response);

		} else {
			response.sendError(403);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("inputName");
		String description = request.getParameter("inputDescription");
		String url = request.getParameter("inputUrl");
		User authenticatedUser = (User) request.getSession().getAttribute("user");
		if (request.getUserPrincipal() != null) {
			NewsService newsService = new NewsService();
			newsService.addNews(name, description, url, authenticatedUser);
			response.sendRedirect(request.getContextPath() + "/");

		} else {
			response.sendError(403);
		}

	}

}
