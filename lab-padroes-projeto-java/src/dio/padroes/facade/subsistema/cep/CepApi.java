package dio.padroes.facade.subsistema.cep;

/**
 * Singleton CEPAPI para através do CEP recebido devolver algumas informações
 * (cidade, estado..)
 * 
 */
public class CepApi {
    private static CepApi instancia = new CepApi();

    private CepApi() {
        super();
    }

    public static CepApi getInstancia() {
        return instancia;
    }

    public String getCidade(String cep) {
        return "Araraquara";
    }

    public String getEstado(String cep) {
        return "SP";
    }
}
