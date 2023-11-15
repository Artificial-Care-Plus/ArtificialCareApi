package br.com.fiap.artificialcareapi.resource;

import br.com.fiap.artificialcareapi.beans.Acoes;
import br.com.fiap.artificialcareapi.beans.Mensagem;
import br.com.fiap.artificialcareapi.bo.AcoesService;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.artificialcareapi.bo.UsuarioService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("acoes")
public class AcoesResource {
    /* Get all nao recebe parametro*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws SQLException {
        List<Acoes> acoesLista = AcoesService.findAll();

        if(((List<Acoes>) acoesLista).isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(acoesLista).build();
    }
    /* getById {id} */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) throws SQLException {
        Acoes acao = AcoesService.findById(id);
        if(acao == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Mensagem("ID não existe", false)).build();
        }

        return Response.ok(acao).build();
    }

    /* getbyIdUsuario {id} / {qtd} */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{emailCliente}/{qtd}")
    public Response getByIdUsuario(@PathParam("emailCliente") String emailCliente, @PathParam("qtd") int qtd) throws SQLException {
        if (qtd <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Mensagem("Quantidade deve ser maior que 0", false)).build();
        }
        var usuario = UsuarioService.find(emailCliente);
        if(emailCliente == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Mensagem("Email não existe", false)).build();
        }
        List<Acoes> acoesLista = AcoesService.findByEmailUsuario(emailCliente, qtd);
        if(acoesLista.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Mensagem("Não tem Açoes cadastradas", false)).build();
        }
        return Response.ok(acoesLista).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") Long id,Acoes acao) throws SQLException {
        var acaoAntiga = AcoesService.findById(id);
        if(acaoAntiga == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Mensagem("ID não existe", false)).build();
        }

        Mensagem mensagem = AcoesService.update(id,acao);

        return Response.ok(mensagem).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) throws SQLException {
        Acoes acaoAntiga = AcoesService.findById(id);
        if(acaoAntiga == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Mensagem("ID não existe", false)).build();
        }
        AcoesService.delete(id);
        return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Acoes acao) throws SQLException {
        var usuario = UsuarioService.find(acao.getUsuario().getEmail());
        if(usuario == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Mensagem("Usuario não cadastrado", false)).build();
        }
        AcoesService.create(acao);
        return Response.status(Response.Status.CREATED).entity(new Mensagem("Ação criada com sucesso", true)).build();
    }

}
