import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String getPasswordHashFromDatabase(String username) {
        User user = userRepository.findByUsername(username);
        return user != null ? user.getPasswordHash() : null;
    }
}
