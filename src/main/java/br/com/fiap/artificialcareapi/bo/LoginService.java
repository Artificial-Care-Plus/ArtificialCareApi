package br.com.fiap.artificialcareapi.bo;

import br.com.fiap.artificialcareapi.beans.Mensagem;
import br.com.fiap.artificialcareapi.beans.Usuario;
import br.com.fiap.artificialcareapi.dao.UsuarioDao;

import java.sql.SQLException;
import java.util.UUID;

public class LoginService {
    private static Mensagem validar(Usuario usuario) {
        /* As outras validações serão feitas no front-end */
        if (!usuario.getEmail().contains("@")) {
            return new Mensagem("Email inválido", false);
        }

        return new Mensagem("Usuário válido", true);
    }


    public static Mensagem login(Usuario usuario) throws SQLException {
        var validacao = validar(usuario);
        if (!validacao.isSucesso()) {
            return validacao;
        }
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
