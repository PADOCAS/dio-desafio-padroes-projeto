package dio.padroes.strategy;

public class TesteStrategy {
    public static void main(String[] args) {
        Comportamento comportamentoNormal = new ComportamentoNormal();
        Comportamento comportamentoDefensivo = new ComportamentoDefensivo();
        Comportamento comportamentoAgressivo = new ComportamentoAgressivo();

        Robo robo = new Robo();
        robo.setComportamentoStrategy(comportamentoNormal);
        robo.mover();
        robo.mover();

        robo.setComportamentoStrategy(comportamentoDefensivo);
        robo.mover();
        robo.mover();
        robo.mover();

        robo.setComportamentoStrategy(comportamentoAgressivo);
        robo.mover();
        robo.mover();
        robo.mover();
        robo.mover();
    }
}
