package beans.soap;

import beans.services.UserService;
import beans.soap.classes.GetRegisterUserRequest;
import beans.soap.classes.GetRegisterUserResponse;
import beans.soap.classes.GetRemoveUserByIdRequest;
import beans.soap.classes.GetRemoveUserByIdResponse;
import beans.soap.classes.GetUserByUsernameRequest;
import beans.soap.classes.GetUserByUsernameResponse;
import beans.soap.classes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://tutorialspoint/schemas";

    @Autowired
    private UserService userServiceImpl;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRegisterUserRequest")
    @ResponsePayload
    public GetRegisterUserResponse register(@RequestPayload GetRegisterUserRequest request) {

        System.out.println("UserEndpoint.register() called");

        beans.models.User newUser = new beans.models.User();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setRoles("USER_ROLE");
        newUser.setPassword(request.getPassword());
        newUser.setBirthday(java.time.LocalDate.now());

        GetRegisterUserResponse response = new GetRegisterUserResponse();
        try {
            userServiceImpl.register(newUser);
            response.setRegistered(true);
        } catch (Throwable e) {
            response.setRegistered(false);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRemoveUserByIdRequest")
    @ResponsePayload
    public GetRemoveUserByIdResponse removeById(@RequestPayload GetRemoveUserByIdRequest request) {

        System.out.println("UserEndpoint.removeById() called");

        GetRemoveUserByIdResponse response = new GetRemoveUserByIdResponse();

        try {
            beans.models.User userToBeRemoved = userServiceImpl.getById(request.getId());

            beans.models.User user = new beans.models.User();
            user.setId(request.getId());
            user.setName(userToBeRemoved.getName());
            user.setBirthday(userToBeRemoved.getBirthday());
            user.setPassword(userToBeRemoved.getPassword());
            user.setEmail(userToBeRemoved.getEmail());
            user.setRoles(userToBeRemoved.getRoles());

            userServiceImpl.remove(user);
            response.setRemoved(true);
        } catch (Exception e) {
            e.printStackTrace();
            response.setRemoved(false);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByUsernameRequest")
    @ResponsePayload
    public GetUserByUsernameResponse getByName(@RequestPayload GetUserByUsernameRequest request) {

        System.out.println("UserEndpoint.getByName() called");

        GetUserByUsernameResponse response = new GetUserByUsernameResponse();

        beans.models.User foundUser = userServiceImpl.getByName(request.getName());

        User user = new User();
        user.setId(foundUser.getId());
//        user1.setBirthday(foundUser.getBirthday());
        user.setEmail(foundUser.getEmail());
        user.setPassword(foundUser.getPassword());
        user.setRoles(foundUser.getRoles());

        response.setUser(user);

        return response;
    }

}
