package com.projet.forum.Services;

import com.projet.forum.Repositories.UserRepository;
import com.projet.forum.Entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> opt = this.userRepository.findByUsername(username);
        System.out.println(opt.get().getUsername());
        return opt.orElseThrow(() -> new UsernameNotFoundException("User with username(" + username + ") doesn't exist"));
    }
}
