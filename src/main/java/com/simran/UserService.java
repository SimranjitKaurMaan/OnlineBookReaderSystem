package com.simran;

import com.simran.exceptions.BookNotFoundException;
import com.simran.exceptions.UserMembershipExpiredException;
import com.simran.exceptions.UserNotFoundException;
import com.simran.models.MembershipStatus;
import com.simran.models.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UserService
{

    private Map<String,User> userMap;

    public UserService() {
        this.userMap = new HashMap<>();
    }

    public String addUser(String name)
    {
        User user = new User(name);
        userMap.put(user.getId(),user);
        return user.getId();
    }

    public void extendUser(String userId)
    {
        if(!this.userMap.containsKey(userId))
        {
            throw new UserNotFoundException();
        }
        User user = userMap.get(userId);
        user.setStatus(MembershipStatus.active);
        user.setMembershipExpiryDate(LocalDateTime.now().plusMonths(3));
    }

    public void updateUserMembershipStatus()
    {
        userMap.values().stream().forEach(user -> {
            if((user.getStatus() == MembershipStatus.active) && user.getMembershipExpiryDate().isBefore(LocalDateTime.now()))
            {
              user.setStatus(MembershipStatus.expired);
            }
        });
    }

    public User getUser(String userId)
    {
        if(!this.userMap.containsKey(userId))
        {
            throw new UserNotFoundException();
        }
        if(this.userMap.get(userId).getStatus() == MembershipStatus.expired)
        {
            throw new UserMembershipExpiredException();
        }

        return this.userMap.get(userId);
    }


}
