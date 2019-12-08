package br.com.dge_bi.br.com.dge_bi.config.auth;

import br.com.dge_bi.br.com.dge_bi.modules.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.dge_bi.br.com.dge_bi.modules.usuario.exception.UsuarioException.USUARIO_ACESSO_INVALIDO;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return usuarioRepository
            .findByEmail(email)
            .map(usuario -> {
                List<GrantedAuthority> permissoes = AuthorityUtils
                    .createAuthorityList("ROLE_" + usuario.getPermissao().getCodigo().name());
                return new User(
                    usuario.getEmail(),
                    usuario.getSenha(),
                    permissoes);
            }).orElseThrow(USUARIO_ACESSO_INVALIDO::getException);
    }
}