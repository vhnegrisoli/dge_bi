package br.com.dge_bi.br.com.dge_bi.modules.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioFiltros {

    private String nome;
    private String email;
    private String cpf;
    private String permissao;

    public UsuarioPredicate toPredicate() {
        return new UsuarioPredicate()
            .comNome(nome)
            .comEmail(email)
            .comCpf(cpf)
            .comPermissao(permissao);
    }
}
