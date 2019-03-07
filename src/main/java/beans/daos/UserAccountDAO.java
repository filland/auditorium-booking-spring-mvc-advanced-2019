package beans.daos;

import beans.models.UserAccount;

public interface UserAccountDAO {

    UserAccount create(UserAccount userAccount);

    UserAccount get(long userId);

    UserAccount update(UserAccount userAccount);

    void delete(UserAccount userAccount);

    static void validate(UserAccount userAccount) {

        if (userAccount.getAmount() < 0){
            throw new IllegalArgumentException("amount cannot be less than 0");
        }

        if (userAccount.getUserId() <= 0){
            throw new IllegalArgumentException("id cannot be less or equal zero");
        }
    }
}
