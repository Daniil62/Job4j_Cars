package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cars.model.Account;
import ru.job4j.cars.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AccountController {

    private final UserService service;

    public AccountController(UserService service) {
        this.service = service;
    }

    @GetMapping("/registrationPage")
    public String registrationPage(Model model, HttpSession session) {
        addUserAttribute(model, session);
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute Account account,
                               Model model, HttpServletRequest req) {
        Optional<Account> regUser = service.create(account);
        String result = "redirect:/mainPage";
        if (regUser.isEmpty()) {
            model.addAttribute("error", true);
            result = "registrationPage";
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("account", regUser.get());
        }
        return result;
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model,
                            @RequestParam(name = "fail", required = false)
                            Boolean fail, HttpSession session) {
        addUserAttribute(model, session);
        model.addAttribute("fail", fail != null);
        return "loginPage";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Account account, HttpServletRequest req) {
        String result = "redirect:/mainPage";
        Optional<Account> accountDb = service
                .findByLoginAndPassword(account.getLogin(), account.getPassword());
        if (accountDb.isEmpty()) {
            result = "redirect:/loginPage?fail=true";
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("account", accountDb.get());
        }
        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/mainPage";
    }
    private void addUserAttribute(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            account = new Account();
            account.setName("Гость");
        }
        model.addAttribute("account", account);
    }
}