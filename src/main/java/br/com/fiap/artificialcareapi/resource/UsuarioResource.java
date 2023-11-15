package br.com.fiap.artificialcareapi.resource;

import br.com.fiap.artificialcareapi.beans.Acoes;
import br.com.fiap.artificialcareapi.beans.Mensagem;
import br.com.fiap.artificialcareapi.beans.Usuario;
import br.com.fiap.artificialcareapi.bo.AcoesService;
import br.com.fiap.artificialcareapi.bo.UsuarioService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("usuario")
public class UsuarioResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getall() throws SQLException {
        List<Usuario> usuarios = UsuarioService.findAll();

        if(((List<Usuario>) usuarios).isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(usuarios).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Usuario usuario) throws SQLException {
        Mensagem mensagem = UsuarioService.create(usuario);
        if(!mensagem.isSucesso()){
            return Response.status(Response.Status.BAD_REQUEST).entity(mensagem).build();
        }
        return Response.ok(mensagem).build();
    }
}
