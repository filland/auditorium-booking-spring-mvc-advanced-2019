package beans.services;

import beans.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {


            beans.models.User userByName = userServiceImpl.getByName(username);

            List<GrantedAuthority> authorities = new ArrayList<>();

            for (Role role : userByName.getRolesList()) {
                authorities.add(new SimpleGrantedAuthority(role.name()));
            }

            UserDetails userDetails = new User(
                    userByName.getName(),
                    userByName.getPassword(),
                    authorities
            );

            return userDetails;
        } catch (Throwable e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
    }
}
