package com.phurbalama.SpringSecurityJPA;

import com.phurbalama.SpringSecurityJPA.models.MyUserDetails;
import com.phurbalama.SpringSecurityJPA.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRespository userRespository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRespository.findByUserName(username);

        user.orElseThrow(()-> new UsernameNotFoundException("Not found: "+ username));

        return user.map(MyUserDetails::new).get();

    }
}
