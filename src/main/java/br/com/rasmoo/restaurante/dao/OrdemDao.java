package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cliente;
import br.com.rasmoo.restaurante.entity.Ordem;
import br.com.rasmoo.restaurante.vo.ItensPrincipaisVO;

import javax.persistence.EntityManager;
import java.util.List;

public class OrdemDao {

    private EntityManager entityManager;

    public OrdemDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(final Ordem ordem) {
        this.entityManager.persist(ordem);
    }

    public Ordem consultar(final Long id) {
        return this.entityManager.find(Ordem.class, id);
    }

    public void atualizar(final Ordem ordem) {
        this.entityManager.merge(ordem);
    }

    public void excluir(final Ordem ordem) {
        this.entityManager.remove(ordem);
    }

    public List<Ordem> consultarTodos() {
        String sql = "SELECT o FROM Ordem o";
        return this.entityManager.createQuery(sql, Ordem.class).getResultList();
    }

    public Ordem joinFetchClient(final Long id) {
        String sql = "SELECT o FROM Ordem o JOIN FETCH o.cliente  WHERE o.id = :id";
        return this.entityManager.createQuery(sql, Ordem.class).setParameter("id", id).getSingleResult();
    }

    public List<ItensPrincipaisVO> consultarItensMaisVendidos(){
        String sql = "SELECT new br.com.rasmoo.restaurante.vo.ItensPrincipaisVO(c.nome, SUM(oc.quantidade)) FROM Ordem o " +
                "JOIN OrdensCardapio oc ON o.id = oc.cardapio.id " +
                "JOIN oc.cardapio c " +
                "GROUP BY c.nome "+
                "ORDER BY SUM(oc.quantidade) DESC";
        return this.entityManager.createQuery(sql, ItensPrincipaisVO.class).getResultList();
    }
}
