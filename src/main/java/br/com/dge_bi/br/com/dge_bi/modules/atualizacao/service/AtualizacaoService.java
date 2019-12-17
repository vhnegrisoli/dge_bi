package br.com.dge_bi.br.com.dge_bi.modules.atualizacao.service;

import br.com.dge_bi.br.com.dge_bi.config.exception.ValidacaoException;
import br.com.dge_bi.br.com.dge_bi.modules.relatorios.repository.RelatoriosRepository;
import br.com.dge_bi.br.com.dge_bi.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class AtualizacaoService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RelatoriosRepository relatoriosRepository;

    public String atualizarDados(Integer relatorioId) {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        var relatorio = relatoriosRepository.findById(relatorioId)
            .orElseThrow(() -> new ValidacaoException("O relatório não foi encontrado."));
        var dataAtual = LocalDateTime.now();
        var data = dataAtual.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        var hora = dataAtual.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss"));
        emailService.enviarEmailDeConfirmacao(usuarioAutenticado, relatorio, dataAtual);
        return "Os dados foram atualizados com sucesso em " + data + " às " + hora
            + ", aguarde até que os dados sejam atualizados"
            + " no dashboard, o processo pode levar até 24 horas.";
    }
}
