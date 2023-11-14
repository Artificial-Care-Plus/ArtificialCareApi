package br.com.fiap.artificialcareapi.dao;

import br.com.fiap.artificialcareapi.beans.Dicas;

import java.sql.SQLException;
import java.util.List;

public class DicasDao {
    public static List<Dicas> findAll() throws SQLException {
        var con = ConnectionFactory.getConnection();

        con.close();
    }
}
