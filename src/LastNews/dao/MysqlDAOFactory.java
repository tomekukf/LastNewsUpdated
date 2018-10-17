package LastNews.dao;

public class MysqlDAOFactory extends DAOFactory {

	@Override
	public NewsDAO getNewsDAO() {
		return new NewsDAOImpl();
	}

	@Override
	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

	@Override
	public VoteDAO getVoteDAO() {
		return new VoteDAOImpl();
	}

}
