package br.com.fiap.artificialcareapi.bo;

import br.com.fiap.artificialcareapi.beans.Mensagem;
import br.com.fiap.artificialcareapi.beans.Usuario;
import br.com.fiap.artificialcareapi.dao.UsuarioDao;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {

    private static boolean validar(String email) throws SQLException {
        for (Usuario user : UsuarioDao.findAll()) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }

        /* O restante das validacoes sao feitas no app e no site (verificam tamanho, data e se está "nulo") */
        return true;
    }

    public static List<Usuario> findAll() throws SQLException {
        return UsuarioDao.findAll();
    }

    public static Mensagem create(Usuario usuario) throws SQLException {
        if(!validar(usuario.getEmail())) {
            return new Mensagem("Email já cadastrado", false);
        }

        try {
            UsuarioDao.create(usuario);
            } catch(Exception e){
            return new Mensagem("Erro no servidor ao criar usuário", false);
        }

        return new Mensagem("Usuário criado com sucesso", true);
    }



    public static Mensagem delete(String email) throws SQLException {
        if (validar(email)){
            return new Mensagem("Email não encontrado", false);
        }
        try {
            UsuarioDao.delete(email);
        } catch (SQLException e) {
            return new Mensagem("Erro no servidor ao deletar usuário", false);
        }
        return new Mensagem("Usuário deletado com sucesso", true);
    }

    public static Mensagem update(String email, Usuario usuario) throws SQLException {
        if (validar(usuario.getEmail())){
            return new Mensagem("Email não encontrado", false);
        }
        try {
            UsuarioDao.update(email, usuario);
        } catch (SQLException e) {
            return new Mensagem("Erro no servidor ao atualizar usuário", false);
        }
        return new Mensagem("Usuário atualizado com sucesso", true);
    }

    public static Usuario find(String email) throws SQLException {
        return UsuarioDao.find(email);
    }
}
