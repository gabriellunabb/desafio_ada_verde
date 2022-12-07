package br.ada.sayajins.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Pagamentos {

    private String nome;
    private LocalDate dtVencto;
    private BigDecimal valor;
    private TipoPagamentoEnum tipoPagamentoEnum;

    public TipoPagamentoEnum getTipoPagamentoEnum() {
        return tipoPagamentoEnum;
    }

    public void setTipoPagamentoEnum(TipoPagamentoEnum tipoPagamentoEnum) {
        this.tipoPagamentoEnum = tipoPagamentoEnum;
    }

    public LocalDate getDtVencto() {
        return dtVencto;
    }

    public void setDtVencto(LocalDate dtVencto) {
        this.dtVencto = dtVencto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String v) {
        this.nome = v;
    }

    public Pagamentos(String s) {
        setNome(s);
    }

    public Pagamentos(String s, TipoPagamentoEnum tipoPagamentoEnum, LocalDate dateVencto, BigDecimal valor) {
        this.valor = valor;
        this.nome = s;
        this.dtVencto = dateVencto;
        this.tipoPagamentoEnum = tipoPagamentoEnum;
    }

    public String getValorFormatado() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatter.format(this.valor.doubleValue());
    }

    @Override
    public String toString() {
        return this.nome
                .concat(";")
                .concat(this.getTipoPagamentoEnum().name())
                .concat(";")
                .concat(this.dtVencto.toString().replaceAll("-","" ))
                .concat(";")
                .concat(String.valueOf(this.valor.doubleValue()));
    }
}
