package one.dio.labpadroesprojetospring.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.dio.labpadroesprojetospring.handler.BusinessException;
import one.dio.labpadroesprojetospring.handler.CampoRequeridoException;
import one.dio.labpadroesprojetospring.model.Cliente;
import one.dio.labpadroesprojetospring.model.Endereco;
import one.dio.labpadroesprojetospring.repository.ClienteRepository;
import one.dio.labpadroesprojetospring.repository.EnderecoRepository;
import one.dio.labpadroesprojetospring.service.ClienteService;
import one.dio.labpadroesprojetospring.service.ViaCepService;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author Luciano
 * 
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    // Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Cliente> buscarTodos() {
        // Buscar todos os Clientes.
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        // Buscar Cliente por ID.
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new BusinessException("Nenhum cliente com ID: " + id + " encontrado.");
        }
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        // Buscar Cliente por ID, caso exista:
        Optional<Cliente> clienteBd = clienteRepository.findById(id);

        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        // Deletar Cliente por ID.
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new BusinessException("Nenhum cliente com ID: " + id + " encontrado.");
        }
    }

    private void salvarClienteComCep(Cliente cliente) {
        if (cliente != null
                && cliente.getEndereco() != null
                && cliente.getEndereco().getCep() != null) {
            // Verificar se o Endereco do Cliente já existe (pelo CEP).
            String cep = cliente.getEndereco().getCep();

            Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
                // Caso não exista, integrar com o ViaCEP e persistir o retorno.
                Endereco novoEndereco = viaCepService.consultarCep(cep);
                enderecoRepository.save(novoEndereco);
                return novoEndereco;
            });

            if (cliente.getNome() == null) {
                throw new CampoRequeridoException("nome");
            }

            if (cliente.getDataNascimento() == null) {
                throw new CampoRequeridoException("dataNascimento");
            }

            cliente.setEndereco(endereco);
        }

        // Inserir Cliente, vinculando o Endereco (novo ou existente).
        clienteRepository.save(cliente);
    }

}