package br.com.letscode.dependenciasexternas.devclassmgmt;

import br.com.letscode.dependenciasexternas.devclassmgmt.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

@Builder
@Getter
@AllArgsConstructor
public class Turma {

    private String nomeTurma;
    private String caminhoArquivo;

    private List<String> criarTurma(String path) {

        List<String> nomesAlunos = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (StringUtils.isNotBlank(line))
                    CollectionUtils.addIgnoreNull(nomesAlunos, line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado no diretório: " + path);
        } catch (IOException e) {
            System.out.println("Erro inesperado :(");
        }
        Collections.sort(nomesAlunos);

        return nomesAlunos;
    }

    public void imprimirTurma(String outputPath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

            List<String> listaAlunos = criarTurma(caminhoArquivo);

            writer.append("TURMA: ")
                    .append(StringUtils.upperCase(getNomeTurma()));
            writer.newLine();
            writer.append(Utils.getSeparador());
            writer.newLine();
            writer.append("LISTA DE ALUNOS");
            writer.newLine();

            listaAlunos.forEach(aluno -> {
                        try {
                            writer.append("  ")
                                    .append(aluno);
                            writer.newLine();
                        } catch (IOException e) {
                            System.out.println("Não foi possível escrever no arquivo.");
                        }
                    }
            );
            writer.append(Utils.getSeparador());
            writer.newLine();
            writer.append("TOTAL DE ALUNOS : ")
                    .append(String.valueOf(listaAlunos.size()));
        } catch (IOException e) {
            System.out.println("Erro ao criar/escrever no arquivo, verifique o caminho de destino");
        }
    }

    public static void imprimirTodasTurmas(String outputPath, Turma... turmas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            Set<String> todosAlunos = new TreeSet<>();
            List<String> listaAlunos;

            for (Turma turma : turmas) {
                listaAlunos = turma.criarTurma(turma.caminhoArquivo);
                CollectionUtils.addAll(todosAlunos, listaAlunos);
            }
            writer.append("TODOS OS ALUNOS DA ESCOLA");
            writer.newLine();
            writer.append(Utils.getSeparador());
            writer.newLine();
            todosAlunos.forEach(aluno -> {
                try {
                    writer.append(" ")
                            .append(aluno);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.append(Utils.getSeparador());
            writer.newLine();
            writer.append("TOTAL DE ALUNOS: ")
                    .append(String.valueOf(todosAlunos.size()));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
