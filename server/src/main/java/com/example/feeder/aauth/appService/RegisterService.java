package com.example.feeder.aauth.appService;

import com.example.feeder.aauth.User;
import com.example.feeder.aauth.UserRepository;
import com.example.feeder.aauth.exception.UserExistsException;
import com.example.feeder.aauth.request.RegisterRequest;
import com.example.feeder.aauth.response.MessageResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public RegisterService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public MessageResponse registerUser(RegisterRequest signUpRequest) {

        String username = signUpRequest.getUsername();
        String email = signUpRequest.getEmail();

        if (userRepository.existsUserByUsername(username)) {
            String message = String.format("Error: Username [%s] is already taken!", username);
            throw new UserExistsException(message);
        }

        if (userRepository.existsUserByEmail(email)) {
            String message = String.format("Error: Email [%s] is already in use!", email);
            throw new UserExistsException(message);
        }

        // Create new user's account, save into DB
        User user = createUser(signUpRequest);
        userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }

    private User createUser(RegisterRequest request) {
        return new User(
                request.getUsername(),
                request.getEmail(),
                encoder.encode(request.getPassword()),
                request.getRole());
    }
}
