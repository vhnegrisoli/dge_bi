package br.com.dge_bi.br.com.dge_bi.modules.usuario.exception;

import br.com.dge_bi.br.com.dge_bi.config.exception.ValidacaoException;
import lombok.Getter;

public enum UsuarioException {

    USUARIO_NAO_ENCONTRADO(new ValidacaoException("Usuário não encontrado.")),
    USUARIO_SEM_SESSAO(new ValidacaoException("Não há uma sessão de usuário ativa.")),
    USUARIO_EMAIL_JA_CADASTRADO(new ValidacaoException("Email já cadastrado para um usuário ativo.")),
    USUARIO_CPF_JA_CADASTRADO(new ValidacaoException("CPF já cadastrado para um usuário ativo.")),
    USUARIO_ACESSO_INVALIDO(new ValidacaoException("Usuário ou senha inválidos, tente novamente."));

    @Getter
    private ValidacaoException exception;

    UsuarioException(ValidacaoException exception) {
        this.exception = exception;
    }
}
