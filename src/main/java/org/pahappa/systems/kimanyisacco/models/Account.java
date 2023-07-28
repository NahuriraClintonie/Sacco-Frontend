package org.pahappa.systems.kimanyisacco.models;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    
    // account number
    @Id
    @Column(name = "account_number")
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // The user associated with the account
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // account balance attribute
    @Column(name = "account_balance", nullable = false)
    private Double accountBalance;

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
