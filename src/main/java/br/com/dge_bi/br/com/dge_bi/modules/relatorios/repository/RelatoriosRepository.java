package br.com.dge_bi.br.com.dge_bi.modules.relatorios.repository;

import br.com.dge_bi.br.com.dge_bi.modules.relatorios.model.Relatorios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelatoriosRepository extends JpaRepository<Relatorios, Integer> {

    List<Relatorios> findByUsuarioId(Integer usuarioId);

}
