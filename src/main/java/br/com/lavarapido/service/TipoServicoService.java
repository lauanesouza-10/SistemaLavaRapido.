/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lavarapido.service;
import br.com.lavarapido.model.TipoServico;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lauanesouza
 */
public class TipoServicoService {
    private final List<TipoServico> servicos = new ArrayList<>();
    private int proximoId = 1;
    
    public TipoServico cadastrar(String nome, String descricao,
                                BigDecimal preco) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                "O nome do servico e obrigatorio.");
        }

        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                "O preco deve ser maior que zero.");
        }
        
        for (TipoServico servico : servicos) {
            if (servico.getNome().equalsIgnoreCase(nome.trim())) {
                throw new IllegalArgumentException(
                    "Ja existe um servico com este nome.");
            }
        }

        TipoServico novoServico = new TipoServico(
            proximoId++,
            nome.trim(),
            descricao,
            preco
        );

        servicos.add(novoServico);
        return novoServico;
    }

    public List<TipoServico> listar() {
        return new ArrayList<>(servicos);
    }

    public TipoServico buscarPorId(int id) {
        for (TipoServico servico : servicos) {
            if (servico.getId() == id) {
                return servico;
            }
        }
        return null;
    }
}
    
