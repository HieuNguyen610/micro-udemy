package hieu.authservice.service.impl;

import hieu.authservice.entity.Account;
import hieu.authservice.repository.AccountRepository;
import hieu.authservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService, UserDetailsService {

    private final AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username).orElseThrow
                (()-> new UsernameNotFoundException("Username " + username + " not found"));
        List<SimpleGrantedAuthority> authorities = account.getRoles().stream().map(SimpleGrantedAuthority::new).toList();
        return new User(username, account.getPassword(), authorities);
    }
}
