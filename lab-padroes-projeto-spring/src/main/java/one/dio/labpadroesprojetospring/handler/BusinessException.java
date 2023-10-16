package one.dio.labpadroesprojetospring.handler;

public class BusinessException extends RuntimeException {

    public BusinessException(String mensagem) {
        super(mensagem);
        System.out.println(mensagem);
    }

    public BusinessException(String mensagem, Object... params) {
        super(String.format(mensagem, params));
        System.out.println(mensagem);
    }

}
