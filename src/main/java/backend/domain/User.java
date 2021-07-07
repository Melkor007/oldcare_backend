package backend.domain;
import java.io.Serializable;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.validation.annotation.Validated;
@Validated
@Getter
@Setter
public class User implements Serializable{
    private static final long serialVersionUID = 7709777521459696137L;

    @NotBlank
    @Size(max = 10)
    private String username;

    @NotBlank
    @Size(max = 10)
    private String password;

    @NotBlank
    @Size(max = 10)
    private String role;

    @Email(message = "Email Address")
    @Size(max = 20)
    private String email;

    @Size(max = 1)
    private String gender;

    public User(String username, String password, String role, String email, String gender) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email= email;
        this.gender = gender;
    }

    public User() {
        this.setUsername("default");
        this.setPassword("default");
        this.setRole("norm");
    }
}
