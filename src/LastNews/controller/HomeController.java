package LastNews.controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LastNews.model.News;
import LastNews.service.NewsService;

@WebServlet("")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		saveNewsInRequest(request);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
	
	
	}
	 private void saveNewsInRequest(HttpServletRequest request) {
	        NewsService newsService = new NewsService();
	        List<News> allNewses = newsService.getAllNewses(new Comparator<News>() {
	            //more votes = higher
	            @Override
	            public int compare(News d1, News d2) {
	                int d1Vote = d1.getUpVote() + d1.getDownVote();
	                int d2Vote = d2.getUpVote() + d2.getDownVote();
	                if(d1Vote < d2Vote) {
	                    return 1;
	                } else if(d1Vote > d2Vote) {
	                    return -1;
	                }
	                return 0;
	            }
	        });
	        request.setAttribute("newses", allNewses);

	 }
}
