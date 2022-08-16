package com.example.Service;
import com.example.model.Episode;
import com.example.model.TvSeries;
import com.example.proxy.EpisodeProxy;
import com.example.proxy.TvSeriesProxy;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/tvseries")
public class TvSeriesResource {

    @RestClient TvSeriesProxy proxy;
    @RestClient EpisodeProxy proxyResource;
    private List<TvSeries> tvSeriesList = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("title") String title) {
        TvSeries tvSeries = proxy.get(title);
        List<Episode> episode = proxyResource.get(tvSeries.getId());
        tvSeriesList.add(tvSeries);

        return Response.ok(episode).build();
    }

}