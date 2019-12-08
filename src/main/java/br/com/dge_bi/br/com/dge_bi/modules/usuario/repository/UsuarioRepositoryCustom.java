package br.com.dge_bi.br.com.dge_bi.modules.usuario.repository;

import br.com.dge_bi.br.com.dge_bi.modules.usuario.dto.UsuarioAutenticado;
import br.com.dge_bi.br.com.dge_bi.modules.usuario.model.Usuario;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioRepositoryCustom {

    UsuarioAutenticado findUsuarioAutenticadoByEmail(String email);

    List<Usuario> findAllPredicate(Predicate build);

    List<Usuario> findAllPredicatePageable(Pageable pageable, Predicate predicate);
}
