import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class LoginController {

    private UserService userService;

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
        //String expectedPasswordHash = getPasswordHashFromDatabase(username);
        // authenticate login here, check againsta a database
        // Compare the hashed password with the one entered by the user
        // return expectedPasswordHash.equals(hashPassword(password));
        return storedPasswordHash != null && storedPasswordHash.equals(hashPassword(password));

    }
    private String getPasswordHashFromDatabase(String username){
        // retrieve hashed password from database
        return "storedPasswordHash"; // Placeholder
    }
    private String hashPassword(String password){
        // Apply hashing algorithm
        private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password); // Placeholder
    }

    public String toString(){
        return "login";
    }
}