package com.product.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "products")
//要保证数据表名称的一致性！
public class Products {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private int productid;
    private UUID id;

    private String cover;
    private String coverBase64; // 新增字段存储 Base64 编码的图片内容

    private String name;
    private double price;
    private Double priceSale;
    private String colors;
    private String status;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }


    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getCoverBase64() {
        return coverBase64;
    }

    public void setCoverBase64(String coverBase64) {
        this.coverBase64 = coverBase64;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(Double priceSale) {
        this.priceSale = priceSale;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}