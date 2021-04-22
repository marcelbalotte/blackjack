import java.util.Scanner;

public class Main {
    // declaraão de constantes
    public static final int RESTRICAO_QUANTIDADE_CARTAS = 7;
    public static final int RESTRICAO_QUANTIDADE_PONTOS = 17;
    public static final int VALOR_PONTUACAO = 21;
    public static final String NOME_OPERADOR_HUMANO = "jogador";
    public static final String NOME_OPERADOR_MAQUINA = "computador";

    public static void main(String[] args) {
        Scanner enter = new Scanner(System.in);

        System.out
                .println("############################################################################################"
                        + "\n############################# BEM-VINDO AO CYBERBLACKJACK 2077 #############################"
                        + "\n############################################################################################");

        int continuarJogando = 0;
        int pontosDoJogador = 0;
        int pontosDoComputador = 0;
        int qtdBaralhos = 0;
        Baralho baralho;

        qtdBaralhos = persistirEntradaQuantidadeDeck(enter);

        baralho = new Baralho(qtdBaralhos);

        System.out.println("\nComeçaremos o jogo com um deck de " + qtdBaralhos * 52 + " cartas!");

        do {

            // CÓDIGOS DA VEZ DO JOGADOR
            Mao maoJogador = new Mao(NOME_OPERADOR_HUMANO);

            // JOGADOR COMEÇA COM DUAS CARTAS

            if (!maoJogador.push(baralho.pop(), baralho)) {
                break;
            }

            if (!maoJogador.push(baralho.pop(), baralho)) {
                break;
            }

            // maoJogador.push(baralho.pop());
            // maoJogador.push(baralho.pop());

            System.out.println("\nPreparado? Vamos lá! Essas são suas duas cartas iniciais:");
            maoJogador.imprimir();

            if (maoJogador.getSomaDaMao() == VALOR_PONTUACAO) {
                System.out.println(
                        "\nEita, que sorte! Você tirou 21 nas suas duas cartas inicias, vamos aguardar a vez do próximo...");
            } else if (maoJogador.getSomaDaMao() >= RESTRICAO_QUANTIDADE_PONTOS) {
                System.out.println("\nVocê atingiu o limite de 17 pontos para essa rodada!");
            } else {
                int comprarOutraCarta = 0;

                while (comprarOutraCarta != 2) {

                    if (maoJogador.getTopo() < RESTRICAO_QUANTIDADE_CARTAS && maoJogador.getSomaDaMao() < 17) {
                        comprarOutraCarta = persistirEntradaStandHit(enter);

                        if (comprarOutraCarta == 1) {

                            if (!maoJogador.push(baralho.pop(), baralho)) {
                                break;
                            }

                            System.out.println("\nSuas cartas agora são:");
                            maoJogador.imprimir();

                            if (maoJogador.getSomaDaMao() == VALOR_PONTUACAO) {
                                System.out.println("\nBoa! Você tirou 21, agora vamos aguardar a vez do próximo");
                                break;
                            } else if (maoJogador.getSomaDaMao() > VALOR_PONTUACAO) {
                                System.out.println("\nPuts, que azar, você tirou mais que 21!");
                                pontosDoComputador++;
                                break;
                            }
                        }
                    } else {

                        if (maoJogador.getSomaDaMao() >= 17) {
                            System.out.println("\nVocê atingiu o limite de 17 pontos para essa rodada!");
                        } else {
                            System.out.println("\nVocê atingiu o limite de 5 hits para essa rodada!");
                        }

                        break;
                    }
                }
            }

            // CÓDIGOS DA VEZ DO COMPUTADOR
            if (maoJogador.getSomaDaMao() <= VALOR_PONTUACAO) {
                Mao maoComputador = new Mao(NOME_OPERADOR_MAQUINA);

                // JOGADOR COMEÇA COM DUAS CARTAS

                if (!maoComputador.push(baralho.pop(), baralho)) {
                    break;
                }

                if (!maoComputador.push(baralho.pop(), baralho)) {
                    break;
                }
                // maoComputador.push(baralho.pop());
                // maoComputador.push(baralho.pop());

                System.out.println("\nAs duas cartas iniciais do computador são:");
                maoComputador.imprimir();

                if (maoComputador.getSomaDaMao() == VALOR_PONTUACAO && maoJogador.getSomaDaMao() == VALOR_PONTUACAO) {
                    System.out.println("\nPuts! O computador também tirou 21, deu empate nessa rodada...");
                } else if (maoComputador.getSomaDaMao() == VALOR_PONTUACAO) {
                    System.out.println("\nPuts! O computador tirou 21, você perdeu essa rodada!");
                    pontosDoComputador++;
                } else if (maoComputador.getSomaDaMao() > maoJogador.getSomaDaMao()
                        && maoComputador.getSomaDaMao() < VALOR_PONTUACAO) {
                    System.out.println("\nPuts! O computador tirou uma mão maior que a sua, você perdeu essa rodada!");
                    pontosDoComputador++;
                } else if (maoComputador.getSomaDaMao() > VALOR_PONTUACAO) {
                    System.out.println("\nVocê ganhou essa rodada! O computador ultrapassou 21!");
                    pontosDoJogador++;
                } else if (maoComputador.getSomaDaMao() >= RESTRICAO_QUANTIDADE_PONTOS) {

                    System.out.println("\nO computador atingiu o limite de 17 pontos para essa rodada!");

                    if (maoComputador.getSomaDaMao() == maoJogador.getSomaDaMao()) {
                        System.out.println("\nHouve empate!");
                    } else if (maoComputador.getSomaDaMao() > maoJogador.getSomaDaMao()) {
                        System.out.println(
                                "\nPuts! O computador tirou uma mão maior que a sua, você perdeu essa rodada!");
                        pontosDoComputador++;
                    } else {
                        System.out.println("\nVocê ganhou essa rodada, pois fez mais pontos que o computador");
                        pontosDoJogador++;
                    }

                } else {

                    while (maoComputador.getSomaDaMao() < VALOR_PONTUACAO) {

                        if (maoComputador.getTopo() < RESTRICAO_QUANTIDADE_CARTAS
                                && maoComputador.getSomaDaMao() < 17) {
                            System.out.println("\nO computador faz um hit:");

                            if (!maoComputador.push(baralho.pop(), baralho)) {
                                break;
                            }

                            // maoComputador.push(baralho.pop());
                            maoComputador.imprimir();

                            if (maoComputador.getSomaDaMao() == VALOR_PONTUACAO
                                    && maoJogador.getSomaDaMao() == VALOR_PONTUACAO) {
                                System.out.println("\nPuts! O computador também tirou 21, deu empate nessa rodada...");
                            } else if (maoComputador.getSomaDaMao() > maoJogador.getSomaDaMao()
                                    && maoComputador.getSomaDaMao() <= VALOR_PONTUACAO) {
                                System.out.println(
                                        "\nPuts! O computador tirou uma mão maior que a sua, você perdeu essa rodada!");
                                pontosDoComputador++;
                                break;
                            } else if (maoComputador.getSomaDaMao() > VALOR_PONTUACAO) {
                                System.out.println("\nVocê ganhou essa rodada! O computador ultrapassou 21!");
                                pontosDoJogador++;
                            }
                        } else {

                            if (maoJogador.getSomaDaMao() >= 17) {
                                System.out.println("\nO computador atingiu o limite de 17 pontos para essa rodada!");
                            } else {
                                System.out.println("\nO computador atingiu o limite de 5 hits para essa rodada!");
                            }

                            if (maoComputador.getSomaDaMao() == maoJogador.getSomaDaMao()) {
                                System.out.println("\nHouve empate!");
                            } else if (maoComputador.getSomaDaMao() > maoJogador.getSomaDaMao()) {
                                System.out.println(
                                        "\nPuts! O computador tirou uma mão maior que a sua, você perdeu essa rodada!");
                                pontosDoComputador++;
                            } else {
                                System.out.println("\nVocê ganhou essa rodada, pois fez mais pontos que o computador");
                                pontosDoJogador++;
                            }

                            break;
                        }

                    }
                }
            }

            System.out.println("\nSeus pontos até aqui: " + pontosDoJogador);
            System.out.println("Os pontos do computador até aqui: " + pontosDoComputador);

            continuarJogando = persistirDigitoSaida(enter);

        } while (continuarJogando == 1);

        System.out.println("\nO jogo chegou ao fim!");

        System.out.println("\nSeus pontos: " + pontosDoJogador);
        System.out.println("Os pontos do computador: " + pontosDoComputador);

        if (pontosDoJogador == pontosDoComputador) {
            System.out.println("\nHouve empate!");
        } else if (pontosDoJogador > pontosDoComputador) {
            System.out.println("\nParabéns, você foi o campeão desse jogo!");
        } else {
            System.out.println("\nQue pena, você perdeu!");
        }

        if (!baralho.isEmpty()) {
            System.out.println("\nAs cartas que sobraram no baralho são:");

            baralho.imprimir();
        }
    }

