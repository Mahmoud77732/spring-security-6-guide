package com.hegazy.ssecuritypart18.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hegazy.ssecuritypart18.model.Customer;
import com.hegazy.ssecuritypart18.repo.CustomerRepo;

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
        List<GrantedAuthority> authorities = customer.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
        return new User(customer.getEmail(), customer.getPwd(), authorities);
    }

}
