package one.dio.labpadroesprojetospring.handler;

public class CampoRequeridoException extends BusinessException {

    public CampoRequeridoException(String mensagem) {
        super("O campo " + mensagem + " é obrigatório.");
    }

}
