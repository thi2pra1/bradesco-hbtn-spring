package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método para buscar um usuário pelo ID
    Optional<Usuario> findById(Long id);

    // Método para salvar um usuário
    Usuario save(Usuario usuario);
}
