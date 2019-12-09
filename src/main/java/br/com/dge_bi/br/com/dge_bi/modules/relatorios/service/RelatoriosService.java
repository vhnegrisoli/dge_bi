package br.com.dge_bi.br.com.dge_bi.modules.relatorios.service;

import br.com.dge_bi.br.com.dge_bi.modules.relatorios.model.Relatorios;
import br.com.dge_bi.br.com.dge_bi.modules.relatorios.repository.RelatoriosRepository;
import br.com.dge_bi.br.com.dge_bi.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatoriosService {

    @Autowired
    private RelatoriosRepository relatoriosRepository;
    @Autowired
    private UsuarioService usuarioService;

    public List<Relatorios> buscarTodosOsRelatorios() {
        var usuarioAutenticado = usuarioService.getUsuarioAutenticado();
        if (usuarioAutenticado.isAdmin()) {
            return relatoriosRepository.findAll();
        }
        return relatoriosRepository.findByUsuarioId(usuarioAutenticado.getId());
    }
}
