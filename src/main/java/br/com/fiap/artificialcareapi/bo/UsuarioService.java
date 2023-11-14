package br.com.fiap.artificialcareapi.bo;

import br.com.fiap.artificialcareapi.beans.Usuario;
import br.com.fiap.artificialcareapi.dao.UsuarioDao;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {

    public static List<Usuario> findAll() throws SQLException {
        return UsuarioDao.findAll();
    }
}
