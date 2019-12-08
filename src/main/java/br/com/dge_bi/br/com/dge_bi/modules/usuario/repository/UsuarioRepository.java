package br.com.dge_bi.br.com.dge_bi.modules.usuario.repository;

import br.com.dge_bi.br.com.dge_bi.modules.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>,
    QuerydslPredicateExecutor<Usuario>, UsuarioRepositoryCustom {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCpf(String cpf);

    @Modifying
    @Query("update Usuario a set a.ultimoAcesso = :ultimoAcesso where a.id = :id")
    void atualizarUltimoAcesso(LocalDateTime ultimoAcesso, Integer id);
}
