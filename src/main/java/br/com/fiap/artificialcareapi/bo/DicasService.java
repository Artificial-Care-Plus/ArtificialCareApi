package br.com.fiap.artificialcareapi.bo;

import br.com.fiap.artificialcareapi.beans.Dicas;
import br.com.fiap.artificialcareapi.dao.DicasDao;

import java.sql.SQLException;
import java.util.List;

public class DicasService {
    public static List<Dicas> findAll() throws SQLException {
        return DicasDao.findAll();
    }
}
