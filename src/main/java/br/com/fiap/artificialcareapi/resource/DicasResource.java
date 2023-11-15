package br.com.fiap.artificialcareapi.resource;

import br.com.fiap.artificialcareapi.beans.Dicas;
import br.com.fiap.artificialcareapi.beans.Mensagem;
import br.com.fiap.artificialcareapi.bo.DicasService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("dicas")
public class DicasResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws SQLException {
        List<Dicas> DicasLista = DicasService.findAll();

        if (((List<Dicas>) DicasLista).isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(DicasLista).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getOne(@PathParam("id") Long id) throws SQLException {
        Dicas Dicas = DicasService.find(id);

        if (Dicas == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Mensagem("ID não existe", false)).build();
        }

        return Response.ok(Dicas).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Dicas dicas) throws SQLException {
        Mensagem mensagem = DicasService.create(dicas);

        return Response.status(Response.Status.CREATED).entity(mensagem).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Dicas dicas) throws SQLException {
        var Dicas = DicasService.find(id);
        if(Dicas == null){
            return Response.status(Response.Status.NOT_FOUND).entity(new Mensagem("ID não existe", false)).build();
        }

        Mensagem mensagem = DicasService.update(id, dicas);


        return Response.ok(mensagem).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) throws SQLException {
        var Dicas = DicasService.find(id);
        if(Dicas == null){
            return Response.status(Response.Status.NOT_FOUND).entity(new Mensagem("ID não existe", false)).build();
        }
        Mensagem mensagem = DicasService.delete(id);

        return Response.noContent().entity(mensagem).build();
    }
}
