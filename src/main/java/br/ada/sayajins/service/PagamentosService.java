package br.ada.sayajins.service;

import java.time.LocalDate;
import java.util.List;

import br.ada.sayajins.model.AlteracoesValor;
import br.ada.sayajins.model.Pagamentos;

public class PagamentosService {

    public static List<Pagamentos> alterarListaAtrasado(List<Pagamentos> lista){
        return lista.stream()
            .filter((p) -> p.getDtVencto().isBefore(LocalDate.now()))
            .map((p) -> new Pagamentos(
                p.getNome(),
                p.getDtVencto(),
                AlteracoesValor.acrescimo(p.getTipoPagamentoEnum()).pow(Long.valueOf(p.getDtVencto().until(LocalDate.now()).toTotalMonths()).intValue())
                    .multiply(p.getValor())
                    .doubleValue(),
                p.getTipoPagamentoEnum()))
            .toList();  
    };

    public static List<Pagamentos> alterarListaAdiantado(List<Pagamentos> lista){
        return lista;
    }
    
}
