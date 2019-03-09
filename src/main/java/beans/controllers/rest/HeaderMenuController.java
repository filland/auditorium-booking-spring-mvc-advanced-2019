package beans.controllers.rest;

import beans.models.UserAccount;
import beans.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/menu")
public class HeaderMenuController {

    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(path = "/data", method = RequestMethod.GET)
    public Map getDataForHeader(Principal principal){

        System.out.println("updating header menu");

        Map<String, String> map = new HashMap<>();

        UserAccount userAccount = userAccountService.get(principal.getName());

        map.put("username", principal.getName());
        map.put("account_balance", String.valueOf(userAccount.getAmount()));

        return map;
    }

}
