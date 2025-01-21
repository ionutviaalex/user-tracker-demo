package com.example.user_tracking_demo.model;

public class TotalUsers {
    public int totalUsers;

    public TotalUsers() {/*empty*/}

    public TotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    @Override
    public String toString() {
        return "TotalUsers{" +
                "totalUsers=" + totalUsers +
                '}';
    }
}
