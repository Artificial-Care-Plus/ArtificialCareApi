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
        var rs = con.createStatement().executeQuery("select * from t_ac_acoes a inner join t_ac_usuario u on a.t_ac_usuario_id_usuario = u.id_usuario");

        while (rs.next()) {
            acoesLista.add(new Acoes(

                    rs.getLong("id_acoes"),
                    new Usuario(
                            rs.getLong("t_ac_usuario_id_usuario"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getDate("dt_nasc"),
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

    public static Acoes findById(Long id) throws SQLException {
        var con = ConnectionFactory.getConnection();
        var rss = con.prepareStatement("select * from t_ac_acoes a inner join t_ac_usuario u on a.t_ac_usuario_id_usuario = u.id_usuario where a.id_acoes = ?");
        rss.setLong(1, id);
        var rs = rss.executeQuery();
        rs.next();

        if (rs.isAfterLast()) {
            return null;
        }
        Acoes acao = new Acoes(

                rs.getLong("id_acoes"),
                new Usuario(
                        rs.getLong("t_ac_usuario_id_usuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getDate("dt_nasc"),
                        rs.getDouble("peso"),
                        rs.getDouble("altura")
                ),
                rs.getInt("score"),
                rs.getString("descricao"),
                rs.getDouble("duracao"),
                rs.getDate("data")
        );


        con.close();

        return acao;
    }

    public static List<Acoes> findByEmailUsuario(String emailCliente, int qtd) throws SQLException {
        List<Acoes> acoesLista = new ArrayList<>();

        var con = ConnectionFactory.getConnection();
        var rss = con.prepareStatement("select * from t_ac_acoes a inner join t_ac_usuario u on a.t_ac_usuario_id_usuario = u.id_usuario where u.email = ? order by a.id_acoes desc fetch first ? rows only");
        rss.setString(1, emailCliente);
        rss.setInt(2, qtd);
        var rs = rss.executeQuery();

        while (rs.next()) {
            acoesLista.add(new Acoes(

                    rs.getLong("id_acoes"),
                    new Usuario(
                            rs.getLong("t_ac_usuario_id_usuario"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getDate("dt_nasc"),
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

    public static void update(Long id, Acoes acao) throws SQLException {
        var con = ConnectionFactory.getConnection();

        var ps = con.prepareStatement("update t_ac_acoes set score = ?, descricao = ?, duracao = ?, data = ? where id_acoes = ?");
        ps.setInt(1, acao.getScore());
        ps.setString(2, acao.getDescricao());
        ps.setDouble(3, acao.getDuracao());
        ps.setDate(4, acao.getData());
        ps.setLong(5, id);
        ps.executeUpdate();

        con.close();
    }

    public static void delete(Long id) throws SQLException {
        var con = ConnectionFactory.getConnection();

        var ps = con.prepareStatement("delete from t_ac_acoes where id_acoes = ?");
        ps.setLong(1, id);
        ps.executeUpdate();

        con.close();

    }

    public static void create(Acoes acao) throws SQLException {
        var con = ConnectionFactory.getConnection();

        var ps = con.prepareStatement("insert into t_ac_acoes (t_ac_usuario_id_usuario, score, descricao, duracao, data) values ((select T_AC_USUARIO.id_usuario from T_AC_USUARIO where email = ?), ?, ?, ?, ?)");

        ps.setString(1, acao.getUsuario().getEmail());
        ps.setInt(2, acao.getScore());
        ps.setString(3, acao.getDescricao());
        ps.setDouble(4, acao.getDuracao());
        ps.setDate(5, acao.getData());
        ps.executeUpdate();

        con.close();

    }
}
