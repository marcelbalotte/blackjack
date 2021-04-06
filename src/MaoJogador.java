public class MaoJogador {

    private Carta[] cartasJogador;
    private int topo;
    private int somaDaMaoDoJogador;

    public MaoJogador() {
        this.cartasJogador = new Carta[5];
        this.topo = 0;
    }

    public void imprimir() {
        for (int i = 0; i < cartasJogador.length; i++) {
            if(cartasJogador[i] != null) {
                System.out.println(cartasJogador[i]);
            }
        }
        System.out.println("O valor total da sua mão é: "+somaDaMaoDoJogador);
    }

    public void push(Carta carta) {
        this.somaDaMaoDoJogador += carta.getNumero();

        this.cartasJogador[this.topo] = carta;
        this.topo++;
    }

    public int getSomaDaMaoDoJogador() { return somaDaMaoDoJogador; }

}