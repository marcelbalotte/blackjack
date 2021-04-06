import java.util.Random;

public class Baralho {

    private Carta[] cartas;
    private int topo;

    public Baralho(int qntBaralhos) {
        this.cartas = new Carta [52*qntBaralhos];
        this.topo = 0;

        int posicao = 0;

        for (int i = 0; i < qntBaralhos; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 1; k <= 13; k++) {
                    this.cartas[posicao] = new Carta(Naipe.values()[j], k);
                    posicao++;
                    topo++;
                }
            }
        }

        embaralhar(this.cartas);
    }

    public void imprimir() {
        for (int i = 0; i < topo; i++) {
            System.out.println(cartas[i]);
        }
    }

    public void embaralhar(Carta[] cartas) {
        Random randomico = new Random();

        for (int i = 1; i < cartas.length; i++) {
            int j = randomico.nextInt(cartas.length);

            Carta temporario = cartas[i];
            cartas[i] = cartas[j];
            cartas[j] = temporario;
        }
    }

    public void push(Carta carta) {
        if (! this.isFull()) {
            this.cartas[this.topo] = carta;
            this.topo++;
        } else {
            System.out.println("Não foi possível inserir o elemento "+carta+" pois a pilha já está cheia!");
        }
    }

    public Carta pop() {
        if (!this.isEmpty()) {
            this.topo--;
            return this.cartas[this.topo];
        } else {
            System.out.println("Não há mais cartas para comprar no baralho, o jogo chegou ao fim!");
            return cartas[topo];
        }
    }

    public boolean isFull() {
        if (this.topo == this.cartas.length) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        return this.topo == 0;
    }

    public void reset() { this.topo = 0; }

    public int size() { return this.topo; }

}