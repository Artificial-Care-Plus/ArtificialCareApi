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

        while (rs.next()) {
            dicasLista.add(new Dicas(
                    rs.getLong("id_dicas"),
                    rs.getString("categoria"),
                    rs.getString("texto")
            ));
        }

        con.close();
        return dicasLista;
    }

    public static Dicas find(Long id) throws SQLException {
        var con = ConnectionFactory.getConnection();

        var rss = con.prepareStatement("select * from t_ac_dicas where id_dicas = ?");
        rss.setLong(1, id);

        var rs = rss.executeQuery();
        rs.next();

        if (rs.isAfterLast()) {
            return null;
        }

        Dicas dica = new Dicas(
                rs.getLong("id_dicas"),
                rs.getString("categoria"),
                rs.getString("texto")
        );

        con.close();

        return dica;
    }

    public static void create(Dicas dicas) throws SQLException {
        var con = ConnectionFactory.getConnection();

        var rss = con.prepareStatement("insert into t_ac_dicas (categoria, texto) values (?, ?)");
        rss.setString(1, dicas.getCategoria());
        rss.setString(2, dicas.getTexto());

        rss.executeUpdate();

        con.close();

    }

    public static void update(Long id, Dicas dicas) throws SQLException {
        var con = ConnectionFactory.getConnection();

        var ps = con.prepareStatement("update t_ac_dicas set categoria = ?, texto = ? where id_dicas = ?");
        ps.setString(1, dicas.getCategoria());
        ps.setString(2, dicas.getTexto());
        ps.setLong(3, id);

        ps.executeUpdate();

        con.close();
    }

    public static void delete(Long id) throws SQLException {
        var con = ConnectionFactory.getConnection();

        var ps = con.prepareStatement("delete from t_ac_dicas where id_dicas = ?");
        ps.setLong(1, id);

        ps.executeUpdate();

        con.close();

    }
}
