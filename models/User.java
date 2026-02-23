package models;

public class User {
    private int userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String status;
    private Integer accountId;
    private String image_path;

    // أضف هذا داخل كلاس User
    public User(int userId, String username, String password, String firstName, String lastName, String email, String role, String status, Integer accountId,String image_path) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.status = status;
        this.accountId = accountId;
        this.image_path = image_path;
    }

    // نصيحة: اترك الـ Default Constructor موجوداً (للاحتياط)
    public User() {}

    // Getters & Setters

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }


    public String getImage_Path() {
return image_path;    }
    public void setImage(String image_path) {
        this.image_path = image_path;
    }
}

































/*
package models;

import java.sql.Timestamp;

public class User {
    private int userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String role;       // admin أو student
    private String status;     // active أو inactive
    private Timestamp createdAt;

    // Constructor
    public User(int userId, String username, String password, String firstName, String lastName, String email,
                String role, String status, Timestamp createdAt) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
    }

    public User() {

    }

    // Getters & Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
*/
