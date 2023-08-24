package org.pahappa.systems.kimanyisacco.models;

import java.util.Date;

import javax.persistence.*;

import org.pahappa.systems.kimanyisacco.constants.Gender;

@Entity
@Table(name = "users")
public class User {

    private Long id;
    private String username;
    private String password;
    private Date dateOfBirth;
    private Gender gender;
    private String email;
    private String physicalAddress;
    // private String accountNumber;
    private String status;
    private Account account;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true, name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false, name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "date_of_birth")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(nullable = true, name = "gender")
    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(nullable = true, name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "physical_address")
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    // @Transient
    // public String getAccountNumber() {
    //     if (account != null) {
    //         return account.getAccountNumber();
    //     }
    //     return null;
    // }

    // public void setAccountNumber(String accountNumber) {
    //     this.accountNumber = accountNumber;
    // }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Transient
    public Account getAccount() {
        return account;
    }

}
