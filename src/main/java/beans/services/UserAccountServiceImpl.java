package beans.services;

import beans.daos.UserAccountDAO;
import beans.exceptions.NotEnoughMoneyException;
import beans.models.Ticket;
import beans.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    @Qualifier("userAccountDAO")
    private UserAccountDAO userAccountDAO;

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public UserAccount create(UserAccount userAccount) {
        return userAccountDAO.create(userAccount);
    }

    @Override
    public UserAccount get(long userId) {
        return userAccountDAO.get(userId);
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

        System.out.println("ticket price is "+price);

        UserAccount userAccount = userAccountDAO.get(ticket.getUser().getId());

        double userAccountAmount = userAccount.getAmount();

        System.out.println("user has = "+userAccountAmount);

        if (userAccountAmount > price){

            userAccount.setAmount(userAccountAmount - price);
            userAccountDAO.update(userAccount);
            System.out.println("user account balance updated");

        } else {

            System.out.println("throwing an exception");
            throw new NotEnoughMoneyException("User does not have enough money to pay for the tickets");
        }
    }
}
