package com.dekeyserjarno.hoefsmidapp.util;

import com.dekeyserjarno.hoefsmidapp.objects.user.User;
import com.dekeyserjarno.hoefsmidapp.util.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class SecurityUtil {

    @Autowired
    private UserRepository userRepository;

    public boolean logIn(String username,String password){

        Iterator<User> it = userRepository.findAll().iterator();
        while (it.hasNext()){
            User user = it.next();
            if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)){
                return true;
            }
        }

        return false;
    }


}
