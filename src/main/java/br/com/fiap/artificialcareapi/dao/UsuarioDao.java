package br.com.fiap.artificialcareapi.dao;

import br.com.fiap.artificialcareapi.beans.Mensagem;
import br.com.fiap.artificialcareapi.beans.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    public static List<Usuario> findAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        var con = ConnectionFactory.getConnection();

        var rs = con.createStatement().executeQuery("SELECT * FROM T_AC_USUARIO");
        while(rs.next()){
            usuarios.add(new Usuario(
                    rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getDate("nascimento"),
                    rs.getDouble("peso"),
                    rs.getDouble("altura")
            )
            );
        }
        con.close();
        System.out.println(usuarios);
        return usuarios;
    }

    public static void create(Usuario usuario) throws SQLException {
            var con = ConnectionFactory.getConnection();

            var rs = con.prepareStatement("INSERT INTO T_AC_USUARIO (nome, email, senha, nascimento, peso, altura) VALUES (?, ?, ?, ?, ?, ?)");
            rs.setString(1, usuario.getNome());
            rs.setString(2, usuario.getEmail());
            rs.setString(3, usuario.getSenha());
            rs.setDate(4, usuario.getNascimento());
            rs.setDouble(5, usuario.getPeso());
            rs.setDouble(6, usuario.getAltura());
            rs.executeUpdate();

            con.close();

    }

    public static void delete(String email) throws SQLException {
            var con = ConnectionFactory.getConnection();

            var rs = con.prepareStatement("DELETE FROM T_AC_USUARIO WHERE email = ?");
            rs.setString(1, email);
            rs.executeUpdate();

            con.close();

    }

    public static void update(String email, Usuario usuario) throws SQLException {
        var con = ConnectionFactory.getConnection();

        var rs = con.prepareStatement("UPDATE T_AC_USUARIO SET nome = ?, email = ?, senha = ?, nascimento = ?, peso = ?, altura = ? WHERE email = ?");
        rs.setString(1, usuario.getNome());
        rs.setString(2, usuario.getEmail());
        rs.setString(3, usuario.getSenha());
        rs.setDate(4, usuario.getNascimento());
        rs.setDouble(5, usuario.getPeso());
        rs.setDouble(6, usuario.getAltura());
        rs.setString(7, email);
        rs.executeUpdate();

        con.close();
    }
}
