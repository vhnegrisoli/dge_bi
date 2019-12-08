package br.com.dge_bi.br.com.dge_bi.modules.usuario.dto;

import br.com.dge_bi.br.com.dge_bi.config.PredicateBase;

import static br.com.dge_bi.br.com.dge_bi.modules.usuario.model.QUsuario.usuario;
import static org.springframework.util.ObjectUtils.isEmpty;

public class UsuarioPredicate extends PredicateBase {

    public UsuarioPredicate comNome(String nome) {
        if (!isEmpty(nome)) {
            builder.and(usuario.nome.likeIgnoreCase("%" + nome + "%"));
        }
        return this;
    }

    public UsuarioPredicate comEmail(String email) {
        if (!isEmpty(email)) {
            builder.and(usuario.email.likeIgnoreCase("%" + email + "%"));
        }
        return this;
    }

    public UsuarioPredicate comCpf(String cpf) {
        if (!isEmpty(cpf)) {
            builder.and(usuario.cpf.likeIgnoreCase("%" + cpf + "%"));
        }
        return this;
    }

    public UsuarioPredicate comPermissao(String permissao) {
        if (!isEmpty(permissao)) {
            builder.and(usuario.permissao.descricao.likeIgnoreCase("%" + permissao + "%"));
        }
        return this;
    }
}