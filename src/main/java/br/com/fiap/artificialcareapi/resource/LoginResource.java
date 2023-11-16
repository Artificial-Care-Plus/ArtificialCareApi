package br.com.fiap.artificialcareapi.resource;

import br.com.fiap.artificialcareapi.beans.Mensagem;
import br.com.fiap.artificialcareapi.beans.Usuario;
import br.com.fiap.artificialcareapi.bo.LoginService;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("login")
public class LoginResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Usuario usuario) throws SQLException {
        Mensagem usuarioMensagem = LoginService.login(usuario);

        return Response.ok(usuarioMensagem).build();
    }
}
