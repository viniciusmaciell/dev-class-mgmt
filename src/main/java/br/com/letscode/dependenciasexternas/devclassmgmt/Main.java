package br.com.letscode.dependenciasexternas.devclassmgmt;

public class Main {
    public static void main(String[] args) {
        String inputJava = "inputFiles/alunosJava.txt";
        String inputBancodeDados = "inputFiles/alunosBancodeDados.txt";

        String outputJava = "outputFiles/turmaJava.txt";
        String outputBancodeDados = "outputFiles/turmaBancodeDados.txt";
        String outputTodasTurmas = "outputFiles/todasTurmas.txt";

        Turma java = Turma.builder()
                .nomeTurma("java")
                .caminhoArquivo(inputJava)
                .build();

        Turma bancoDados = Turma.builder()
                .nomeTurma("banco de dados")
                .caminhoArquivo(inputBancodeDados)
                .build();


        java.imprimirTurma(outputJava);
        bancoDados.imprimirTurma(outputBancodeDados);
        Turma.imprimirTodasTurmas(outputTodasTurmas, java, bancoDados);
    }

}
