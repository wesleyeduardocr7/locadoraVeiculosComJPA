package repositorio;

import com.mysql.cj.xdevapi.Client;
import modelo.Cliente;
import modelo.Cnh;
import modelo.builder.ClienteBuilder;
import modelo.builder.CnhBuilder;
import org.junit.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;


public class ClienteRepositorioTest {

    private EntityManager manager;
    private static EntityManagerFactory factory;
    private ClienteRepository clienteRepository;

    @BeforeClass
    public static void inicio() {
        factory = Persistence.createEntityManagerFactory("sistemalocacao-test");
    }

    @Before
    public void antes() {
        manager = factory.createEntityManager();
        clienteRepository = new ClienteRepository(manager);
        manager.getTransaction().begin();
    }

    @After
    public void depois() {
        manager.getTransaction().rollback();
    }

    @AfterClass
    public static void fim() {
        factory.close();
    }


    // TESTE 06
    //Deve atualizar dados da habilitação do cliente.
    @Test
    public void deveAlterarDadosCnhCliente() {

        Cliente cliente = ClienteBuilder.umCliente().comNome("Wesley Eduardo").constroi();

        clienteRepository.salvaOuAtualiza(cliente);

        Cnh cnh = CnhBuilder.umaCnh().constroi();

        cliente.setCnh(cnh);

        cliente.getCnh().setCategoriaCnh("B");
        cliente.getCnh().setNumeroCnh("54545454");
        cliente.getCnh().setValidadeCnh(LocalDate.now());

        clienteRepository.salvaOuAtualiza(cliente );
        manager.flush();

        List<Cliente> clientes = clienteRepository.buscaPor("Wesley Eduardo");

        Assert.assertThat(clientes.size(), is(1));
        Assert.assertThat(clientes.get(0).getNome(), is("Wesley Eduardo") );

        Assert.assertTrue(clientes.isEmpty() );
    }

}