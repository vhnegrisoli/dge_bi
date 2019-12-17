package br.com.dge_bi.br.com.dge_bi.modules.atualizacao.service;

import br.com.dge_bi.br.com.dge_bi.modules.relatorios.model.Relatorios;
import br.com.dge_bi.br.com.dge_bi.modules.usuario.dto.UsuarioAutenticado;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String email;

    public void enviarEmailDeConfirmacao(UsuarioAutenticado usuarioAutenticado,
                                         Relatorios relatorio,
                                         LocalDateTime dataAtualizacao) {
        try {
            var mensagem = new SimpleMailMessage();
            mensagem.setTo(email);
            mensagem.setSubject("Atualização de Relatório PowerBI - Usuário: " + usuarioAutenticado.getNome());
            mensagem.setText("O usuário " + usuarioAutenticado.getNome() + " realizou uma atualização dos dados do "
                + "relatório " + relatorio.getTitulo() + " em "
                + dataAtualizacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
            javaMailSender.send(mensagem);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("Erro ao enviar email para " + email + ": " + ex.getMessage());
        }
    }
}
