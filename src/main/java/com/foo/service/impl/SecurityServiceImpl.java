package com.foo.service.impl;

import com.foo.entity.User;
import com.foo.entity.common.UserPrincipal;
import com.foo.repository.UserRepository;
import com.foo.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user ==null){
            throw new UsernameNotFoundException("This user does not exists");
        }
        UserPrincipal userPrincipal=new UserPrincipal(user);
        return userPrincipal;
    }
}
