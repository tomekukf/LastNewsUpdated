package LastNews.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LastNews.model.News;
import LastNews.model.User;
import LastNews.model.Vote;
import LastNews.model.VoteType;
import LastNews.service.NewsService;
import LastNews.service.VoteService;

/**
 * Servlet implementation class VoteController
 */
@WebServlet("/vote")
public class VoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User loggedUser = (User) request.getSession().getAttribute("user");
		if (loggedUser != null) {
			VoteType voteType = VoteType.valueOf(request.getParameter("vote"));
			long userId = loggedUser.getId();
			long newsId = Long.parseLong(request.getParameter("news_id"));
			updateVote(userId, newsId, voteType);
		}
		 response.sendRedirect(request.getContextPath() + "/");
	}

	private void updateVote(long userId, long newsId, VoteType voteType) {
		VoteService voteService = new VoteService();
		Vote existingVote = voteService.getVoteByNewsIdAndUserId(newsId, userId);
		Vote updatedVote = voteService.addOrUpdateVote(userId, newsId, voteType);
		if (existingVote != updatedVote || !updatedVote.equals(existingVote)) {
			updateNews(newsId, existingVote, updatedVote);
		}
	}

	private void updateNews(long newsId, Vote oldVote, Vote newVote) {
		NewsService newsSerivce = new NewsService();
		News newsById = newsSerivce.getNewsById(newsId);
		News updatedNews = null;
		if (oldVote == null && newVote != null) {
			updatedNews = addNewsVote(newsById, newVote.getVoteType());
		} else if (oldVote != null && newVote != null) {
			updatedNews = removeNewsVote(newsById, oldVote.getVoteType());
			updatedNews = addNewsVote(newsById, newVote.getVoteType());
		}
	newsSerivce.updateNews(updatedNews);
	}

	private News removeNewsVote(News news, VoteType voteType) {
		News newsCopy = new News(news);
		if(voteType == VoteType.VOTE_UP) {
			newsCopy.setUpVote(newsCopy.getDownVote()-1);
		}else if ( voteType == VoteType.VOTE_DOWN) {
			newsCopy.setDownVote(newsCopy.getUpVote() +1);
		}

		return newsCopy;
	}

	private News addNewsVote(News news, VoteType voteType) {
		News newsCopy = new News(news);
		if(voteType == VoteType.VOTE_UP) {
			newsCopy.setUpVote(newsCopy.getUpVote()+1);
		}else if ( voteType == VoteType.VOTE_DOWN) {
			newsCopy.setDownVote(newsCopy.getDownVote() -1);
		}

		return newsCopy;
	}

}
