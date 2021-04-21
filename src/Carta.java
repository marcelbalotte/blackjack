public class Carta {

    private Naipe naipe;
    private int numero;

    public Carta(Naipe naipe, int numero) {
        this.naipe = naipe;
        this.numero = numero;
    }

    public Naipe getNaipe() {
        return naipe;
    }

    public int getNumero() {

        //Q, J, K VALEM 10
        if (this.numero >= 11) {
            return 10;
        }

        //ÁS VALE 11 POR DEFAULT. A LÓGICA PRA VALIDAR SE VALE 1 OU 11 ESTÁ NA CLASSE Mao
        if (this.numero == 1) {
            return 11;
        }

        return this.numero;
    }

    public String toString() {
        String numeroEmString = "";

        switch (this.numero) {
        case 1:
            numeroEmString = "Ás";
            break;
        case 2:
            numeroEmString = "Dois";
            break;
        case 3:
            numeroEmString = "Três";
            break;
        case 4:
            numeroEmString = "Quatro";
            break;
        case 5:
            numeroEmString = "Cinco";
            break;
        case 6:
            numeroEmString = "Seis";
            break;
        case 7:
            numeroEmString = "Sete";
            break;
        case 8:
            numeroEmString = "Oito";
            break;
        case 9:
            numeroEmString = "Nove";
            break;
        case 10:
            numeroEmString = "Dez";
            break;
        case 11:
            numeroEmString = "Valete";
            break;
        case 12:
            numeroEmString = "Dama";
            break;
        case 13:
            numeroEmString = "Rei";
            break;
        }

        return numeroEmString + " de " + naipe;
    }

}