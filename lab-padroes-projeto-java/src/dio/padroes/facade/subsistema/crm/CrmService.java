package dio.padroes.facade.subsistema.crm;

/**
 * Classe apenas com método static para gravar cliente, não precisa ser
 * instanciada (construtor private)
 */
public class CrmService {

    private CrmService() {
        super();
    }

    public static void gravarCliente(String nome, String cep, String cidade, String estado) {
        System.out.println("Cliente salvo no sistema de CRM.");
        System.out.println(nome);
        System.out.println(cep);
        System.out.println(cidade);
        System.out.println(estado);
    }
}