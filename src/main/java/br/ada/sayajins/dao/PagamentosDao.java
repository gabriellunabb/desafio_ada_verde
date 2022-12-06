package br.ada.sayajins.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import br.ada.sayajins.model.Pagamentos;
import br.ada.sayajins.model.TipoPagamentoEnum;

public class PagamentosDao {

    public void lerArquivo(List<Pagamentos> pagamentos) {
        try {
            File arquivo = new File("src/main/resources/pagamentos.csv");

            checarArquivo(arquivo.exists(), arquivo.getAbsolutePath());
            Path arquivoNio = Paths.get("src/main/resources/pagamentos.csv");
            checarArquivo(Files.exists(arquivoNio), arquivoNio.toFile().getAbsolutePath());

            Scanner scanner = new Scanner(arquivo);
            scanner.nextLine();
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(";");

                pagamentos.add(new Pagamentos(
                        line[0],
                        TipoPagamentoEnum.valueOf(line[1]),
                        LocalDate.parse(line[2], DateTimeFormatter.ofPattern("yyyyMMdd")),
                        Double.parseDouble(line[3])));

            }

            //pagamentos.stream().forEach(System.out::println);
            scanner.close();

        } catch (Exception e) {
            System.out.println("Temos um erro aqui!");
        }
    }

    private static void checarArquivo(boolean existe, String path) {
        System.out.println(existe ? "Temos o arquivo aqui" : "Não temos o arquivo aqui");
        System.out.println(path);

    }

    public void criarArquivo(File file, List<Pagamentos> pagamentos) {
        
        String[] conteudo = pagamentos.toString().substring(1, pagamentos.toString().length()-2).split(", ");

        try {
            BufferedWriter arquivo = new BufferedWriter(new FileWriter(file));

            for (String string : conteudo) {
                arquivo.write(string + "\n");
            }
            
            arquivo.close();

        } catch (Exception e) {
            System.out.println("Não foi possível criar o arquivo.");
        }
    }
    
}
