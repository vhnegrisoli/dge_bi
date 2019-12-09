package br.com.dge_bi.br.com.dge_bi.modules.relatorios.controller;

import br.com.dge_bi.br.com.dge_bi.modules.relatorios.model.Relatorios;
import br.com.dge_bi.br.com.dge_bi.modules.relatorios.service.RelatoriosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/relatorios")
public class RelatoriosController {

    @Autowired
    private RelatoriosService relatoriosService;

    @GetMapping
    public List<Relatorios> buscarRelatorios() {
        return relatoriosService.buscarTodosOsRelatorios();
    }
}
