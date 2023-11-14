package br.com.fiap.artificialcareapi.resource;

import br.com.fiap.artificialcareapi.beans.Acoes;
import br.com.fiap.artificialcareapi.bo.AcoesService;

import java.sql.SQLException;
import java.util.List;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("acoes")
public class AcoesResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws SQLException {
        List<Acoes> acoesLista = AcoesService.findAll();

        if(((List<Acoes>) acoesLista).isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(acoesLista).build();
    }

}
