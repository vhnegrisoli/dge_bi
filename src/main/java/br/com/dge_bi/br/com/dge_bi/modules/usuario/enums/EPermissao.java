package br.com.dge_bi.br.com.dge_bi.modules.usuario.enums;

import lombok.Getter;

public enum EPermissao {

    USER("Usuário"),
    ADMIN("Administrador");

    @Getter
    private String descricao;

    EPermissao(String descricao) {
        this.descricao = descricao;
    }
}
