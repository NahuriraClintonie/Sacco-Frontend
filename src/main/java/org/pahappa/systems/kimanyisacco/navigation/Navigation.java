package org.pahappa.systems.kimanyisacco.navigation;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "navigation")
@ApplicationScoped
public class Navigation {

    public static final String DASHBOARD = "/pages/dashboard/Dashboard.xhtml";
    public static final String LOGIN = "/pages/authenticate/login.xhtml";
    public static final String LANDING = "/pages/landing/Landing.xhtml";
    public static final String DEPOSIT = "/pages/dashboard/Deposit.xhtml";
    public static final String WITHDRAW = "/pages/dashboard/Withdraw.xhtml";
    public static final String SETTINGS = "/pages/dashboard/Settings.xhtml";
    public static final String TRANSACT = "/pages/dashboard/transaction.xhtml";
    public static final String ADMIN = "/pages/admin/AdminDashboard.xhtml";

    public String getDashboard() {
        return DASHBOARD;
    }

    public String getLanding() {
        return LANDING;
    }

    public String getDeposit() {
        return DEPOSIT;
    }

    public String getWithdraw() {
        return WITHDRAW;
    }

    public String getSettings() {
        return SETTINGS;
    }

    public String getLogin() {
        return LOGIN;
    }

    public String getTransaction() {
        return TRANSACT;
    }
}
