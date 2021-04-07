public class Mao {
    private Carta[] cartas;
    private int topo;
    private int somaDaMao;
    private String operador;

    public Mao(String operador) {
        this.cartas = new Carta[5];
        this.topo = 0;
        this.operador = operador;
    }

    public void imprimir() {
        for (int i = 0; i < cartas.length; i++) {
            if (cartas[i] != null) {
                System.out.println(cartas[i]);

            }
        }
        System.out.println("O valor total da mão do " + this.operador + " é: " + this.somaDaMao);
    }

    public void push(Carta carta) {
        this.somaDaMao += carta.getNumero();

        this.cartas[this.topo] = carta;
        this.topo++;
    }

    public int getSomaDaMao() {
        return somaDaMao;
    }

    public String getOperador() {
        return this.operador;
    }
}
