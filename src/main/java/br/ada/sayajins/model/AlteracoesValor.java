package br.ada.sayajins.model;

import java.math.BigDecimal;

public class AlteracoesValor {

    public static BigDecimal acrescimo(TipoPagamentoEnum tPag){
        switch (tPag) {
            case CREDITO: return new BigDecimal(1.03);

            case DEBITO: return new BigDecimal(1.01);

            case BOLETO: return new BigDecimal(1.05);

            default: return new BigDecimal(1.0);
        }
    };

    public static BigDecimal decrescimo(TipoPagamentoEnum tPag){
        switch (tPag) {
            case FIDELIDADE: return new BigDecimal(0.995);

            case PIX: return new BigDecimal(0.995);

            default: return new BigDecimal(1.0);
        }
    };
    
}
