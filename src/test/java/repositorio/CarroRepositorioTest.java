package repositorio;

import enums.TipoCarro;
import modelo.Carro;
import modelo.Sede;
import modelo.builder.CarroBuilder;
import modelo.builder.SedeBuilder;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;


public class CarroRepositorioTest {

    private EntityManager manager;
    private static EntityManagerFactory factory;
    private CarroRepository carroRepository;

    @BeforeClass
    public static void inicio() {
        factory = Persistence.createEntityManagerFactory("sistemalocacao-test");
    }

    @Before
    public void antes() {
        manager = factory.createEntityManager();
        carroRepository = new CarroRepository(manager);
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



    // TESTE 01
    //Deve recuperar todos os carros da classe Compacto
    @Test
    public void deveRetornarTodosOsCarrosCompactos() {

        SedeRepository SedeRepository = new SedeRepository(this.manager);

        Sede saoLuis = SedeBuilder.umaSede().comNome("Sao Luis").constroi();
        Sede imperatriz = SedeBuilder.umaSede().comNome("Imperatriz").constroi();

        SedeRepository.salvaOuAtualiza(saoLuis);
        SedeRepository.salvaOuAtualiza(imperatriz);
        manager.flush();


        // criando os Carros, cada um com um tipo
        Carro subcompacto = CarroBuilder.umCarro().constroi();
        subcompacto.setSedeLocacao(saoLuis);
        subcompacto.setSedeDevolucao(imperatriz);


        Carro compacto = CarroBuilder.umCarro().constroi();
        compacto.setSedeLocacao(saoLuis);
        compacto.setSedeDevolucao(imperatriz);


        Carro luxo = CarroBuilder.umCarro().constroi();
        luxo.setSedeLocacao(saoLuis);
        luxo.setSedeDevolucao(imperatriz);

        Carro grande = CarroBuilder.umCarro().constroi();
        grande.setSedeLocacao(saoLuis);
        grande.setSedeDevolucao(imperatriz);

        Carro medio = CarroBuilder.umCarro().constroi();
        medio.setSedeLocacao(saoLuis);
        medio.setSedeDevolucao(imperatriz);

        carroRepository.salvaOuAtualiza(subcompacto);
        carroRepository.salvaOuAtualiza(compacto);
        carroRepository.salvaOuAtualiza(luxo);
        carroRepository.salvaOuAtualiza(medio);
        carroRepository.salvaOuAtualiza(grande);

        // Testanto o Retorno da Lista de Carros Compactos
        List<Carro> tiposDeCarros = carroRepository.compacto();

    }

    // TESTE 02
    //Deve recuperar todos os carros da classe Luxo.
    @Test
    public void deveRetornarTodosOsCarrosLuxo() {

        SedeRepository SedeRepository = new SedeRepository(this.manager);

        Sede saoLuis = SedeBuilder.umaSede().comNome("Sao Luis").constroi();
        Sede imperatriz = SedeBuilder.umaSede().comNome("Imperatriz").constroi();

        SedeRepository.salvaOuAtualiza(saoLuis);
        SedeRepository.salvaOuAtualiza(imperatriz);
        manager.flush();


        // criando os Carros, cada um com um tipo
        Carro subcompacto = CarroBuilder.umCarro().constroi();
        subcompacto.setSedeLocacao(saoLuis);
        subcompacto.setSedeDevolucao(imperatriz);


        Carro compacto = CarroBuilder.umCarro().constroi();
        compacto.setSedeLocacao(saoLuis);
        compacto.setSedeDevolucao(imperatriz);


        Carro luxo = CarroBuilder.umCarro().constroi();
        luxo.setSedeLocacao(saoLuis);
        luxo.setSedeDevolucao(imperatriz);

        Carro grande = CarroBuilder.umCarro().constroi();
        grande.setSedeLocacao(saoLuis);
        grande.setSedeDevolucao(imperatriz);

        Carro medio = CarroBuilder.umCarro().constroi();
        medio.setSedeLocacao(saoLuis);
        medio.setSedeDevolucao(imperatriz);

        carroRepository.salvaOuAtualiza(subcompacto);
        carroRepository.salvaOuAtualiza(compacto);
        carroRepository.salvaOuAtualiza(luxo);
        carroRepository.salvaOuAtualiza(medio);
        carroRepository.salvaOuAtualiza(grande);

        // Testanto o Retorno da Lista de Carros Compactos
        List<Carro> tiposDeCarros = carroRepository.luxo();

    }


}