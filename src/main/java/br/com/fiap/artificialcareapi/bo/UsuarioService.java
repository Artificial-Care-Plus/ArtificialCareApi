package br.com.fiap.artificialcareapi.bo;

import br.com.fiap.artificialcareapi.beans.Mensagem;
import br.com.fiap.artificialcareapi.beans.Usuario;
import br.com.fiap.artificialcareapi.dao.UsuarioDao;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {

    private static Mensagem validar(Usuario usuario) throws SQLException {
        for (Usuario user : UsuarioDao.findAll()) {
            if (user.getEmail().equals(usuario.getEmail())) {
                return new Mensagem("Email já cadastrado", false);
            }
        }
        if (!usuario.getEmail().contains("@")) {
            return new Mensagem("Email inválido", false);
        }
        if (usuario.getSenha().length() < 8) {
            return new Mensagem("Senha deve ter no mínimo 8 caracteres", false);
        }
        /* O restante das validacoes sao feitas no app e no site (verificam tamanho, data e se está "nulo")*/
        return new Mensagem("Usuário válido", true);
    }

    public static List<Usuario> findAll() throws SQLException {
        return UsuarioDao.findAll();
    }

    public static Mensagem create(Usuario usuario) throws SQLException {
        var validacao = validar(usuario);
        if (!validacao.isSucesso()) {
            return validacao;
        }

        try {
            UsuarioDao.create(usuario);
        } catch (Exception e) {
            return new Mensagem("Erro no servidor ao criar usuário", false);
        }

        return new Mensagem("Usuário criado com sucesso", true);
    }


    public static void delete(String email) throws SQLException {
        UsuarioDao.delete(email);
    }

    public static Mensagem update(String email, Usuario usuario) throws SQLException {
        /* Caso o usuario queira trocar o email verificamos se não é igual ao que ele usava antes e depois checamos
         * se não já está cadastrado */
        Mensagem validacao = validar(usuario);
        if (!email.equals(usuario.getEmail()) && validacao.isSucesso()) {
        return validacao;
    }
        try

    {
        UsuarioDao.update(email, usuario);
    } catch(
    SQLException e)

    {
        return new Mensagem("Erro no servidor ao atualizar usuário", false);
    }
        return new

    Mensagem("Usuário atualizado com sucesso",true);

}

    public static Usuario find(String email) throws SQLException {
        return UsuarioDao.find(email);
    }
}
