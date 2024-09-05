package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;

    // Viet Code theo chuan Dependency Injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        // String test = this.userService.handleHello();
        // List<User> mailList = this.userService.getUserEmail("huydieu@gmail.com");
        // System.out.println(mailList);
        model.addAttribute("Aron", "test");
        return "hello";
    }

    // Create New User
    @RequestMapping("/admin/user/create")
    public String CreateUserPage(Model model) {
        // List<User> userList = this.userService.getAllUser();
        // System.out.println(userList);
        model.addAttribute("newUser", new User());
        return "/admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String creatingUserPage(Model model, @ModelAttribute("newUser") User userIT) {
        // System.out.println(userIT);
        this.userService.handleSaveUser(userIT);// => Save User vao database
        return "redirect:/admin/user"; // chuyen trang khong can cau query
    }

    // Read User ==> Show all user
    @RequestMapping("admin/user")
    public String tableUser(Model model) {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("users1", users);
        return "/admin/user/tableUser";
    }

    // Dung PathVariable ==> tao bien dong de co the chuyen trang theo id
    @RequestMapping("admin/user/{id}")
    public String detailUser(Model model, @PathVariable long id) {
        User userId = this.userService.gettingById(id);
        model.addAttribute("id", id);
        model.addAttribute("userId", userId);
        return "/admin/user/show";
    }

    // Update User
    @RequestMapping("admin/user/update/{id}")
    public String updateUser(Model model, @PathVariable long id) {
        User currentUser = this.userService.gettingById(id);
        model.addAttribute("currentUser", currentUser);
        return "/admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String PostUpdatingUser(Model model, @ModelAttribute("currentUser") User userUpdated) {
        User currentUser = this.userService.gettingById(userUpdated.getId());
        if (currentUser != null) {
            currentUser.setAddress(userUpdated.getAddress());
            currentUser.setFullName(userUpdated.getFullName());
            currentUser.setPhone(userUpdated.getPhone());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    // Delete User
    @GetMapping("/admin/user/delete/{id}")
    public String deleteUserPage(Model model, @PathVariable long id) {
        // User userDeleted = this.userService.gettingById(id);
        // model.addAttribute("userDeleted", userDeleted);
        model.addAttribute("id", id);
        User userDeleted = new User();
        userDeleted.setId(id);
        model.addAttribute("userDeleted", userDeleted);
        return "/admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser(Model model, @ModelAttribute("userDeleted") User user) {
        // System.out.println(user.getId());
        this.userService.deleteUser(user.getId());
        return "redirect:/admin/user";
    }

}
