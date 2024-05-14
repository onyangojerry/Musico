import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginPage(){
        return "login"; // returns the login page (login.html)
    }

    @PostMapping("/login")
    public ModelAndView processLogin(@RequestParam("username") String username, @RequestParam("password") String password ){
        boolean isAuthenticated = authenticate(username, password);

        if(isAuthenticated){
            // Redirect to the dashboard if authenticated
            return new ModelAndView("redirect:/dashboard");
        }else{
            // Return to login page with an error message if authentication fails
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "Invalid username or password");
            return modelAndView;
        }
    }

    private boolean authenticate(String username, String password){
        String storedPasswordHash = userService.getPasswordHashFromDatabase(username);
        //return storedPasswordHash != null && storedPasswordHash.equals(hashPassword(password));
        return passwordEncoder.matches(password, storedPasswordHash);
    }

    private String getPasswordHashFromDatabase(String username){
        // retrieve hashed password from database
        return "storedPasswordHash"; // Placeholder
    }
    private String hashPassword(String password){
        // Apply hashing algorithm
        passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password); // Placeholder
    }

    public String toString(){
        return "login";
    }
}