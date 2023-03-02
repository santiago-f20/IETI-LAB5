package edu.eci.ieti.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.eci.ieti.app.Dto.UserDto;
import edu.eci.ieti.app.Entity.User;
import edu.eci.ieti.app.Exception.UserNotFoundException;
import edu.eci.ieti.app.Repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    User createUser(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    User findById(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new UserNotFoundException();
    }

    @GetMapping("/all")
    Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @PutMapping("/{id}")
    User updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            return userRepository.save(user);
        } else
            throw new UserNotFoundException();
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
        } else
            throw new UserNotFoundException();
    }

}
