import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

    //ChatGPT foi usado aqui
    public class save{
        private static String FILE_NAME = "save.txt";

        // Função para salvar o conteúdo no arquivo
        public static void saveContent(tabuleiro x) {
            // Obter o caminho da área de trabalho
            String userHome = System.getProperty("user.home");
            String desktopPath = userHome + File.separator + "\\OneDrive\\Área de Trabalho";
            //String desktopPath = userHome + File.separator + "Desktop";


            List<String> contentLevel = new ArrayList<>();
            for(int j = 0;j<8;j++){
                for (int i = 0; i < 5; i++) {
                    contentLevel.add(String.valueOf(x.level[i][j]));
                }
            }


            List<String> contents = new ArrayList<>();
            contents.addAll(contentLevel);

            // Criar o arquivo
            File file = new File(desktopPath, FILE_NAME);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                for (String line : contents) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
            }
        }

        // Função para ler o conteúdo do arquivo
        public List<List<Integer>> readContent(tabuleiro x) {
            List<List<Integer>> segmentedContent = new ArrayList<>();
            List<Integer> currentContent = new ArrayList<>();

            // Obter o caminho da área de trabalho
            String userHome = System.getProperty("user.home");
            String desktopPath = userHome + File.separator + "Desktop";

            // Verificar se há arquivos alternativos e permitir ao usuário escolher qual ler
            List<String> fileNames = new ArrayList<>();
            int fileCount = 0;
            File desktop = new File(desktopPath);
            for (File file : Objects.requireNonNull(desktop.listFiles())) {
                if (file.getName().startsWith(FILE_NAME.replace(".txt", ""))) {
                    fileNames.add(file.getName());
                    fileCount++;
                }
            }

            if (fileCount > 1) {
                System.out.println("Foram encontrados arquivos alternativos:");
                for (int i = 0; i < fileCount; i++) {
                    System.out.println((i + 1) + "." + fileNames.get(i));
                }
                Scanner scanner = new Scanner(System.in);
                System.out.print("Escolha o número do arquivo que deseja ler: ");
                int choice = scanner.nextInt();
                FILE_NAME = fileNames.get(choice - 1);
                scanner.close();
            }

            // Ler o arquivo
            File file = new File(desktopPath, FILE_NAME);

            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Adiciona a linha ao conteúdo atual
                        if (line.trim().isEmpty()) {
                            currentContent.add(0);
                        } else {
                            // Verifica se a linha contém apenas números e converte para inteiro se necessário
                            try {
                                int number = Integer.parseInt(line);
                                currentContent.add(number);
                            } catch (NumberFormatException e) {
                                // Se não for um número, adiciona como string
                                currentContent.add(Integer.valueOf(line));
                            }
                        }

                        // Cada linha é um conteúdo separado
                        segmentedContent.add(new ArrayList<>(currentContent));
                    }

                    // Adiciona o último conteúdo se houver
                    if (!currentContent.isEmpty()) {
                        segmentedContent.add(currentContent);
                    }

                } catch (IOException e) {
                    System.err.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
                }
            } else {
                System.out.println("Arquivo '" + FILE_NAME + "' não encontrado.");
            }



            int aux = 0;
            for(int j = 0; j <8;j ++) {
                for (int i = 0; i < 5; i++) {
                    x.level[j][i] = currentContent.get(aux);
                    aux = aux+1;
                }
            }
            return segmentedContent;
        }
    }

