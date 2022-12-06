package br.ada.sayajins;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void exibirArquivo(InputStream is) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;

            while (br.readLine() != null) {
                line = br.readLine();
                result.append(line).append("\n");
            }

            System.out.println(result.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
