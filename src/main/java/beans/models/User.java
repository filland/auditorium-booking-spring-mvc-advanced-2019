package beans.models;

import beans.enums.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/1/2016
 * Time: 7:35 PM
 */
public class User {

    private long id;
    private String email;
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthday;

    private String password;
    private String roles;

//    private UserAccount userAccount;

    public User() {
    }

    public User(long id, String email, String name, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
    }

    public User(long id, String email, String name, LocalDate birthday, String password, String roles) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.password = password;
        this.roles = roles;
    }

//    public User(String email, String name, LocalDate birthday, String password, String roles, UserAccount userAccount) {
//        id = -1;
//        this.email = email;
//        this.name = name;
//        this.birthday = birthday;
//        this.password = password;
//        this.roles = roles;
//        this.userAccount = userAccount;
//    }

    public User(String email, String name, LocalDate birthday) {
        this(-1, email, name, birthday);
    }

    public User withId(long id) {
        return new User(id, email, name, birthday);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<Role> getRolesList() {

        List<Role> list = new ArrayList<>();

        try {

            System.out.println("user's roles = " + roles);

            String[] roles = this.roles.split(",");

            if (roles.length == 0) {

                System.out.println("creating list singleton");

                return Collections.singletonList(Role.valueOf(this.roles));
            }

            for (String role : roles) {
                list.add(Role.valueOf(role));
            }

            return list;

        } catch (Throwable e) {
            return Collections.emptyList();
        }
    }

//    public UserAccount getUserAccount() {
//        return userAccount;
//    }
//
//    public void setUserAccount(UserAccount userAccount) {
//        this.userAccount = userAccount;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(email, user.email) &&
                Objects.equals(name, user.name) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(password, user.password) &&
                Objects.equals(roles, user.roles)
//                &&
//                Objects.equals(userAccount, user.userAccount)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, birthday, password, roles
//                ,
//                userAccount
        );
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
//                ", userAccount=" + userAccount +
                '}';
    }
}
