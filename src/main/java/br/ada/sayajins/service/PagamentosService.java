package br.ada.sayajins.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.ada.sayajins.model.AlteracoesValor;
import br.ada.sayajins.model.Pagamentos;

public class PagamentosService {

    public static List<Pagamentos> alterarListaAtrasado(List<Pagamentos> lista) {

        // lista.stream().filter((p) ->
        // p.getDtVencto().isBefore(LocalDate.now())).forEach((p) ->
        // System.out.println(AlteracoesValor.acrescimo(p.getTipoPagamentoEnum())));
        return lista.stream()
                .filter(p -> p.getDtVencto().isBefore(LocalDate.now()))
                .map(p -> {
                    p.setValor(AlteracoesValor.acrescimo(p.getTipoPagamentoEnum()).pow(getMesesAtraso(p))
                            .multiply(p.getValor()));
                    return p;
                }).collect(Collectors.toList());
    };

    public static List<Pagamentos> alterarListaAdiantado(List<Pagamentos> lista) {
        // lista.stream().filter((p) ->
        // p.getDtVencto().isAfter(LocalDate.now())).forEach(System.out::println);;
        return lista.stream()
                .filter(p -> p.getDtVencto().isAfter(LocalDate.now()))
                .map(p -> {
                    p.setValor(AlteracoesValor.decrescimo(p.getTipoPagamentoEnum()).pow(getDiasAdiantados(p))
                            .multiply(p.getValor()));
                    return p;
                })
                .collect(Collectors.toList());
    }

    private static Integer getMesesAtraso(Pagamentos p) {
        return Long.valueOf(p.getDtVencto().until(LocalDate.now()).toTotalMonths()).intValue();
    }

    private static Integer getDiasAdiantados(Pagamentos p) {
        return (LocalDate.now().until(p.getDtVencto()).getDays());
    }

}
