package MintCulture.Server.UserPackage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    @GetMapping("/{state}")
//    public User getUserByState(@PathVariable UserState state){
//        return userService.getByUserState(state);
//
//    }

    @GetMapping("/{state}")
    public ResponseEntity<User> getUserByState(@PathVariable UserState state){
        User user = userService.getByUserState(state);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

}
