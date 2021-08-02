package kwy.taxi.allocation.model;

import kwy.taxi.allocation.utils.UserType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name = "USER_SEQ_GENERATOR",
        sequenceName = "USER_SEQ",
        initialValue = 1,
        allocationSize = 1)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
    private int id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    private String passwordEncoding(String password) {
        return "string";
    }

    public User() {
    }

    public User(String email, String password, UserType userType){
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public static class Builder {
        private int id;
        private String email;
        private String password;
        private UserType userType;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder() {
        }

        public static Builder anUser() {
            return new Builder();
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder userType(UserType userType) {
            this.userType = userType;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public User build() {
            User user = new User(email, password, userType);
            user.setId(id);
            user.setCreatedAt(createdAt);
            user.setUpdatedAt(updatedAt);
            return user;
        }
    }
}
