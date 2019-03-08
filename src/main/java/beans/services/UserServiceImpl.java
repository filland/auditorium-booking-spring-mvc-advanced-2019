package beans.services;

import beans.daos.UserDAO;
import beans.enums.Role;
import beans.models.Ticket;
import beans.models.User;
import beans.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/1/2016
 * Time: 7:30 PM
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final double EMPTY_ACCOUNT = 0;


    private final UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    public UserServiceImpl(@Qualifier("userDAO")UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User register(User user) {

        if (null == user.getRoles()){

            System.out.println("user's roles is null, adding default role");
            user.setRoles(Role.ROLE_REGISTERED_USER.name());
        }

        String password = user.getPassword();
        String encodedPass = passwordEncoder.encode(password);

        user.setPassword(encodedPass);

        User createdUser = userDAO.create(user);

        System.out.println("created user = "+createdUser);

        UserAccount userAccount = new UserAccount(createdUser.getId(), EMPTY_ACCOUNT);

        userAccountService.create(userAccount);

        return createdUser;
    }

    @Override
    public void remove(User user) {
        userDAO.delete(user);
    }

    @Override
    public User getById(long id) {
        return userDAO.get(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    @Override
    public User getByName(String username) {
        return userDAO.getByName(username);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userDAO.getAllByName(name);
    }

    @Override
    public List<Ticket> getBookedTickets() {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
