package br.com.fiap.artificialcareapi.bo;


import br.com.fiap.artificialcareapi.beans.Acoes;
import br.com.fiap.artificialcareapi.dao.AcoesDao;

import java.sql.SQLException;
import java.util.List;

public class AcoesService {


    public static List<Acoes> findAll() throws SQLException {
        return AcoesDao.findAll();
    }
}
