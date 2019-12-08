package br.com.dge_bi.br.com.dge_bi.modules.usuario.model;

import br.com.dge_bi.br.com.dge_bi.modules.usuario.enums.EPermissao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PERMISSAO")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "CODIGO")
    @NotNull
    @Enumerated(EnumType.STRING)
    private EPermissao codigo;

    @Column(name = "DESCRICAO")
    @NotNull
    private String descricao;

}
