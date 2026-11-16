/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lavarapido;
import br.com.lavarapido.service.ClienteService;
import br.com.lavarapido.service.TipoServicoService;
import br.com.lavarapido.service.AgendamentoService;

/**
 *
 * @author lauanesouza
 */
public class DadosSistema {
    public static final ClienteService CLIENTES =
            new ClienteService();

    public static final TipoServicoService SERVICOS =
            new TipoServicoService();

    public static final AgendamentoService AGENDAMENTOS =
            new AgendamentoService();

    private DadosSistema() {
    
}
}
