package beans.soap.client;

import beans.soap.classes.GetRegisterUserRequest;
import beans.soap.classes.GetRegisterUserResponse;
import beans.soap.classes.GetRemoveUserByIdRequest;
import beans.soap.classes.GetRemoveUserByIdResponse;
import beans.soap.classes.GetUserByUsernameRequest;
import beans.soap.classes.GetUserByUsernameResponse;
import beans.soap.classes.LocalDate;
import beans.soap.classes.User;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import javax.xml.soap.MessageFactory;

public class SoapClient {

    private String linkToService;

    private WebServiceTemplate webServiceTemplate;

    public SoapClient(String linkToService) {
        this.linkToService = linkToService;
    }

    public void init() throws Exception {

        SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory(
                MessageFactory.newInstance());
        messageFactory.afterPropertiesSet();

        webServiceTemplate = new WebServiceTemplate(messageFactory);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setPackagesToScan("beans.soap.classes");
        marshaller.afterPropertiesSet();

        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);
        webServiceTemplate.afterPropertiesSet();
    }

    public boolean registerUser(User user) {
        try {

            GetRegisterUserRequest registerUserRequest = new GetRegisterUserRequest();
            registerUserRequest.setName(user.getName());
            registerUserRequest.setBirthday(user.getBirthday().toString());
            registerUserRequest.setEmail(user.getEmail());
            registerUserRequest.setPassword(user.getPassword());

            GetRegisterUserResponse response = (GetRegisterUserResponse) webServiceTemplate
                    .marshalSendAndReceive(
                            linkToService,
                            registerUserRequest);

            return response.isRegistered();

        } catch (Exception s) {
            s.printStackTrace();
            return false;
        }
    }

    public User getByUsername(String username) {

        try {

            GetUserByUsernameRequest getUserByUsernameRequest = new GetUserByUsernameRequest();
            getUserByUsernameRequest.setName(username);

            GetUserByUsernameResponse response = (GetUserByUsernameResponse) webServiceTemplate
                    .marshalSendAndReceive(
                            linkToService,
                            getUserByUsernameRequest);

            return response.getUser();

        } catch (Exception s) {
            return null;
        }
    }

    public boolean removeByUserId(long userId) {
        try {

            GetRemoveUserByIdRequest getRemoveUserByIdRequest = new GetRemoveUserByIdRequest();
            getRemoveUserByIdRequest.setId(userId);

            GetRemoveUserByIdResponse response = (GetRemoveUserByIdResponse) webServiceTemplate
                    .marshalSendAndReceive(
                            linkToService,
                            getRemoveUserByIdRequest);

            return response.isRemoved();

        } catch (Exception s) {
            s.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws Exception {

        SoapClient client = new SoapClient("http://localhost:8080/soap/getUser/");

        client.init();

        User user = new User();
        user.setName("Tim");
        user.setEmail("sfafsdf@mail.co");
        user.setBirthday(new LocalDate());
        user.setPassword("qeq232");
        user.setRoles("ROLE_USER");

        client.registerUser(user);

        System.out.println(client.getByUsername("Tim"));

        System.out.println(client.removeByUserId(1));

        System.out.println(client.getByUsername("Tim"));
    }
}
