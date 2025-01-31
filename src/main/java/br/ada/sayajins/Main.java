package br.ada.sayajins;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import br.ada.sayajins.dao.PagamentosDao;
import br.ada.sayajins.model.Pagamentos;
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
            pDao.criarArquivosSeparados(pagamentos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
