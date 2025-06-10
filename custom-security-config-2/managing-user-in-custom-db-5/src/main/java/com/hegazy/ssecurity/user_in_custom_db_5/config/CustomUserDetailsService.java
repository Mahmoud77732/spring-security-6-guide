/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hegazy.ssecurity.user_in_custom_db_5.config;

import com.hegazy.ssecurity.user_in_custom_db_5.model.Customer;
import com.hegazy.ssecurity.user_in_custom_db_5.repository.CustomerRepo;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author mahmoud
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{
    
    private final CustomerRepo customerRepo;

    public CustomUserDetailsService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User details not found for the user: " + username)
        );
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
        return new User(customer.getEmail(), customer.getPwd(), authorities);
    }
    
}
