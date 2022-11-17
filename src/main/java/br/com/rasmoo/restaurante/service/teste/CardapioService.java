package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CardapioService {

    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManagerRasFood();
        entityManager.getTransaction().begin();
        CargaDeDadosUtil.cadastrarCategoria(entityManager);
        CargaDeDadosUtil.cadastrarProdutosCardapio(entityManager);

        CardapioDao cardapioDao = new CardapioDao(entityManager);
        List<Cardapio> listaCardapio =  cardapioDao.consultarPorValor(BigDecimal.valueOf(59.00));
        Cardapio produto = cardapioDao.consultarPorNome("moqueca");
        System.out.println("Lista de produtos por valor: " + listaCardapio);
        System.out.println("Produto pesquisado: " + produto);
        entityManager.close();
    }
}
