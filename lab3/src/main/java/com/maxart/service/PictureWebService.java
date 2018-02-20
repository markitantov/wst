package com.maxart.service;

import com.maxart.service.exceptions.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "PictureService")
public class PictureWebService {

    @WebMethod(operationName = "getAllPictures")
    public List<Picture> getAllPictures() {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.getAllPictures();
    }

    @WebMethod(operationName = "findPictures")
    public List<Picture> findPictures(@WebParam(name = "q") MyRequest request) throws IllegalQException {
        if (request == null || request.isNull()) {
            PictureServiceFault fault = PictureServiceFault.defaultInstance();
            throw new IllegalQException("Parameter q cannot be null or empty", fault);
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.findPictures(request);
    }

    @WebMethod(operationName = "createPicture")
    public int createPicture(@WebParam(name = "name") String name,
                             @WebParam(name = "author") String author,
                             @WebParam(name = "year") int year,
                             @WebParam(name = "material") String material,
                             @WebParam(name = "height") float height,
                             @WebParam(name = "width") float width) throws InsertingException,
            InvalidCreatingParametersException {

        PictureServiceFault fault = PictureServiceFault.defaultInstance();
        fault.setMessage("Invalid creating parameter");

        if (name == null || name.trim().isEmpty()) {
            fault.setMessage("Parameter name cannot be null or empty");
            throw new InvalidCreatingParametersException("Invalid creating parameter", fault);
        }

        if (author == null || author.trim().isEmpty()) {
            fault.setMessage("Parameter author cannot be null or empty");
            throw new InvalidCreatingParametersException("Invalid creating parameter", fault);
        }

        if (year <= 0) {
            fault.setMessage("Parameter year cannot be null or empty and less or equal to 0");
            throw new InvalidCreatingParametersException("Invalid creating parameter", fault);
        }

        if (material == null || material.trim().isEmpty()) {
            fault.setMessage("Parameter material cannot be null or empty");
            throw new InvalidCreatingParametersException("Invalid creating parameter", fault);
        }

        if (height <= 0) {
            fault.setMessage("Parameter height cannot be null or empty and less or equal to 0");
            throw new InvalidCreatingParametersException("Invalid creating parameter", fault);
        }

        if (width <= 0) {
            fault.setMessage("Parameter width cannot be null or empty and less or equal to 0");
            throw new InvalidCreatingParametersException("Invalid creating parameter", fault);
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.createPicture(name, author, year, material, height, width);
    }

    @WebMethod(operationName = "updatePicture")
    public int updatePicture(@WebParam(name = "id") int id,
                             @WebParam(name = "q") MyRequest request) throws IllegalQException, IllegalIdException, InvalidEntityException {
        if (id == 0) {
            PictureServiceFault fault = PictureServiceFault.defaultInstance();
            fault.setMessage("Parameter id cannot be null or empty");
            throw new IllegalIdException("Parameter id cannot be null or empty", fault);
        }

        if (request == null || request.isNull()) {
            PictureServiceFault fault = PictureServiceFault.defaultInstance();
            throw new IllegalQException("Parameter q cannot be null or empty", fault);
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.updatePicture(id, request);
    }

    @WebMethod(operationName = "deletePicture")
    public int deletePicture(@WebParam(name = "id") int id) throws InvalidEntityException, IllegalIdException {
        if (id == 0) {
            PictureServiceFault fault = PictureServiceFault.defaultInstance();
            fault.setMessage("Parameter id cannot be null or empty");
            throw new IllegalIdException("Parameter id cannot be null or empty", fault);
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.deletePicture(id);
    }
}
