package br.ada.sayajins.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PagamentosFactory {

   private String nome;
   private String dtVencto;
   private String valor;
   private String tipoPagamentoEnum;

   public PagamentosFactory(String nome, String tipoPagamentoEnum, String dtVencto, String valor) {
      this.nome = nome;
      this.tipoPagamentoEnum = tipoPagamentoEnum;
      this.dtVencto = dtVencto;
      this.valor = valor;
   }

   public Pagamentos criaPagamentos() {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
      LocalDate dtVencto = LocalDate.parse(this.dtVencto, formatter);
      BigDecimal valor = new BigDecimal(this.valor).setScale(2, RoundingMode.HALF_UP);

      return new Pagamentos(
            nome,
            TipoPagamentoEnum.valueOf(tipoPagamentoEnum),
            dtVencto,
            valor);
   }
}
