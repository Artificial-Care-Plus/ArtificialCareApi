package br.com.fiap.artificialcareapi.bo;

import br.com.fiap.artificialcareapi.beans.Mensagem;
import br.com.fiap.artificialcareapi.beans.Usuario;

import java.sql.SQLException;
import java.util.UUID;

public class LoginService {
    public static Mensagem login(Usuario usuario) throws SQLException {
        var usuarioDados = UsuarioService.find(usuario.getEmail());
        if (usuarioDados == null) {
            return new Mensagem("Email não existe", false);
        }
        if (!usuarioDados.getSenha().equals(usuario.getSenha())) {
            return new Mensagem("Senha incorreta", false);
        }
        /* Criamos um UUID para ser usado de token no front-end */
        UUID uuid = UUID.randomUUID();
        return new Mensagem(uuid.toString(), true);
    }
}
