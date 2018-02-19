package com.maxart.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "MyRequest", namespace="http://service.maxart.com")
public class MyRequest implements Serializable
{
    @XmlElement(name = "id", required = false)
    private int id;

    @XmlElement(name = "name", required = false)
    private String name;

    @XmlElement(name = "author", required = false)
    private String author;

    @XmlElement(name = "year", required = false)
    private int year;

    @XmlElement(name = "material", required = false)
    private String material;

    @XmlElement(name = "height", required = false)
    private int height;

    @XmlElement(name = "width", required = false)
    private int width;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}