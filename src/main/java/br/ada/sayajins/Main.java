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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ada.sayajins.model.Pagamentos;
import br.ada.sayajins.model.TipoPagamentoEnum;


public class Main {
    public static void main(String[] args) throws FileNotFoundException, Exception{
        
        try {
            File arquivo = new File("src/main/resources/pagamentos.csv");
            List<String> lista = new ArrayList<>();
            List<Pagamentos> pagamentos = new ArrayList<>();
            checarArquivo(arquivo.exists(), arquivo.getAbsolutePath());
            Path arquivoNio = Paths.get("src/main/resources/pagamentos.csv");
            checarArquivo(Files.exists(arquivoNio), arquivoNio.toFile().getAbsolutePath());

            Scanner scanner = new Scanner(arquivo);
            scanner.nextLine();
            while(scanner.hasNext()){
                String[] line = scanner.nextLine().split(";");
                // System.out.println(line[2]);
                // LocalDate.parse(line[2], DateTimeFormatter.ofPattern("yyyyMMdd"));
                pagamentos.add(new Pagamentos(line[0], TipoPagamentoEnum.valueOf(line[1]), LocalDate.parse(line[2], DateTimeFormatter.ofPattern("yyyyMMdd")), Double.parseDouble(line[3])));
                // String col1 = line[0];
                // String col2 = line[1];
                // String col3 = line[2];
                // String col4 = line[3];
                
                
            }
            // exibirArquivo(new FileInputStream(arquivo));

            // System.out.println(lista);

            pagamentos.stream().forEach(System.out::println);
            
        

            
            
        } catch (Exception e) {
            System.out.println("Temos um erro aqui!");
        }
        

        
        }
    
    
        

    private static void checarArquivo(boolean existe, String path) {
        System.out.println(existe ? "Temos o arquivo aqui" : "Não temos o arquivo aqui");
        System.out.println(path);

    }
    

    private static void exibirArquivo(InputStream is) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line).append("\n");
            }
            System.out.println(result.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

