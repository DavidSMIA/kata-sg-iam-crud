package kata.sg.iam.controller;


import io.swagger.v3.oas.annotations.Operation;
import kata.sg.iam.model.dto.user.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Operation(summary = "Get all users", tags = { "user" })
    @GetMapping
    public List<UserDTO> getUsers() {
        return null;
    }

    @Operation(summary = "Get user by id", tags = { "user" })
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") String userId) {
        return null;
    }

    @Operation(summary = "Update user", tags = { "user" })
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable("id") String userId, UserDTO userPayload) {
        return null;
    }

    @Operation(summary = "Create user", tags = { "user" })
    @PostMapping
    public UserDTO createUser(UserDTO userPayload) {
        return null;
    }


}
