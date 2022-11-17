package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cardapio;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardapioDao {

    private EntityManager entityManager;

    public CardapioDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(final Cardapio cardapio) {
        this.entityManager.persist(cardapio);
    }

    public Cardapio consultarPorId(final Long id) {
        return this.entityManager.find(Cardapio.class, id);
    }

    public Cardapio consultarPorNome(String filtro){
        try{
            String sql = "SELECT c FROM Cardapio c WHERE upper(c.nome)=upper(:nome)";
            return entityManager.createQuery(sql, Cardapio.class)
                    .setParameter("nome", filtro).getSingleResult();
        }catch (Exception e){
            return null;
        }

    }

    public List<Cardapio> consultarPorValor(BigDecimal filtro){
        try{
            String sql = "SELECT c FROM Cardapio c WHERE c.valor=:valor";
            return this.entityManager.createQuery(sql, Cardapio.class)
                    .setParameter("valor", filtro)
                    .getResultList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    public List<Cardapio> consultarTodos() {
        try{
            String sql = "SELECT c from Cardapio c";
            return this.entityManager.createQuery(sql, Cardapio.class).getResultList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    public void atualizar(final Cardapio cardapio) {
        this.entityManager.merge(cardapio);
    }

    public void excluir(final Cardapio cardapio) {
        this.entityManager.remove(cardapio);
    }
}
