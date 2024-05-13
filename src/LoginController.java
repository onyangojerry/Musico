import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

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
        // authenticate login here, check againsta a database
        return "admin".equals(username) && "admin".equals(password);
    }
}