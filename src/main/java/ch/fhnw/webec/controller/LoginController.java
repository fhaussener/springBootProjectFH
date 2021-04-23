package ch.fhnw.webec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    /*EXTERNAL (inspired from school example) : https://github.com/WebEngineering-FHNW/contact-list-security-netopyr/blob/master/src/main/java/ch/fhnw/webec/contactlistsecurity/controller/LoginController.java */
    //Gets mapping for login and gets state for error and logout
    @GetMapping("login")
    public ModelAndView login(@RequestParam(required = false) String error,
                              @RequestParam(required = false) String logout) {
        final Map<String, Object> model = new HashMap<>();
        model.put("error", error != null);
        model.put("logout", logout != null);
        return new ModelAndView("login", model);
    }
}
