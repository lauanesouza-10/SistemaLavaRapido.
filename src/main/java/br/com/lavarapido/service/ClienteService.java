/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lavarapido.service;
import br.com.lavarapido.model.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lauanesouza
 */
public class ClienteService {
    private final List<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;
    public Cliente cadastrar(String nome, String cpf,
                            String telefone, String email,
                            String placaVeiculo) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                "O nome do cliente e obrigatorio.");
        }

        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException(
                "O CPF e obrigatorio.");
        }

        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                throw new IllegalArgumentException(
                    "Ja existe um cliente com este CPF.");
            }
        }

        Cliente cliente = new Cliente(
            proximoId++, nome, cpf, telefone,
            email, placaVeiculo
        );

        clientes.add(cliente);
        return cliente;
    }

    public List<Cliente> listar() {
        return new ArrayList<>(clientes);
    }

    public Cliente buscarPorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    public void editar(int id, String nome, String cpf,
                       String telefone, String email,
                       String placaVeiculo) {

        Cliente cliente = buscarPorId(id);

        if (cliente == null) {
            throw new IllegalArgumentException(
                "Cliente nao encontrado.");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                "O nome e obrigatorio.");
        }

        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException(
                "O CPF e obrigatorio.");
        }

        for (Cliente outro : clientes) {
            if (outro.getId() != id &&
                outro.getCpf().equals(cpf)) {
                throw new IllegalArgumentException(
                    "CPF ja cadastrado.");
            }
        }

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setPlacaVeiculo(placaVeiculo);
    }

    public void excluir(int id) {
        Cliente cliente = buscarPorId(id);

        if (cliente == null) {
            throw new IllegalArgumentException(
                "Cliente nao encontrado.");
        }

        clientes.remove(cliente);
    }
}
    
