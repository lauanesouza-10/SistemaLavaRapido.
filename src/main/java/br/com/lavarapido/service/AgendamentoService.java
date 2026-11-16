/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lavarapido.service;
import br.com.lavarapido.model.Agendamento;
import br.com.lavarapido.model.Cliente;
import br.com.lavarapido.model.TipoServico;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lauanesouza
 */
public class AgendamentoService {
    private final List<Agendamento> agendamentos = new ArrayList<>();
    private int proximoId = 1;
    
    public Agendamento agendar(Cliente cliente,
                               TipoServico tipoServico,
                               LocalDateTime dataHora) {

        validarDados(cliente, tipoServico, dataHora);
        verificarConflito(dataHora, -1);

        Agendamento novo = new Agendamento(
            proximoId++, cliente, tipoServico,
            dataHora, "AGENDADO"
        );

        agendamentos.add(novo);
        return novo;
    }

    public List<Agendamento> listar() {
        return new ArrayList<>(agendamentos);
    }
    
    public Agendamento buscarPorId(int id) {
        for (Agendamento a : agendamentos) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public List<Agendamento> listarPorDia(LocalDate dia) {
        List<Agendamento> resultado = new ArrayList<>();

        for (Agendamento a : agendamentos) {
            if (a.getDataHora().toLocalDate().equals(dia)) {
                resultado.add(a);
            }
        }

        resultado.sort(
            (a, b) -> a.getDataHora().compareTo(b.getDataHora())
        );

        return resultado;
    }

    public List<Agendamento> listarPorSemana(LocalDate inicio) {
        List<Agendamento> resultado = new ArrayList<>();
        LocalDate fim = inicio.plusDays(7);

        for (Agendamento a : agendamentos) {
            LocalDate dia = a.getDataHora().toLocalDate();

            if (!dia.isBefore(inicio) && dia.isBefore(fim)) {
                resultado.add(a);
            }
        }

        resultado.sort(
            (a, b) -> a.getDataHora().compareTo(b.getDataHora())
        );

        return resultado;
    }

    public void editar(int id, Cliente cliente,
                       TipoServico tipoServico,
                       LocalDateTime dataHora) {

        Agendamento a = buscarPorId(id);

        if (a == null) {
            throw new IllegalArgumentException(
                "Agendamento nao encontrado.");
        }

        if ("CANCELADO".equals(a.getStatus())) {
            throw new IllegalArgumentException(
                "Nao e possivel editar um agendamento cancelado.");
        }

        validarDados(cliente, tipoServico, dataHora);
        verificarConflito(dataHora, id);

        a.setCliente(cliente);
        a.setTipoServico(tipoServico);
        a.setDataHora(dataHora);
    }

    public void cancelar(int id) {
        Agendamento a = buscarPorId(id);

        if (a == null) {
            throw new IllegalArgumentException(
                "Agendamento nao encontrado.");
        }

        a.setStatus("CANCELADO");
    }
    
    private void validarDados(Cliente cliente,
                              TipoServico tipoServico,
                              LocalDateTime dataHora) {

        if (cliente == null || tipoServico == null
                || dataHora == null) {
            throw new IllegalArgumentException(
                "Cliente, servico e data/hora sao obrigatorios.");
        }

        if (dataHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                "Nao e permitido agendar em data passada.");
        }
    }

    private void verificarConflito(LocalDateTime dataHora,
                                   int idIgnorado) {

        for (Agendamento a : agendamentos) {
            if (a.getId() != idIgnorado
                    && !"CANCELADO".equals(a.getStatus())
                    && a.getDataHora().equals(dataHora)) {

                throw new IllegalArgumentException(
                    "Ja existe um agendamento neste horario.");
            }
        }
    }
}
    