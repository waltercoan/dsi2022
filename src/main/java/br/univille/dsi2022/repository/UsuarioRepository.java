package br.univille.dsi2022.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.univille.dsi2022.entity.Usuario;

@Repository
public interface UsuarioRepository 
    extends JpaRepository<Usuario,Long>{
    //select * from usuario where usuario.usuario = 'zezinho'
    Usuario findByUsuario(String usuario);
    //select * from usuario where 
    //usuario.usuario = "zezinho" and usuario.senha = "1234"
    Usuario findByUsuarioAndSenha(String usuario, String senha);
}
