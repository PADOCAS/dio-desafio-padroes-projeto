package dio.padroes.facade;

import dio.padroes.facade.subsistema.cep.CepApi;
import dio.padroes.facade.subsistema.crm.CrmService;

public class Facade {

    public void migrarCliente(String nome, String cep) {
        if (nome != null && cep != null) {
            String cidade = CepApi.getInstancia().getCidade(cep);
            String estado = CepApi.getInstancia().getEstado(cep);

            CrmService.gravarCliente(nome, cep, cidade, estado);
        }
    }
}
