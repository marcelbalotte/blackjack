public class MaoComputador {

    private Carta[] cartasComputador;
    private int topo;
    private int somaDaMaoDoComputador;

    public MaoComputador() {
        this.cartasComputador = new Carta[5];
        this.topo = 0;
    }

    public void imprimir() {
        for (int i = 0; i < cartasComputador.length; i++) {
            if(cartasComputador[i] != null) {
                System.out.println(cartasComputador[i]);

            }
        }
        System.out.println("O valor total da mão do computador é: "+somaDaMaoDoComputador);
    }

    public void push(Carta carta) {
        this.somaDaMaoDoComputador += carta.getNumero();

        this.cartasComputador[this.topo] = carta;
        this.topo++;
    }

    public int getSomaDaMaoDoComputador() { return somaDaMaoDoComputador; }

}