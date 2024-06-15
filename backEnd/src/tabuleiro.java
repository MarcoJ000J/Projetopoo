import console.Console;
import cores.Cor;
import cores.StringColorida;
import mecanicas.Carta;
import mecanicas.Tabuleiro;

import java.util.Objects;
import java.util.Random;

public class tabuleiro extends Tabuleiro {
    Random random = new Random();

    int fim = 1;
    int ponto = 0;
    int movimentos = 27;

    int[][] level = new int[5][8];

    carta vazio = new carta(new StringColorida("   ", Cor.AZUL, Cor.FUNDO_PRETO));
    carta a = new carta(new StringColorida(" ᛤ ", Cor.AZUL, Cor.FUNDO_PRETO));
    carta b = new carta(new StringColorida(" ☘ ", Cor.VERDE, Cor.FUNDO_PRETO));
    carta c = new carta(new StringColorida(" ⚠ ", Cor.AMARELO, Cor.FUNDO_PRETO));
    carta d = new carta(new StringColorida(" ☭ ", Cor.VERMELHO, Cor.FUNDO_PRETO));//pode? não resisti
    carta e = new carta(new StringColorida(" ⚑ ", Cor.ROXO, Cor.FUNDO_PRETO));
    carta f = new carta(new StringColorida(" ⊗ ", Cor.ROSA_CHOQUE, Cor.FUNDO_PRETO));

    public tabuleiro(int numLinhas, int numColunas, Carta fundo) {
        super(numLinhas, numColunas, fundo);
    }

    public void jogoNovo(tabuleiro jogo) {
        criaLevel();
        montaLevel(jogo);
    }

    public void continua(tabuleiro jogo) {
        save cont = new save();
        cont.readContent(jogo);
        montaLevel(jogo);
    }

