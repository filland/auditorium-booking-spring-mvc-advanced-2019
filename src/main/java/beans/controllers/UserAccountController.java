package beans.controllers;

import beans.models.User;
import beans.models.UserAccount;
import beans.services.UserAccountService;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public ModelAndView openTransferMoneyPage() {

        return new ModelAndView("user-add-money");
    }


    @RequestMapping(path = "/account/add", method = RequestMethod.POST)
    public ModelAndView putMoneyOnAccount(Principal principal,
                                          @RequestParam("amount") double amount) {

        User user = userServiceImpl.getByName(principal.getName());

        UserAccount userAccount = userAccountService.get(user.getId());

        if (null == userAccount) {

            userAccountService.create(new UserAccount(user.getId(), amount));

        } else {

            double newAmount = userAccount.getAmount() + amount;
            System.out.println("updated amount = " + newAmount);

            userAccountService.update(
                    new UserAccount(user.getId(), newAmount)
            );
        }

        return new ModelAndView("redirect:/account");
    }

}
