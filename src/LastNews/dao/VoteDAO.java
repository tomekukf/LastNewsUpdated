package LastNews.dao;

import java.util.List;

import LastNews.model.Vote;

public interface VoteDAO extends GenericDAO<Vote, Long>{
	List<Vote> getAll();
	public Vote getVoteByUserIdNewsId(long UserId,long NewsId);
}
