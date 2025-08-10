package com.hegazy.ssecuritypart26.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>>{

    /**
     * It is responsible for converting a JWT token into a collection of granted authorities
     */
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> realmAccess = (Map<String, Object>) source.getClaims().get("realm_access");
        
        if (realmAccess == null || realmAccess.isEmpty()) {
            return new ArrayList<>();
        }

        /*
        * In Keycloak, roles are stored in a list called realm_access.roles
        * and they are prefixed with ROLE_
        */
        Collection<GrantedAuthority> returnValue = ((List<String>) realmAccess.get("roles")) // output: [USER, ADMIN, ...etc]
                .stream().map(roleName -> "ROLE_" + roleName) // output: ROLE_USER, ROLE_ADMIN, ...etc
                .map(SimpleGrantedAuthority::new) // output: SimpleGrantedAuthority(ROLE_USER), SimpleGrantedAuthority(ROLE_ADMIN), ...etc
                .collect(Collectors.toList()); // output: [SimpleGrantedAuthority(ROLE_USER), SimpleGrantedAuthority(ROLE_ADMIN), ...etc]

        return returnValue;
    }

}
