package br.ada.sayajins;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import br.ada.sayajins.dao.PagamentosDao;
import br.ada.sayajins.model.Pagamentos;
import br.ada.sayajins.model.TipoPagamentoEnum;
import br.ada.sayajins.service.PagamentosService;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, Exception {

        try {
            PagamentosDao pDao = new PagamentosDao();
            List<Pagamentos> pagamentos = new ArrayList<Pagamentos>();

            pDao.lerArquivo(pagamentos);

            PagamentosService.alterarListaAtrasado(pagamentos);
            PagamentosService.alterarListaAdiantado(pagamentos);

            pagamentos.stream().forEach(System.out::println);
            criarArquivosSeparados(pDao, pagamentos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void criarArquivosSeparados(PagamentosDao pDao, List<Pagamentos> pagamentos) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (TipoPagamentoEnum values : TipoPagamentoEnum.values()) {
            String fileName = "src/main/resources/PAGAMENTOS_" + values.name() + "_" + LocalDate.now().format(formatter)
                    + ".csv";
            File novoArquivo = new File(fileName);
            List<Pagamentos> lista = PagamentosService.retornaPagamentosPorTipo(pagamentos, values);
            pDao.criarArquivo(novoArquivo, lista);
        }
    }
}
