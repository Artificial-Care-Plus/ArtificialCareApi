package br.com.fiap.artificialcareapi.dao;

import br.com.fiap.artificialcareapi.beans.Dicas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DicasDao {
    public static List<Dicas> findAll() throws SQLException {
        List<Dicas> dicasLista = new ArrayList<>();
        var con = ConnectionFactory.getConnection();

        var rs = con.createStatement().executeQuery("select * from t_ac_dicas");
        while(rs.next()){
            dicasLista.add(new Dicas(
                    rs.getLong("id"),
                    rs.getString("categoria"),
                    rs.getString("texto")
            ));
        }

        con.close();
        return dicasLista;
    }
}
