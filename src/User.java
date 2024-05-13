import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    private long id;

    private String username;
    private String passwordHash; // stores password hashed


    // getters and setters
    public long getId{
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPasswordHash(){
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
    }

}