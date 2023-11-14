package br.com.fiap.artificialcareapi.dao;

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
}
