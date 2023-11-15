package br.com.fiap.artificialcareapi.dao;

import br.com.fiap.artificialcareapi.beans.Acoes;
import br.com.fiap.artificialcareapi.beans.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcoesDao {

    public static List<Acoes> findAll() throws SQLException {
        List<Acoes> acoesLista = new ArrayList<>();

        var con = ConnectionFactory.getConnection();
        var rs = con.createStatement().executeQuery("select * from t_ac_acoes a inner join t_ac_usuario u on a.t_ac_usuario_id = u.id");

        while(rs.next()) {
            acoesLista.add(new Acoes(

                    rs.getLong("id"),
                    new Usuario(
                            rs.getLong("t_ac_usuario_id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getDate("nascimento"),
                            rs.getDouble("peso"),
                            rs.getDouble("altura")
                    ),
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
