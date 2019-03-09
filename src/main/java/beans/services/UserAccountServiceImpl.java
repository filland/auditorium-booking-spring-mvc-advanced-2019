package beans.services;

import beans.daos.UserAccountDAO;
import beans.exceptions.NotEnoughMoneyException;
import beans.models.Ticket;
import beans.models.User;
import beans.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    @Qualifier("userAccountDAO")
    private UserAccountDAO userAccountDAO;

    @Autowired
    private UserService userServiceImpl;

    @Override
    public UserAccount create(UserAccount userAccount) {
        return userAccountDAO.create(userAccount);
    }

    @Override
    public UserAccount get(long userId) {
        return userAccountDAO.get(userId);
    }

    @Override
    public UserAccount get(String userName) {

        User user = userServiceImpl.getByName(userName);

        return userAccountDAO.get(user.getId());
    }

    @Override
    public UserAccount update(UserAccount userAccount) {
        return userAccountDAO.update(userAccount);
    }

    @Override
    public void delete(UserAccount userAccount) {
        userAccountDAO.delete(userAccount);
    }

    @Override
    public void pay(Ticket ticket) {

        Double price = ticket.getPrice();

        UserAccount userAccount = userAccountDAO.get(ticket.getUser().getId());

        double userAccountAmount = userAccount.getAmount();

        if (userAccountAmount > price){

            userAccount.setAmount(userAccountAmount - price);
            userAccountDAO.update(userAccount);

        } else {

            throw new NotEnoughMoneyException("User does not have enough money to pay for the tickets");
        }
    }
}
