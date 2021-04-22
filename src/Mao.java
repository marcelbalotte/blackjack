public class Mao {
    private Carta[] cartas;
    private int topo;
    private int somaDaMao;
    private String operador;

    public Mao(String operador) {
        this.cartas = new Carta[8]; // O MÁXIMO DE CARTAS POSSÍVEIS É 7, 2 INICIAIS E 5 HITS, MAS O ARRAY TEM
                                    // TAMANHO 8 PARA EVITAR EXCEPTION NA SÉTIMA POSIÇÃO
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

    public boolean push(Carta carta, Baralho baralho) {

        if (baralho.isEmpty()) {
            System.out.println("\nAcabaram as cartas do baralho!");
            return false;
        } else {

            // IF PARA VALIDAR O VALOR DO ÁS. SE O VALOR ULTRAPASSAR 21, É ATRIBUÍDO 1
            // PONTO, CASO CONTRÁRIO 11.
            if (carta.getNumero() == 11) {

                // 21 - 11 = 10
                if (this.somaDaMao >= 11) {
                    this.somaDaMao += 1;
                } else {
                    this.somaDaMao += 11;
                }

            } else { // OUTRAS CARTAS, SOMA NORMAL
                this.somaDaMao += carta.getNumero();
            }

            this.cartas[this.topo] = carta;
            this.topo++;

            return true;
        }
    }

    public int getSomaDaMao() {
        return somaDaMao;
    }

    public String getOperador() {
        return this.operador;
    }

    public int getTopo() {
        return this.topo;
    }

    public void setTopo(int topo) {
        this.topo = topo;
    }
}
