import java.util.Scanner;

//TODO: fazer restrição de 5 cartas ou até número 17
//TODO: fazer classe mão genérica
//TODO: carta Ás valendo 1 ou 11
//TODO: persistir os valores de entrada

public class Main {

    public static void main(String[] args) {
        Scanner enter = new Scanner(System.in);

        System.out.println("############################################################################################" +
                "\n############################# BEM-VINDO AO CYBERBLACKJACK 2077 #############################" +
                "\n############################################################################################");

        int continuarJogando;
        int pontosDoJogador = 0;
        int pontosDoComputador = 0;

        System.out.println("\nPara iniciarmos o jogo, indique quantos baralhos serão usados com os números de 1 a 7:");
        int qntBaralhos = enter.nextInt();
        Baralho baralho = new Baralho(qntBaralhos);
        System.out.println("\nComeçaremos o jogo com um deck de " + qntBaralhos * 52 + " cartas!");

        do {

            //CÓDIGOS DA VEZ DO JOGADOR
            MaoJogador maoJogador = new MaoJogador();
            maoJogador.push(baralho.pop());
            maoJogador.push(baralho.pop());

            System.out.println("\nPreparado? Vamos lá! Essas são suas duas cartas iniciais:");
            maoJogador.imprimir();

            if (maoJogador.getSomaDaMaoDoJogador() == 21) {
                System.out.println("\nEita, que sorte! Você tirou 21 nas suas duas cartas inicias, vamos aguardar a vez do computador...");
            } else {
                int comprarOutraCarta = 0;

                while (comprarOutraCarta != 2) {
                    System.out.println("\nDeseja fazer um hit (comprar uma carta) ou um stand (parar)? (Digite 1 para hit ou 2 para stand)");
                    comprarOutraCarta = enter.nextInt();

                    if (comprarOutraCarta == 1) {
                        maoJogador.push(baralho.pop());

                        System.out.println("\nSuas cartas agora são:");
                        maoJogador.imprimir();

                        if (maoJogador.getSomaDaMaoDoJogador() == 21) {
                            System.out.println("\nBoa! Você tirou 21, agora vamos aguardar a vez do computador...");
                            break;
                        } else if (maoJogador.getSomaDaMaoDoJogador() > 21) {
                            System.out.println("\nPuts, que azar, você tirou mais que 21, o computador levou essa rodada!");
                            pontosDoComputador++;
                            break;
                        }
                    }
                }
            }

            //CÓDIGOS DA VEZ DO COMPUTADOR
            if (maoJogador.getSomaDaMaoDoJogador() <= 21) {
                MaoComputador maoComputador = new MaoComputador();
                maoComputador.push(baralho.pop());
                maoComputador.push(baralho.pop());

                System.out.println("\nAs duas cartas iniciais do computador são:");
                maoComputador.imprimir();

                if (maoComputador.getSomaDaMaoDoComputador() == 21 && maoJogador.getSomaDaMaoDoJogador() == 21) {
                    System.out.println("\nPuts! O computador também tirou 21, deu empate nessa rodada...");
                } else if (maoComputador.getSomaDaMaoDoComputador() == 21) {
                    System.out.println("\nPuts! O computador tirou 21, você perdeu essa rodada!");
                    pontosDoComputador++;
                } else if (maoComputador.getSomaDaMaoDoComputador() > maoJogador.getSomaDaMaoDoJogador() && maoComputador.getSomaDaMaoDoComputador() < 21) {
                    System.out.println("\nPuts! O computador tirou uma mão maior que a sua, você perdeu essa rodada!");
                    pontosDoComputador++;
                } else if (maoComputador.getSomaDaMaoDoComputador() > 21) {
                    System.out.println("\nVocê ganhou essa rodada! O computador ultrapassou 21!");
                    pontosDoJogador++;
                } else {

                    while (maoComputador.getSomaDaMaoDoComputador() < 21) {
                        System.out.println("\nO computador faz um hit:");
                        maoComputador.push(baralho.pop());
                        maoComputador.imprimir();

                        if (maoComputador.getSomaDaMaoDoComputador() == 21 && maoJogador.getSomaDaMaoDoJogador() == 21) {
                            System.out.println("\nPuts! O computador também tirou 21, deu empate nessa rodada...");
                        } else if (maoComputador.getSomaDaMaoDoComputador() > maoJogador.getSomaDaMaoDoJogador() && maoComputador.getSomaDaMaoDoComputador() <= 21) {
                            System.out.println("\nPuts! O computador tirou uma mão maior que a sua, você perdeu essa rodada!");
                            pontosDoComputador++;
                            break;
                        } else if (maoComputador.getSomaDaMaoDoComputador() > 21) {
                            System.out.println("\nVocê ganhou essa rodada! O computador ultrapassou 21!");
                            pontosDoJogador++;
                        }
                    }
                }
            }

            System.out.println("\nSeus pontos até aqui: "+pontosDoJogador);
            System.out.println("Os pontos do computador até aqui: "+pontosDoComputador);

            System.out.println("\nGostaria de continuar jogando? (Digite 1 para sim ou 2 para não)");
            continuarJogando = enter.nextInt();

        } while (continuarJogando != 2);

        System.out.println("\nO jogo chegou ao fim!");

        System.out.println("\nSeus pontos: "+pontosDoJogador);
        System.out.println("Os pontos do computador: "+pontosDoComputador);

        if(pontosDoJogador == pontosDoComputador) {
            System.out.println("\nVocê e o computador empataram!");
        } else if(pontosDoJogador > pontosDoComputador) {
            System.out.println("\nParabéns, você foi o campeão desse jogo!");
        } else if(pontosDoComputador > pontosDoJogador) {
            System.out.println("\nPoxa, o computador venceu de você dessa vez, mas não se preocupe, você pode tentar novamente a qualquer momento!");
        }
        System.out.println("Até a próxima jogatina!");

        System.out.println("\nAs cartas que sobraram no baralho são:");
        baralho.imprimir();
    }

}