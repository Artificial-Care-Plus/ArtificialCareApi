package br.com.fiap.artificialcareapi.dao;

import br.com.fiap.artificialcareapi.beans.Acoes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcoesDao {

    public static List<Acoes> findAll() throws SQLException {
        List<Acoes> acoesLista = new ArrayList<>();

        var con = ConnectionFactory.getConnection();
        var rs = con.createStatement().executeQuery("select * from t_ac_acoes");

        while(rs.next()) {
            acoesLista.add(new Acoes(
                    rs.getLong("id"),
                    rs.getLong("t_ac_usuario_id"),
                    rs.getInt("score"),
                    rs.getString("descricao"),
                    rs.getDouble("duracao"),
                    rs.getDate("data")
            ));
        }

        con.close();

        return acoesLista;
    }
}
