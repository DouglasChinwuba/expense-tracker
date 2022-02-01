package com.expensetracker.auth.service;

import com.expensetracker.auth.model.User;
import com.expensetracker.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByUsername(username);

        User user = null;

        if(result.isPresent()){
            user = result.get();
        }else{
            throw new UsernameNotFoundException("User with username(" + username + ") not found");
        }

        return UserDetailsImpl.build(user);

    }
}
