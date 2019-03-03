package beans.services;

import beans.daos.UserDAO;
import beans.enums.Role;
import beans.models.Ticket;
import beans.models.User;
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

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {

        if (null == user.getRoles()){

            System.out.println("user's roles is null, adding default role");
            user.setRoles(Role.ROLE_REGISTERED_USER.name());
        }

        System.out.println("user = "+user);

        String password = user.getPassword();
        System.out.println("pain text pass = "+password);
        String encodedPass = passwordEncoder.encode(password);

        System.out.println("pass after encryption = "+encodedPass);

        user.setPassword(encodedPass);


        return userDAO.create(user);
    }

    public void remove(User user) {
        userDAO.delete(user);
    }

    public User getById(long id) {
        return userDAO.get(id);
    }

    public User getUserByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    @Override
    public User getByName(String username) {
        return userDAO.getByName(username);
    }

    public List<User> getUsersByName(String name) {
        return userDAO.getAllByName(name);
    }

    public List<Ticket> getBookedTickets() {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
