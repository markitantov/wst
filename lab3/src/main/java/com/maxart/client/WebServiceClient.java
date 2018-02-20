package com.maxart.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {

    private static void getStatus(PictureWebService pictureWebService) {
        System.out.println("Pictures Status");
        List<Picture> pictures = pictureWebService.getAllPictures();
        for (Picture picture : pictures) {
            System.out.println(picture.toString());
        }

        System.out.println("Total pictures: " + pictures.size());
        System.out.println();
    }

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/PictureService?wsdl");
        PictureService pictureService = new PictureService(url);
        PictureWebService pictureWebService = pictureService.getPictureWebServicePort();

        System.out.println("Simple hard code client for service");
        getStatus(pictureWebService);

        System.out.println("Query: createPicture?name=Богатыри&author=Виктор Михайлович Васнецов");
        int creatingPictureId = 0;
        try {
            creatingPictureId = pictureWebService.createPicture(
                    "Богатыри",
                    "Виктор Михайлович Васнецов",
                    0,
                    "",
                    0,
                    0);
        } catch (InsertingException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidCreatingParametersException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }

        System.out.println("Response: " + creatingPictureId);
        System.out.println();

        System.out.println("Query: createPicture?name=Богатыри&author=Виктор Михайлович Васнецов&" +
                "year=1881&material=Маслянные краски&height=295.3&width=446");
        int id = 0;
        try {
            id = pictureWebService.createPicture(
                    "Богатыри",
                    "Виктор Михайлович Васнецов",
                    1881,
                    "Маслянные краски",
                    295.3f,
                    446);
        } catch (InsertingException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidCreatingParametersException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }

        System.out.println("Response: " + id);
        System.out.println();

        getStatus(pictureWebService);

        System.out.println("Query: updatePicture?id=10");
        MyRequest myRequest = new MyRequest();
        try {
            creatingPictureId = pictureWebService.updatePicture(11, myRequest);
        } catch (IllegalIdException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (IllegalQException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidEntityException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }

        System.out.println("Response: " + creatingPictureId);
        System.out.println();

        System.out.println("Query: updatePicture?id=111&name=My own picture&author=ITMO&year=2018");
        myRequest.init();
        myRequest.setName("My own picture");
        myRequest.setAuthor("ITMO");
        myRequest.setYear(2018);

        try {
            creatingPictureId = pictureWebService.updatePicture(111, myRequest);
        } catch (IllegalIdException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (IllegalQException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidEntityException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }

        System.out.println("Response: " + creatingPictureId);
        System.out.println();

        System.out.println("Query: deletePicture?id=111");
        try {
            creatingPictureId = pictureWebService.deletePicture(111);
        } catch (IllegalIdException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidEntityException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }

        System.out.println("Response: " + creatingPictureId);
        System.out.println();

        System.out.println("Query: deletePicture?id=" + id);
        try {
            creatingPictureId = pictureWebService.deletePicture(id);
        } catch (IllegalIdException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidEntityException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }

        System.out.println("Response: " + creatingPictureId);
        System.out.println();

        getStatus(pictureWebService);
    }
}