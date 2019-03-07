package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.UserAccountDAO;
import beans.models.UserAccount;
import org.springframework.stereotype.Repository;

@Repository(value = "userAccountDAO")
public class UserAccountDAOImpl extends AbstractDAO implements UserAccountDAO {

    @Override
    public UserAccount create(UserAccount userAccount) {

        UserAccountDAO.validate(userAccount);

        System.out.println(userAccount);

        long id = (Long) getCurrentSession().save(userAccount);

        userAccount.setUserId(id);

        return userAccount;
    }

    @Override
    public UserAccount get(long userId) {

        return getCurrentSession().get(UserAccount.class, userId);
    }

    @Override
    public UserAccount update(UserAccount userAccount) {
        return ((UserAccount) getCurrentSession().merge(userAccount));
    }

    @Override
    public void delete(UserAccount userAccount) {
        UserAccountDAO.validate(userAccount);
        getCurrentSession().delete(userAccount);
    }
}
