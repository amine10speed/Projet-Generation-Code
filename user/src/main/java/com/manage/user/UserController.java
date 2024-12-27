package com.manage.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User storedUser = userService.findByUsername(user.getUsername());
        if (storedUser == null || !userService.checkPassword(user.getPassword(), storedUser.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
        String token = jwtUtil.generateToken(storedUser.getUsername(), storedUser.getRole());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@RequestHeader(value = "X-Authenticated-User", required = false) String username) {
        System.out.println("Received X-Authenticated-User header: " + username);

        if (username == null || username.isEmpty()) {
            System.out.println("Missing or empty X-Authenticated-User header");
            return ResponseEntity.badRequest().body(null); // Return 400 for missing header
        }

        User user = userService.findByUsername(username);
        if (user == null) {
            System.out.println("User not found for username: " + username);
            return ResponseEntity.notFound().build(); // Return 404 if user not found
        }

        System.out.println("Returning user details for username: " + username);
        return ResponseEntity.ok(user);
    }

}
