package br.com.fiap.artificialcareapi.bo;

import br.com.fiap.artificialcareapi.beans.Dicas;
import br.com.fiap.artificialcareapi.beans.Mensagem;
import br.com.fiap.artificialcareapi.dao.DicasDao;

import java.sql.SQLException;
import java.util.List;

public class DicasService {

    /* As poucas validações que possuimos são feitas no front-end  */
    public static List<Dicas> findAll() throws SQLException {
        return DicasDao.findAll();
    }

    public static Dicas find(Long id) throws SQLException {
        return DicasDao.find(id);
    }

    public static Mensagem create(Dicas dicas) {
        try {
            DicasDao.create(dicas);
            return new Mensagem("Dica criada com sucesso", true);
        } catch (Exception e) {
            return new Mensagem("Erro ao criar dica", false);
        }
    }

    public static Mensagem update(Long id, Dicas dicas) throws SQLException {
        DicasDao.update(id, dicas);
        return new Mensagem("Dica atualizada com sucesso", true);
    }

    public static Mensagem delete(Long id) throws SQLException {
        DicasDao.delete(id);
        return new Mensagem("Dica deletada com sucesso", true);
    }
}
