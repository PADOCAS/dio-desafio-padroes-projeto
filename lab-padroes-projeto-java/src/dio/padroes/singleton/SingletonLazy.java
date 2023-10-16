package dio.padroes.singleton;

/**
 * Singleton 'preguiçoso'
 * Garantimos que nínguem fora da classe consiga instancia ela, e que ela tenha
 * apenas 1 instacia!
 */
public class SingletonLazy {

    private static SingletonLazy instancia;

    private SingletonLazy() {
        super();
    }

    public static SingletonLazy getInstancia() {
        if (instancia == null) {
            instancia = new SingletonLazy();
        }

        return instancia;
    }

}
