package br.ada.sayajins.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import br.ada.sayajins.model.Pagamentos;
import br.ada.sayajins.model.PagamentosFactory;
import br.ada.sayajins.model.TipoPagamentoEnum;
import br.ada.sayajins.service.PagamentosService;

public class PagamentosDao {

    public void lerArquivo(List<Pagamentos> pagamentos) {
        try {
            File arquivo = new File("src/main/resources/pagamentos.csv");
            checarArquivo(arquivo.toPath());
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

    private void criarArquivo(File file, List<Pagamentos> pagamentos) {

        String[] conteudo = pagamentos.toString()
                .substring(1, pagamentos.toString().length() - 1).split(", ");

        try {
            BufferedWriter arquivo = new BufferedWriter(new FileWriter(file));

            for (String string : conteudo) {
                arquivo.write(string + "\n");
            }

            arquivo.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void criarArquivosSeparados(List<Pagamentos> pagamentos) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (TipoPagamentoEnum values : TipoPagamentoEnum.values()) {
            String fileName = "src/main/resources/PAGAMENTOS_" + values.name() + "_" + LocalDate.now().format(formatter)
                    + ".csv";
            File novoArquivo = new File(fileName);
            List<Pagamentos> lista = PagamentosService.retornaPagamentosPorTipo(pagamentos, values);
            criarArquivo(novoArquivo, lista);
        }
    }

}
