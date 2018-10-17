package LastNews.service;

import java.sql.Timestamp;
import java.util.Date;

import LastNews.dao.DAOFactory;
import LastNews.dao.VoteDAO;
import LastNews.model.Vote;
import LastNews.model.VoteType;

public class VoteService {

	public Vote addVote(long userId, long newsID, VoteType voteType) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDAO = factory.getVoteDAO();
		Vote vote = new Vote();

		vote.setNewsId(newsID);
		vote.setUserId(userId);
		vote.setVoteType(voteType);
		vote.setDate(new Timestamp(new Date().getTime()));
		voteDAO.create(vote);
		return vote;
	}



	public Vote updateVote(long userId, long newsID, VoteType voteType) {

		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDAO = factory.getVoteDAO();
		Vote voteToUpdate = voteDAO.getVoteByUserIdNewsId(userId, newsID);
		if (voteToUpdate != null) {
			voteToUpdate.setVoteType(voteType);
			voteDAO.update(voteToUpdate);
		}

		return voteToUpdate;

	}

	public Vote addOrUpdateVote(long userId, long newsID, VoteType voteType) {
		Vote resultVote = null;
		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDAO = factory.getVoteDAO();
		Vote vote = voteDAO.getVoteByUserIdNewsId(userId, newsID);
		if (vote == null) {
			resultVote = addVote(userId, newsID, voteType);
		} else if (vote != null) {
			resultVote = updateVote(userId, newsID, voteType);
		}

		return resultVote;
	}
	public Vote getVoteByNewsIdAndUserId(long newsId, long userId) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDAO = factory.getVoteDAO();
		Vote vote = voteDAO.getVoteByUserIdNewsId(userId, newsId);
				return vote;
	}
}
