package MintCulture.Server.TestPackage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {
    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }
}