    private void criaLevel() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                int aux = random.nextInt(5);
                level[i][j] = aux;
            }
        }
    }

    public void montaLevel(tabuleiro jogo) {
        for (int j = 0; j < 5; j++) {
            colocaCarta(j + 1, 0, new carta(new StringColorida(" " + String.valueOf(j+1) + " ", Cor.PRETO, Cor.FUNDO_ROXO)));
        }
        colocaCarta(5, 0, new carta(new StringColorida(" " + String.valueOf(5) + " ", Cor.PRETO, Cor.FUNDO_ROXO)));
        for (int i = 0; i < 8; i++) {
            colocaCarta(0, i + 1, new carta(new StringColorida(" " + String.valueOf(i+1) + " ", Cor.PRETO, Cor.FUNDO_ROXO)));
        }
        colocaCarta(0, 8, new carta(new StringColorida(" " + String.valueOf(8) + " ", Cor.PRETO, Cor.FUNDO_ROXO)));

        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 9; j++) {
                if (level[i - 1][j - 1] == -1) {
                    jogo.colocaCarta(i, j, vazio);
                }
                if (level[i - 1][j - 1] == 0) {
                    jogo.colocaCarta(i, j, a);
                }
                if (level[i - 1][j - 1] == 1) {
                    jogo.colocaCarta(i, j, b);
                }
                if (level[i - 1][j - 1] == 2) {
                    jogo.colocaCarta(i, j, c);
                }
                if (level[i - 1][j - 1] == 3) {
                    jogo.colocaCarta(i, j, d);
                }
                if (level[i - 1][j - 1] == 4) {
                    jogo.colocaCarta(i, j, e);
                }
                if (level[i - 1][j - 1] == 5) {
                    jogo.colocaCarta(i, j, f);
                }
            }
        }
    }

    public void mover(int i, int j, String direcao, tabuleiro jogo) {
        if (Objects.equals(direcao, "cima")) {
            if (i - 1 >= 0) {
                int temp = level[i][j];
                System.out.println(temp);
                level[i][j] = level[i - 1][j];
                level[i - 1][j] = temp;

                tira(i - 1, j,jogo);

                montaLevel(jogo);
                Console.limpaTela();
                System.out.println();
                Console.println(jogo);

            } else {
                System.out.println("jodadainvalida");
            }
        } else if (Objects.equals(direcao, "baixo")) {
            if (i + 1 < 5) {
                int temp = level[i][j];
                System.out.println(temp);
                level[i][j] = level[i + 1][j];
                level[i + 1][j] = temp;

                tira(i + 1, j,jogo);

                montaLevel(jogo);
                Console.limpaTela();
                Console.println(jogo);

            } else {
                System.out.println("jodadainvalida");
            }

        } else if (Objects.equals(direcao, "direita")) {
            if (j + 1 <= 7) {
                int temp = level[i][j];
                System.out.println(temp);
                level[i][j] = level[i][j + 1];
                level[i][j + 1] = temp;

                tira(i, j + 1,jogo);

                montaLevel(jogo);
                Console.limpaTela();
                Console.println(jogo);

//                verificaPossivel();
            } else {
                System.out.println("jodadainvalida");
            }

        } else if (Objects.equals(direcao, "esquerda")) {
            if (j - 1 >= 0) {
                int temp = level[i][j];
                System.out.println(temp);
                level[i][j] = level[i][j - 1];
                level[i][j - 1] = temp;

                tira(i, j - 1,jogo);

                montaLevel(jogo);
                Console.limpaTela();
                Console.println(jogo);

//                verificaPossivel();
            } else {
                System.out.println("jodadainvalida");
            }
        }
    }

    public void verificaVazio(tabuleiro jogo) {
        int vazio = 1;
        while (vazio == 1) {
            for (int i = 1; i < 5; i++) {
                for (int j = 0; j < 8; j++) {
                    if (level[i][j] == -1) {
                        mover(i, j, "cima", jogo);
                    }
                }
            }

            poe();

            Console.limpaTela();
            System.out.println("");
            Console.println(jogo);

            for (int i = 1; i < 5; i++) {
                for (int j = 0; j < 8; j++) {
                    if (level[i][j] == -1) {
                        verificaVazio(jogo);
                    } else {
                        vazio = 0;
                    }
                }
            }
        }
    }

    private void poe() {
        for (int j = 0; j < 8; j++) {
            if (level[0][j] == -1) {
                int aux = random.nextInt(5);
                level[0][j] = aux;
            }
        }
    }

    private void tira(int i, int j,tabuleiro jogo){
        int hor = 1;
        int ver = 1;
        //horizontal
        for (int v = j; v <= 7; v++) {
            if (level[i][v] == level[i][j]) {
                hor = hor + 1;
            } else {
                break;
            }
        }

        for (int v = j; v >= 0; v--) {
            if (level[i][v] == level[i][j]) {
                hor = hor + 1;
            } else {
                break;
            }
        }

        //vertical
        for (int v = i; v >= 0; v--) {
            if (level[v][j] == level[i][j]) {
                ver = ver + 1;
            } else {
                break;
            }
        }

        for (int v = i; v < 5; v++) {
            if (level[v][j] == level[i][j]) {
                ver = ver + 1;
            } else {
                break;
            }
        }

        //aff
        if (hor >= 3 || ver >= 3) {
            if (hor == ver) {
                for (int v = j + 1; v <= 7; v++) {
                    if (level[i][v] == level[i][j]) {
                        level[i][v] = -1;
                        jogo.ponto = jogo.ponto+1;
                    }
                }
                for (int v = j - 1; v >= 0; v--) {
                    if (level[i][v] == level[i][j]) {
                        level[i][v] = -1;
                        jogo.ponto = jogo.ponto+1;
                    }
                }
                level[i][j] = -1;
                jogo.ponto = jogo.ponto+1;
            }
            else if (hor >= 3 && ver >= 3) {
                if (hor > ver) {
                    for (int v = i + 1; v <= 7; v++) {
                        if (level[i][v] == level[i][j]) {
                            level[i][v] = -1;
                            jogo.ponto = jogo.ponto+1;
                        }
                    }
                    for (int v = i - 1; v >= 0; v--) {
                        if (level[i][v] == level[i][j]) {
                            level[i][v] = -1;
                            jogo.ponto = jogo.ponto+1;
                        }
                    }
                } else {
                    for (int v = i - 1; v >= 0; v--) {
                        if (level[v][j] == level[i][j]) {
                            level[v][j] = -1;
                            jogo.ponto = jogo.ponto+1;
                        }
                    }
                    for (int v = i + 1; v < 5; v++) {
                        if (level[v][j] == level[i][j]) {
                            level[v][j] = -1;
                            jogo.ponto = jogo.ponto+1;
                        }
                    }
                }
                level[i][j] = -1;
                jogo.ponto = jogo.ponto+1;
            }
            else if (hor >= 3) {
                for (int v = i + 1; v <= 7; v++) {
                    if (level[i][v] == level[i][j]) {
                        level[i][v] = -1;
                        jogo.ponto = jogo.ponto+1;
                    }
                }
                for (int v = i - 1; v >= 0; v--) {
                    if (level[i][v] == level[i][j]) {
                        level[i][v] = -1;
                        jogo.ponto = jogo.ponto+1;
                    }
                }
                level[i][j] = -1;
                jogo.ponto = jogo.ponto+1;
            }
            else if (ver >= 3) {
                for (int v = j - 1; v >= 0; v--) {
                    if (level[v][j] == level[i][j]) {
                        level[v][j] = -1;
                        jogo.ponto = jogo.ponto+1;
                    }
                }
                for (int v = j + 1; v < 5; v++) {
                    if (level[v][j] == level[i][j]) {
                        level[v][j] = -1;
                        jogo.ponto = jogo.ponto+1;
                    }
                }
                level[i][j] = -1;
                jogo.ponto = jogo.ponto+1;
            }
        }
    }
}