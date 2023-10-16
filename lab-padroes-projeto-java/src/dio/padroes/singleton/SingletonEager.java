package dio.padroes.singleton;

/**
 * Singleton 'Apressado'
 * Garantimos que nínguem fora da classe consiga instancia ela, e que ela tenha
 * apenas 1 instacia!
 */
public class SingletonEager {

    // Já instancia de cara!
    private static SingletonEager instancia = new SingletonEager();

    private SingletonEager() {
        super();
    }

    public static SingletonEager getInstancia() {
        if (instancia == null) {
            instancia = new SingletonEager();
        }

        return instancia;
    }
}
