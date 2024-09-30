package com.readyent.readyx.controller;

import com.readyent.readyx.domain.dto.request.UserRequest;
import com.readyent.readyx.domain.dto.response.UserResponse;
import com.readyent.readyx.domain.entity.User;
import com.readyent.readyx.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable int id) {
//        return userService.getUserById(id);
        UserResponse userResponse = new UserResponse();
        userResponse.setId((long) id);
        userResponse.setName("John Doe");
        return userResponse;
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @Operation(summary = "Get a user by name", description = "Retrieve a user based on their name")
    @GetMapping("/users")
    public String getUsers(
            @Parameter(
                    name = "name",
                    description = "The name of the user",
//                    in = ParametersIn.QUERY,
                    required = false,
                    schema = @Schema(defaultValue = "John Doe")
            )
            @RequestParam(required = false) String name) {
        return "Requested user: " + name;
    }

    @PostMapping("/users")
    public String createUser(@RequestBody UserRequest userRequest) {
        return "User created: " + userRequest.getName();
    }
}