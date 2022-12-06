package br.ada.sayajins.dao;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import br.ada.sayajins.model.Pagamentos;
import br.ada.sayajins.model.PagamentosFactory;

public class PagamentosDao {

    public void lerArquivo(List<Pagamentos> pagamentos) {
        try {
            File arquivo = new File("src/main/resources/pagamentos.csv");

            checarArquivo(arquivo.toPath());
            Path arquivoNio = Paths.get("src/main/resources/pagamentos.csv");
            checarArquivo(arquivoNio.toAbsolutePath());

            Scanner scanner = new Scanner(arquivo);
            scanner.nextLine();

            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(";");

                PagamentosFactory factory = new PagamentosFactory(line[0], line[1], line[2], line[3]);
                pagamentos.add(factory.criaPagamentos());
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checarArquivo(Path path) {
        if (Files.exists(path)) {
            System.out.println("Arquivo encontrado.");
        } else {
            System.out.println("Arquivo não encontrado.");
        }

        System.out.println(path);
    }

}
