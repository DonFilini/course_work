package com.example.hs;
import com.example.config.UserInfo;
import com.example.config.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AppController {

    private final HsService hsService;

    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public String addNewUser(@ModelAttribute UserInfo userInfo, @RequestParam String name, @RequestParam String roles, HttpSession session) {
        System.out.println("UserInfo object is: " + userInfo);
        userService.addUser(userInfo);
        session.setAttribute("username", name);
        session.setAttribute("roles", roles);
        return "redirect:/";
    }
    @GetMapping("/auth/register")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "register";
    }
    @GetMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Hs> listHs = hsService.listAll(keyword);
        model.addAttribute("listHs", listHs);
        model.addAttribute("keyword", keyword);
        return "index";
    }
    @GetMapping("/new")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showNewClientForm(Model model) {
        Hs hs = new Hs();
        model.addAttribute("hs", hs);
        return "new_hs";
    }
    @PostMapping("/save")
    public String saveHs(@ModelAttribute("hs") Hs hs) {
        hsService.save(hs);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView showEditClientForm(@PathVariable(name="id") Long id) {
        ModelAndView mav = new ModelAndView("edit_hs");
        Hs hs = hsService.get(id);
        mav.addObject("Hs", hs);
        return mav;
    }
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable(name = "id") Long id) {
        hsService.delete(id);
        return "redirect:/";
    }
    @GetMapping("/login_page")
    public String showLogin() {
        return "login_page";
    }
    @GetMapping("/about_us")
    public String showAbout() {
        return "about_us";
    }
    @PostMapping("/login_page")
    public String SuccessLogin(@RequestParam String username, HttpSession session) {
        System.out.println("Username is: " + username);
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        session.setAttribute("username", currentUser);
        return "redirect:/";
    }
}


