package com.toaa.walletcontrol.controller;

import com.toaa.walletcontrol.model.login.User;
import com.toaa.walletcontrol.model.view.Fragment;
import com.toaa.walletcontrol.service.CategoryService;
import com.toaa.walletcontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (!bindingResult.hasErrors()) {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
        }
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = {"/", "/calendar"}, method = RequestMethod.GET)
    public ModelAndView calendar() {
        ModelAndView modelAndView = new ModelAndView("master");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("categories", categoryService.getAllActive());
        modelAndView.addObject("fragment", new Fragment("calendar", "Calendar", "instance"));
        return modelAndView;
    }

    @RequestMapping(value = "/reporting", method = RequestMethod.GET)
    public ModelAndView reporting() {
        ModelAndView modelAndView = new ModelAndView("master");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("fragment", new Fragment("reporting", "Reporting", "instance"));
        return modelAndView;
    }

    @RequestMapping(value = "/admin/settings", method = RequestMethod.GET)
    public ModelAndView settings() {
        ModelAndView modelAndView = new ModelAndView("master");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("fragment", new Fragment("settings", "Settings", "instance"));
        return modelAndView;
    }
}
