package vn.hoidanit.laptopshop.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadFileService;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadFileService uploadFileService;
    private final PasswordEncoder passwordEncoder;

    // Viet Code theo chuan Dependency Injection
    public UserController(UserService userService, UploadFileService uploadFileService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadFileService = uploadFileService;
        this.passwordEncoder = passwordEncoder;
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
    @GetMapping("/admin/user/create")
    public String CreateUserPage(Model model) {
        // List<User> userList = this.userService.getAllUser();
        // System.out.println(userList);
        model.addAttribute("newUser", new User());
        return "/admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String creatingUserPage(Model model,
            @ModelAttribute("newUser") User userIT,
            @RequestParam("aronFile") MultipartFile file) {
        // System.out.println(userIT);
        String hashPassword = this.passwordEncoder.encode(userIT.getPassword());
        String linkAvatar = this.uploadFileService.handleUploadFile(file,
                "avatar");// Luu file anh

        userIT.setPassword(hashPassword);
        userIT.setAvatar(linkAvatar);
        userIT.setRole(this.userService.findRolesName(userIT.getRole().getName()));
        this.userService.handleSaveUser(userIT);// => Save User vao database

        return "redirect:/admin/user"; // redirect ==> chuyen huong sang request khac de xu ly
    }

    // Read User ==> Show all user
    @RequestMapping("admin/user")
    public String tableUser(Model model) {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("users1", users);
        return "/admin/user/show";
    }

    // Dung PathVariable ==> tao bien dong de co the chuyen trang theo id
    @RequestMapping("admin/user/{id}")
    public String detailUser(Model model, @PathVariable long id) {
        User userId = this.userService.gettingById(id);
        model.addAttribute("id", id);
        model.addAttribute("userId", userId);
        return "/admin/user/detail";
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
