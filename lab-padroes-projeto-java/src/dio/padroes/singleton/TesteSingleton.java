package dio.padroes.singleton;

public class TesteSingleton {
    public static void main(String[] args) {
        // LAZY
        SingletonLazy lazy = SingletonLazy.getInstancia();
        System.out.println(lazy);
        // Deve ter o mesmo endereço de memória!
        lazy = SingletonLazy.getInstancia();
        System.out.println(lazy);

        // EAGER
        SingletonEager eager = SingletonEager.getInstancia();
        System.out.println(eager);
        // Deve ter o mesmo endereço de memória!
        eager = SingletonEager.getInstancia();
        System.out.println(eager);

        // LAZY HOLDER
        SingletonLazyHolder lazyHolder = SingletonLazyHolder.getInstancia();
        System.out.println(lazyHolder);
        // Deve ter o mesmo endereço de memória!
        lazyHolder = SingletonLazyHolder.getInstancia();
        System.out.println(lazyHolder);
    }
}
