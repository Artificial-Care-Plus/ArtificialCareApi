package br.com.fiap.artificialcareapi.bo;

import br.com.fiap.artificialcareapi.beans.Mensagem;
import br.com.fiap.artificialcareapi.beans.Usuario;
import br.com.fiap.artificialcareapi.dao.UsuarioDao;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {

    public static List<Usuario> findAll() throws SQLException {
        return UsuarioDao.findAll();
    }

    public static Mensagem create(Usuario usuario) throws SQLException {
        Mensagem validado = validar(usuario);
        if(!validado.isSucesso()) {
            return new Mensagem(validado.getResposta(), validado.isSucesso());
        }

        try {
            UsuarioDao.create(usuario);
            } catch(Exception e){
            return new Mensagem("Erro no servidor ao criar usuário", false);
        }

        return new Mensagem("Usuário criado com sucesso", true);
    }

    private static Mensagem validar(Usuario usuario) throws SQLException {
        for (Usuario user : UsuarioDao.findAll()) {
            if (user.getEmail().equals(usuario.getEmail())) {
                return new Mensagem("Email já cadastrado", false);
            }
        }

        /* O restante das validacoes sao feitas no app e no site (verificam tamanho, data e se está "nulo") */
        return new Mensagem("Usuário validado", true);
    }
}
