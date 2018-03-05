package com.maxart.service;

import com.maxart.service.exceptions.*;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/pictures")
@Produces({MediaType.APPLICATION_JSON})
public class PictureResource {

    @GET
    public List<Picture> find(@Context UriInfo info) {
        String id = info.getQueryParameters().getFirst("id");
        String name = info.getQueryParameters().getFirst("name");
        String author = info.getQueryParameters().getFirst("author");
        String year = info.getQueryParameters().getFirst("year");
        String material = info.getQueryParameters().getFirst("material");
        String height = info.getQueryParameters().getFirst("height");
        String width = info.getQueryParameters().getFirst("width");
        return new PostgreSQLDAO().findPictures(id, name, author, year, material, height, width);
    }

    @GET
    @Path("/{id}")
    public List<Picture> getOne(@PathParam("id") int id) throws IllegalIdException {
        if (id <= 0) {
            throw IllegalIdException.DEFAULT_INSTANCE;
        }

        return new PostgreSQLDAO().findOne(id);
    }

    @POST @Consumes("application/json")
    public String create(Picture picture) throws InvalidCreatingParametersException, InsertingException {
        if (picture.getName() == null || picture.getName().trim().isEmpty()) {
            throw new InvalidCreatingParametersException("Invalid creating parameter: name");
        }

        if (picture.getAuthor() == null || picture.getAuthor().trim().isEmpty()) {
            throw new InvalidCreatingParametersException("Invalid creating parameter: author");
        }

        if (picture.getYear() <= 0) {
            throw new InvalidCreatingParametersException("Invalid creating parameter: year");
        }

        if (picture.getMaterial() == null || picture.getMaterial().trim().isEmpty()) {
            throw new InvalidCreatingParametersException("Invalid creating parameter: material");
        }

        if (picture.getHeight() <= 0) {
            throw new InvalidCreatingParametersException("Invalid creating parameter: height");
        }

        if (picture.getWidth() <= 0) {
            throw new InvalidCreatingParametersException("Invalid creating parameter: width");
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        return "{\"result\":" + dao.createPicture(picture) + "}";
    }

    @PUT @Consumes("application/json")
    @Path("/{id}")
    public String update(@PathParam("id") int id, Picture picture) throws IllegalIdException, InvalidEntityException,
            InvalidUpdatingParametersException {
        if (id <= 0) {
            throw IllegalIdException.DEFAULT_INSTANCE;
        }

        if ((picture.getName() == null || picture.getName().trim().isEmpty()) &&
                (picture.getAuthor() == null || picture.getAuthor().trim().isEmpty()) &&
                (picture.getYear() <= 0) &&
                (picture.getMaterial() == null || picture.getMaterial().trim().isEmpty()) &&
                (picture.getHeight() <= 0) &&
                (picture.getWidth() <= 0)) {
            throw InvalidUpdatingParametersException.DEFAULT_INSTANCE;
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        return "{\"result\":" + dao.updatePicture(id, picture) + "}";
    }

    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") int id) throws IllegalIdException, InvalidEntityException {
        if (id <= 0) {
            throw IllegalIdException.DEFAULT_INSTANCE;
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        return "{\"result\":" + dao.deletePicture(id) + "}";
    }
}