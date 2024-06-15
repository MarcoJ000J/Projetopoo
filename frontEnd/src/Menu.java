import console.Console;
import cores.Cor;
import cores.StringColorida;
import java.util.Scanner;

public class Menu {
    int escolha;

    carta fundo = new carta(new StringColorida(" :3", Cor.PRETO,Cor.FUNDO_ROXO));

    tabuleiro jogo = new tabuleiro(6,9,fundo);

    Scanner scanner = new Scanner(System.in);

    public Menu(){}

    public void exibirMenu(){
        System.out.println(new StringColorida("=== :3 TRASHY¨CRUSH ☢  ===",Cor.ROXO,Cor.FUNDO_PRETO));
        System.out.println("1.NovoJogo");
        System.out.println("2.Continuar");
        System.out.println("3.Regras");
        System.out.println("4.Sair");


        escolha = scanner.nextInt();

        switch (escolha){
            case 1:
                save salvar = new save();
                jogo.jogoNovo(jogo);

                salvar.saveContent(jogo);

                jogando(jogo);
                resultado(jogo);

            case 2:
                jogo.continua(jogo);
                jogando(jogo);
                resultado(jogo);
            case 3:
                regras();
                exibirMenu();
            case 4:
                System.out.println("Saindo!☠");
                Console.saiDoPrograma();
                break;
        }
    }

    public void regras(){
        int temp = 0;
        while(temp == 0) {
            System.out.println(":3 REGRAS☢: ");
            System.out.println("Com um maximo de 27 movimentos mova as peças pelo tabuleiro");
            System.out.println("Faça pontos quando um trio ou mais de peças se juntam!");
            System.out.println("\n Para voltar insira 1.");
            temp = scanner.nextInt();
        }
    }

    public void resultado(tabuleiro jogo){
        System.out.println("☢Fim de Jogo :3");
        System.out.println("Sua pontuação foi de: ☢" +jogo.ponto);
    }

    private void jogando(tabuleiro jogo){
        while(jogo.fim == 1 && jogo.movimentos != 0) {
            jogo.montaLevel(jogo);
            System.out.println("");
            System.out.println("");
            Console.println(jogo);

            System.out.println("1.Fazer Movimento ☢ 2.Pausa");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    int linha;
                    int coluna;
                    int direcao;
                    String aux = null;

                    System.out.println("Insira linha:");
                    linha = scanner.nextInt();
                    System.out.println("Insira Coluna:");
                    coluna = scanner.nextInt();
                    System.out.println("Insira Direcao: 1.Cima 2.Baixo 3.Direita 4.Esquerda");
                    direcao = scanner.nextInt();

                    switch (direcao) {
                        case 1:
                            aux = "cima";
                            break;
                        case 2:
                            aux = "baixo";
                            break;
                        case 3:
                            aux = "direita";
                            break;
                        case 4:
                            aux = "esquerda";
                            break;
                    }
                    if (aux != null) {
                        jogo.movimentos = jogo.movimentos -1;
                        jogo.mover(linha-1, coluna-1, aux, jogo);
                    }else{
                        System.out.println("Insira Jogada Valida!☭");
                    }
                    break;
                case 2:
                    pausa(jogo);
                    break;
            }
            jogo.verificaVazio(jogo);
        }
    }

    private void pausa(tabuleiro jogo){
        int temp = 0;
        while(temp==0) {
            Console.limpaTela();
            System.out.println(new StringColorida("=== :3 TRASHY¨CRUSH ☢  ===",Cor.ROXO,Cor.FUNDO_PRETO));
            System.out.println("1.Continuar");
            System.out.println("2.Menu");
            System.out.println("3.Salvar");
            System.out.println("4.Regras");
            System.out.println("5.Sair");

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    temp = 1;
                    jogando(jogo);
                case 2:
                    temp =1;
                    exibirMenu();
                case 3:
                    System.out.println("Jogo Salvo");
                    save x = new save();
                    x.saveContent(jogo);
                case 4:
                    regras();
                case 5:
                    System.out.println("Saindo!☠");
                    Console.saiDoPrograma();
            }
        }
    }
}
