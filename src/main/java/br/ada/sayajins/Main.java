package br.ada.sayajins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ada.sayajins.model.Pagamentos;
import br.ada.sayajins.model.PagamentosFactory;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, Exception {

        try {
            File arquivo = new File("src/main/resources/pagamentos.csv");
            List<String> lista = new ArrayList<>();
            List<Pagamentos> pagamentos = new ArrayList<>();
            checarArquivo(arquivo.toPath());
            Path arquivoNio = Paths.get("src/main/resources/pagamentos.csv");
            checarArquivo(arquivoNio.toAbsolutePath());

            Scanner scanner = new Scanner(arquivo);
            scanner.nextLine();

            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(";");
                PagamentosFactory factory = new PagamentosFactory(line[0], line[1], line[2], line[3]);
                Pagamentos pagamento = factory.criaPagamentos();
                pagamentos.add(pagamento);
            }

            pagamentos.stream().forEach(System.out::println);
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
