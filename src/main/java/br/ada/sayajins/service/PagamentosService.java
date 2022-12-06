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
                AlteracoesValor.acrescimo(p.getTipoPagamentoEnum()).pow(getMesesAtraso(p)).multiply(p.getValor()).doubleValue(),
                p.getTipoPagamentoEnum()))
            .toList();
    };

    public static List<Pagamentos> alterarListaAdiantado(List<Pagamentos> lista){
        return lista.stream()
            .filter((p) -> p.getDtVencto().isAfter(LocalDate.now()))
            .map((p) -> new Pagamentos(
                p.getNome(),
                p.getDtVencto(),
                AlteracoesValor.decrescimo(p.getTipoPagamentoEnum()).pow(getDiasAdiantados(p)).multiply(p.getValor()).doubleValue(),
                p.getTipoPagamentoEnum()))
            .toList();
    }

    private static Integer getMesesAtraso(Pagamentos p){
        return Long.valueOf(p.getDtVencto().until(LocalDate.now()).toTotalMonths()).intValue();
    }

    private static Integer getDiasAdiantados(Pagamentos p){
        return Long.valueOf(p.getDtVencto().until(LocalDate.now()).toTotalMonths()).intValue();
    }
    
}
