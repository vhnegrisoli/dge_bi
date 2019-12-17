package br.com.dge_bi.br.com.dge_bi.modules.relatorios.controller;

import br.com.dge_bi.br.com.dge_bi.modules.atualizacao.service.AtualizacaoService;
import br.com.dge_bi.br.com.dge_bi.modules.relatorios.model.Relatorios;
import br.com.dge_bi.br.com.dge_bi.modules.relatorios.service.RelatoriosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/relatorios")
public class RelatoriosController {

    @Autowired
    private RelatoriosService relatoriosService;
    @Autowired
    private AtualizacaoService atualizacaoService;

    @GetMapping
    public List<Relatorios> buscarRelatorios() {
        return relatoriosService.buscarTodosOsRelatorios();
    }

    @PutMapping("{id}/atualizar-dados")
    public String atualizarDados(@PathVariable Integer id) {
        return atualizacaoService.atualizarDados(id);
    }
}
