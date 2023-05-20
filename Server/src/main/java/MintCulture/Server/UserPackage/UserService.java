package MintCulture.Server.UserPackage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getByUserState(UserState state){
        return userRepository.findByState(state);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
