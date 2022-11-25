package org.demo.resources;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.demo.domain.Url;
import org.demo.domain.dto.UrlDto;
import org.demo.repository.UrlRepository;
import org.demo.util.URLShortener;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/urls")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    URLShortener us;

    @Inject
    UrlRepository urlRepository;

    @GET
    @Path("/{shortForm}")
    public Response find(@PathParam("shortForm") String shortForm) {
        Optional<Url> u = Url.findByShortForm(shortForm);
        if (u.isPresent()) {
            UrlDto ud = new UrlDto();
            ud.setLongForm(u.get().getLongForm());
            ud.setShortForm(u.get().getShortForm());

            return Response
                    .status(Response.Status.OK)
                    .entity(ud)
                    .build();
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @GET
    public Response findAll() {
        PanacheQuery<Url> uu = Url.<Url>findAll();
        List<UrlDto> ud = new ArrayList<>();
        ud = uu.stream().map(u -> new UrlDto(u)).collect(Collectors.toList());
        return Response
                .status(Response.Status.OK)
                .entity(ud)
                .build();
    }

    @Transactional
    @POST
    public Response create(UrlDto urlDto) {
        String shortenUrl = us.shortenURL(urlDto.getLongForm());
        String shortForm = shortenUrl.substring(shortenUrl.lastIndexOf("/") + 1);

        urlDto.setShortForm(shortenUrl);

        Url url = new Url();
        url.setLongForm(urlDto.getLongForm());
        url.setShortForm(shortForm);
        url.persistAndFlush();
        urlRepository.findByLongForm(urlDto.getLongForm());
        return Response.ok(urlDto).build();
    }

    @DELETE
    @Path("/{shortForm}")
    public Response delete(@PathParam("shortForm") String shortForm) {
        return Url.findByShortForm(shortForm)
                .map(u -> {
                    u.delete();
                    return Response.ok();
                })
                .orElseGet(() -> Response.status(NOT_FOUND))
                .build();
    }
}
