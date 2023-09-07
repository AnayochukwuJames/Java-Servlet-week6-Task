package Models;

import Enums.Gender;
import com.example.demofb.DAO.UserDAO;
import com.example.demofb.DTO.UserDTO;
import com.example.demofb.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

//@Data
//@AllArgsConstructor
//@Builder
public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
   private Gender gender;

    public User(Long id, String firstname, String lastname, String email, String password, Gender gender) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
