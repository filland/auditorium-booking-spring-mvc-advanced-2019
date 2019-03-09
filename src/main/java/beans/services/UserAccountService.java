package beans.services;

import beans.models.Ticket;
import beans.models.UserAccount;

public interface UserAccountService {

    UserAccount create(UserAccount userAccount);

    UserAccount get(long userId);

    UserAccount get(String userName);

    UserAccount update(UserAccount userAccount);

    void delete(UserAccount userAccount);

    void pay(Ticket ticket);

}
