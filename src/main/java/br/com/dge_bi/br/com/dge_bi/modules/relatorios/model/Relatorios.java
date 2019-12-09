package br.com.dge_bi.br.com.dge_bi.modules.relatorios.model;

import br.com.dge_bi.br.com.dge_bi.modules.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RELATORIOS")
public class Relatorios {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "TITULO")
    @NotNull
    private String titulo;

    @Column(name = "LINK")
    @NotNull
    private String link;

    @ManyToOne
    @JoinColumn(name = "FK_USUARIO")
    @NotNull
    private Usuario usuario;
}
