package br.com.rasmoo.restaurante.util;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.entity.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CargaDeDadosUtil {

    public CargaDeDadosUtil() {
    }

    public static void cadastrarClientes(EntityManager entityManager){
        Endereco endereco = new Endereco("88113-815", "Rua Joice Cecília Correia", "bl k, apto 404", "Santa Catarina", "São José");
        Cliente kiev = new Cliente("010.037.509-07","kievmaia@gmail.com", "Kiev", new Contato("48", "999166329"));
        kiev.addEndereco(endereco);

        ClienteDao clienteDao = new ClienteDao(entityManager);
        clienteDao.cadastrar(kiev);
        cadastrarOrdensClientes(entityManager, kiev);
        entityManager.flush();
    }

    public static void cadastrarOrdensClientes(EntityManager entityManager, Cliente kiev){
        CardapioDao cardapioDao = new CardapioDao(entityManager);
        OrdemDao ordemDao = new OrdemDao(entityManager);
        Ordem ordem = new Ordem(kiev);
        ordem.addOrdemCardapio(new OrdensCardapio(ordem, cardapioDao.consultarPorId(1L), 2L));
        ordem.addOrdemCardapio(new OrdensCardapio(ordem, cardapioDao.consultarPorId(2L), 3L));

        ordemDao.cadastrar(ordem);
        entityManager.flush();
    }

    public static void cadastrarCategoria(EntityManager entityManager){
        Categoria entrada = new Categoria("Entradas");
        Categoria salada = new Categoria("Saladas");
        Categoria principal = new Categoria("Pratos Principais");

        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        categoriaDao.cadastrar(entrada);
        entityManager.flush();
        categoriaDao.cadastrar(salada);
        entityManager.flush();
        categoriaDao.cadastrar(principal);
        entityManager.flush();
    }

    public static void cadastrarProdutosCardapio(EntityManager entityManager){
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        CardapioDao cardapioDao = new CardapioDao(entityManager);

        List<Categoria> categorias = categoriaDao.consultarTodos();
        Cardapio moqueca = new Cardapio("Moqueca", "Peixe branco, banana da terra, arroz e farofa",
                true, BigDecimal.valueOf(95.00), categorias.get(2));
        Cardapio spaguetti = new Cardapio("Spaguetti", "Spaguetti ao molho de parmesão e cogumelos",
                true, BigDecimal.valueOf(68.00), categorias.get(2));
        Cardapio bife = new Cardapio("Bife", "Bife acebolado com arroz branco, farofa e batata frita",
                true, BigDecimal.valueOf(59.00), categorias.get(2));
        Cardapio cordeiro = new Cardapio("Cordeiro", "Cordeiro com risoto de funghi",
                true, BigDecimal.valueOf(88.00), categorias.get(2));
        Cardapio burrata = new Cardapio("Burrata", "Tomates queimados, rúcula e torrada",
                true, BigDecimal.valueOf(15.00), categorias.get(0));
        Cardapio bruschetta = new Cardapio("Bruschetta", "Tomate mussarela e manjericão",
                true, BigDecimal.valueOf(20.00), categorias.get(0));
        Cardapio scappeta = new Cardapio("Scappeta", "Raque de linguiça e torradinhas",
                true, BigDecimal.valueOf(25.00), categorias.get(0));
        Cardapio caprese = new Cardapio("Caprese", "Mini rúcula e mussarela",
                true, BigDecimal.valueOf(47.00), categorias.get(1));
        Cardapio ceasar = new Cardapio("Ceasar", "Salada de frango com molho ceasar",
                true, BigDecimal.valueOf(40.00), categorias.get(1));
        Cardapio chevre = new Cardapio("Chevre", "Mix de folhas, mostarda e mel",
                true, BigDecimal.valueOf(59.00), categorias.get(1));

        cardapioDao.cadastrar(moqueca);
        cardapioDao.cadastrar(spaguetti);
        cardapioDao.cadastrar(bife);
        cardapioDao.cadastrar(cordeiro);
        cardapioDao.cadastrar(burrata);
        cardapioDao.cadastrar(bruschetta);
        cardapioDao.cadastrar(scappeta);
        cardapioDao.cadastrar(caprese);
        cardapioDao.cadastrar(ceasar);
        cardapioDao.cadastrar(chevre);
        entityManager.flush();
        entityManager.clear();
    }
}
