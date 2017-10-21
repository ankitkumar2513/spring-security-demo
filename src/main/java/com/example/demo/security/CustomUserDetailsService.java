package com.example.demo.security;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userId);
        if(user == null)
            return null;
        List<String> rolesList = user.getRolesList();
        String [] authorities = rolesList.toArray(new String[rolesList.size()]);
        return new MongoUserDetails(user.getUserName(), user.getPassword(),
                AuthorityUtils.createAuthorityList(authorities));
    }
}
