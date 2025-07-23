package com.hegazy.ssecuritypart11.config;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hegazy.ssecuritypart11.model.Customer;
import com.hegazy.ssecuritypart11.repo.CustomerRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final CustomerRepo customerRepo;


    public MyUserDetailsService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException(
                        "User details not found for the user: " + username
                    ));
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
        return new User(customer.getEmail(), customer.getPwd(), authorities);
    }

}
