package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Roles;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.DTO.RegisterDTO;
import vn.hoidanit.laptopshop.repository.RoleRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String handleHello() {
        return " Hello from Service";
    }

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public List<User> getUserEmail(String email) {
        return this.userRepository.findOneByEmail(email);
    }

    public User gettingById(long id) {
        return this.userRepository.findById(id);
    }

    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
    }

    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public Roles findRolesName(String name) {
        return this.roleRepository.findByName(name);
    }

    public User RegisterDTOtoUser(RegisterDTO registerDTO) {
        User userDTO = new User();
        userDTO.setFullName(registerDTO.getFirstName() + registerDTO.getLastName());
        userDTO.setEmail(registerDTO.getEmail());
        userDTO.setPassword(registerDTO.getPassword());
        return userDTO;
    }

    public boolean checkExistEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
