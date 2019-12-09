package br.com.dge_bi.br.com.dge_bi.modules.usuario.service;

import br.com.dge_bi.br.com.dge_bi.config.exception.ValidacaoException;
import br.com.dge_bi.br.com.dge_bi.modules.usuario.dto.UsuarioAutenticado;
import br.com.dge_bi.br.com.dge_bi.modules.usuario.dto.UsuarioFiltros;
import br.com.dge_bi.br.com.dge_bi.modules.usuario.dto.UsuarioRequest;
import br.com.dge_bi.br.com.dge_bi.modules.usuario.model.Usuario;
import br.com.dge_bi.br.com.dge_bi.modules.usuario.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.dge_bi.br.com.dge_bi.modules.usuario.exception.UsuarioException.*;
import static br.com.dge_bi.br.com.dge_bi.modules.usuario.model.Usuario.of;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(UsuarioRequest usuarioRequest) {
        var usuario = of(usuarioRequest);
        validarDadosUsuario(usuario);
        usuario.setSenha(passwordEncoder.encode(usuarioRequest.getSenha()));
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setUltimoAcesso(LocalDateTime.now());
        usuarioRepository.save(usuario);
    }

    private void validarDadosUsuario(Usuario usuario) {
        validarEmailExistente(usuario);
        validarCpfExistente(usuario);
    }

    private void validarEmailExistente(Usuario usuario) {
        usuarioRepository.findByEmail(usuario.getEmail())
            .ifPresent(usuarioExistente -> {
                if (usuario.isNovoCadastro() || !usuario.getId().equals(usuarioExistente.getId())) {
                    throw USUARIO_EMAIL_JA_CADASTRADO.getException();
                }
            });
    }

    private void validarCpfExistente(Usuario usuario) {
        usuarioRepository.findByCpf(usuario.getCpf())
            .ifPresent(usuarioExistente -> {
                if (usuario.isNovoCadastro() || !usuario.getId().equals(usuarioExistente.getId())) {
                    throw USUARIO_CPF_JA_CADASTRADO.getException();
                }
            });
    }

    @Transactional
    public UsuarioAutenticado getUsuarioAutenticadoAtualizaUltimaData() {
        var usuarioAtualizado = usuarioRepository
            .findById(getUsuarioAutenticado().getId())
            .orElseThrow(USUARIO_NAO_ENCONTRADO::getException);
        return UsuarioAutenticado.of(atualizarUltimoAcesso(usuarioAtualizado));
    }

    @Transactional
    private Usuario atualizarUltimoAcesso(Usuario usuario) {
        usuario.setUltimoAcesso(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public UsuarioAutenticado getUsuarioAutenticado() {
        var email = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (principal instanceof UserDetails) {
                email = ((UserDetails) principal).getUsername();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw USUARIO_SEM_SESSAO.getException();
        }
        return UsuarioAutenticado
            .of(usuarioRepository.findByEmail(getUserEmail(email, principal))
                .orElseThrow(USUARIO_NAO_ENCONTRADO::getException));
    }

    private String getUserEmail(String email, Object principal) {
        if (!isEmpty(email) && email.contains("@")) {
            return email;
        }
        if (!isEmpty(principal) && principal.toString().contains("@")) {
            return principal.toString();
        }
        throw new ValidacaoException("Email não identificado.");
    }

    public Page<Usuario> getUsuarios(Integer page, Integer size, UsuarioFiltros filtros) {
        return usuarioRepository.findAll(filtros.toPredicate().build(), PageRequest.of(page, size));
    }

    public List<Usuario> getUsuariosPageableQueryDsl(Integer page, Integer size, UsuarioFiltros filtros) {
        return usuarioRepository.findAllPredicatePageable(PageRequest.of(page, size), filtros.toPredicate().build());
    }

    public UsuarioAutenticado findUsuarioAutenticadoByEmail() {
        return usuarioRepository.findUsuarioAutenticadoByEmail(recuperarEmailUsuarioAutenticado());
    }

    private String recuperarEmailUsuarioAutenticado() {
        var email = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (principal instanceof UserDetails) {
                email = ((UserDetails)principal).getUsername();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw USUARIO_SEM_SESSAO.getException();
        }
        return email;
    }
}
