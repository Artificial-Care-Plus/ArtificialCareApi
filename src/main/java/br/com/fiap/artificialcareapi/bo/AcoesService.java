package br.com.fiap.artificialcareapi.bo;


import br.com.fiap.artificialcareapi.beans.Acoes;
import br.com.fiap.artificialcareapi.beans.Mensagem;
import br.com.fiap.artificialcareapi.dao.AcoesDao;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

public class AcoesService {
    /* As poucas validações que possuimos são feitas no front-end  */
    public static List<Acoes> findAll() throws SQLException {
        return AcoesDao.findAll();
    }

    public static Acoes findById(Long id) throws SQLException {
        return AcoesDao.findById(id);
    }

    public static List<Acoes> findByEmailUsuario(String emailCliente, int qtd) throws SQLException {
        return AcoesDao.findByEmailUsuario(emailCliente, qtd);
    }

    public static Mensagem update(Long id, Acoes acao) throws SQLException {
        AcoesDao.update(id,acao);
        return new Mensagem("Ação atualizada com sucesso", true);
    }

    public static Mensagem delete(Long id) throws SQLException {
        AcoesDao.delete(id);
        return new Mensagem("Ação deletada com sucesso", true);
    }

    public static void create(Acoes acao) throws SQLException {
        AcoesDao.create(acao);
    }
}
