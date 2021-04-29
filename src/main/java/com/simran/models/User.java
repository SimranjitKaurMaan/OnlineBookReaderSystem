package com.simran.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class User
{
    private final String id;
    private final String name;
    private LocalDateTime membershipExpiryDate;
    private MembershipStatus status;

    public User(String name)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.status = MembershipStatus.active;
        this.membershipExpiryDate = LocalDateTime.now().plusMonths(3);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", membershipExpiryDate=" + membershipExpiryDate +
                ", status=" + status +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setStatus(MembershipStatus status) {
        this.status = status;
    }

    public void setMembershipExpiryDate(LocalDateTime membershipExpiryDate) {
        this.membershipExpiryDate = membershipExpiryDate;
    }

    public MembershipStatus getStatus() {
        return status;
    }


    public LocalDateTime getMembershipExpiryDate() {
        return membershipExpiryDate;
    }

}
