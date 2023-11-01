package lv.digitalbear.sitedemo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Data
@AllArgsConstructor
//@Entity
//@Table
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nickName;
    private String password;
    private String email;
    private Roles userRole;

//    public UserEntity(Long id, String nickName, String password, String email, Roles userRole) {
//        this.id = id;
//        this.nickName = nickName;
//        this.password = password;
//        this.email = email;
//        this.userRole = userRole;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setNickName(String nickName) {
//        this.nickName = nickName;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setUserRole(Roles userRole) {
//        this.userRole = userRole;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getNickName() {
//        return nickName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public Roles getUserRole() {
//        return userRole;
//    }
//
//    @Override
//    public String toString() {
//        return "UserEntity{" +
//                "id=" + id +
//                ", nickName='" + nickName + '\'' +
//                ", password='" + password + '\'' +
//                ", email='" + email + '\'' +
//                ", userRole=" + userRole +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof UserEntity that)) return false;
//        return Objects.equals(id, that.id) && Objects.equals(nickName, that.nickName) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && userRole == that.userRole;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, nickName, password, email, userRole);
//    }
}
