package br.com.fiap.artificialcareapi.resource;

import br.com.fiap.artificialcareapi.beans.Dicas;
import br.com.fiap.artificialcareapi.bo.DicasService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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

        if(((List<Dicas>) DicasLista).isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(DicasLista).build();
    }
}
