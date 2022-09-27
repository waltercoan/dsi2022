package br.univille.dsi2022.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.univille.dsi2022.repository.UsuarioRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Service
public class UserDetailServiceImpl
    implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) 
        throws UsernameNotFoundException {
        var usuario = repository.findByUsuario(username);
        var listaPapeis = new ArrayList<SimpleGrantedAuthority>();
        for(var umPapel : usuario.getListaPapeis()){
            listaPapeis.add(new SimpleGrantedAuthority("ROLE_"+umPapel.getPapel().toUpperCase()));
        }
        return new User(usuario.getUsuario(), 
                        usuario.getSenha(),
                        listaPapeis);
    }
    
}
