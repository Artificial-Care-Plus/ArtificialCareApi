package br.com.fiap.artificialcareapi.resource;

import br.com.fiap.artificialcareapi.beans.Acoes;
import br.com.fiap.artificialcareapi.beans.Mensagem;
import br.com.fiap.artificialcareapi.beans.Usuario;
import br.com.fiap.artificialcareapi.bo.AcoesService;
import br.com.fiap.artificialcareapi.bo.UsuarioService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("usuario")
public class UsuarioResource {

    @GET
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("email") String email) throws SQLException {
        Usuario usuario = UsuarioService.find(email);

        if(usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(usuario).build();
    }

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

    @DELETE
    @Path("{email}")
    /* No banco de dados o email é único então por segurança no site passamos o email como parametro */
    public Response delete(@PathParam("email") String email) throws SQLException {
        Mensagem mensagem = UsuarioService.delete(email);
        if(!mensagem.isSucesso()){
            return Response.status(Response.Status.BAD_REQUEST).entity(mensagem).build();
        }
        return Response.ok(mensagem).build();
    }

    @PUT
    @Path("{email}")
    /* No banco de dados o email é único então por segurança no site passamos o email como parametro */
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("email") String email, Usuario usuario) throws SQLException {
        Mensagem mensagem = UsuarioService.update(email, usuario);
        if(!mensagem.isSucesso()){
            return Response.status(Response.Status.BAD_REQUEST).entity(mensagem).build();
        }
        return Response.ok(mensagem).build();
    }
}
