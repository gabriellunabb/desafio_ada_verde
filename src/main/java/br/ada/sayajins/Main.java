package br.ada.sayajins;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import br.ada.sayajins.dao.PagamentosDao;
import br.ada.sayajins.model.Pagamentos;
import br.ada.sayajins.service.PagamentosService;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, Exception {

        PagamentosDao pDao = new PagamentosDao();

        List<Pagamentos> pagamentos = new ArrayList<Pagamentos>();

        pDao.lerArquivo(pagamentos);

        PagamentosService.alterarListaAtrasado(pagamentos);

        PagamentosService.alterarListaAdiantado(pagamentos);

        // pagamentos.stream().forEach(System.out::println);

        File novoArquivo = new File("src\\main\\resources\\arquivoCriado.csv");
        pDao.criarArquivo(novoArquivo, pagamentos);

    }
}