    public static int persistirEntradaQuantidadeDeck(Scanner enter) {
        int numeroDigitado = 0;
        boolean validaEntradaUsuario = false;

        do {
            System.out.println(
                    "\nPara iniciarmos o jogo, indique quantos baralhos serão usados com os números de 1 a 7:");

            if (enter.hasNextInt()) {
                numeroDigitado = enter.nextInt();

                if (numeroDigitado < 1 || numeroDigitado > 7) {
                    enter.nextLine();
                    System.out.println("\nDigite um valor entre 1 e 7, inteiro");
                } else {
                    validaEntradaUsuario = true;
                }

            } else {
                enter.nextLine();
                System.out.println("\nDigite um valor válido!");
            }

        } while (!validaEntradaUsuario);

        return numeroDigitado;
    }

    public static int persistirEntradaStandHit(Scanner enter) {
        int numeroDigitado = 0;
        boolean validaEntradaUsuario = false;

        do {
            System.out.println(
                    "\nDeseja fazer um hit (comprar uma carta) ou um stand (parar)? (Digite 1 para hit ou 2 para stand)");

            if (enter.hasNextInt()) {
                numeroDigitado = enter.nextInt();

                if (numeroDigitado < 1 || numeroDigitado > 2) {
                    enter.nextLine();
                    System.out.println("\nDigite um valor entre 1 e 2, inteiro");
                } else {
                    validaEntradaUsuario = true;
                }

            } else {
                enter.nextLine();
                System.out.println("\nDigite um valor válido!");
            }

        } while (!validaEntradaUsuario);

        return numeroDigitado;
    }

    public static int persistirDigitoSaida(Scanner enter) {
        int numeroDigitado = 0;
        boolean validaEntradaUsuario = false;

        do {
            System.out
                    .println("\nGostaria de continuar jogando? (Digite 1 para sim ou qualquer outro número para sair)");

            if (enter.hasNextInt()) {
                numeroDigitado = enter.nextInt();
                validaEntradaUsuario = true;
            } else {
                enter.nextLine();
                System.out.println("\nDigite um valor válido!");
            }

        } while (!validaEntradaUsuario);

        return numeroDigitado;
    }

}