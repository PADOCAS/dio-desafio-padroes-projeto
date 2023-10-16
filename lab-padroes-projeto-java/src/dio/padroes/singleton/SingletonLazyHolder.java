package dio.padroes.singleton;

/**
 * Singleton 'preguiçoso'
 * Garantimos que nínguem fora da classe consiga instancia ela, e que ela tenha
 * apenas 1 instacia!
 */
public class SingletonLazyHolder {

    private static class InstanceHolder {
        public static SingletonLazyHolder instancia = new SingletonLazyHolder();
    }

    private SingletonLazyHolder() {
        super();
    }

    public static SingletonLazyHolder getInstancia() {
        return InstanceHolder.instancia;
    }

}
