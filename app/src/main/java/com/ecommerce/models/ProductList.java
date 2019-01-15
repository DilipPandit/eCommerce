package com.ecommerce.models;

import java.io.Serializable;

public class ProductList implements Serializable {
    private String id;

    private String price;

    private String category_name;

    private String description;

    private String name;

    private Sort_props sort_props;

    private String image;

    private String category_id;

    private String qty;
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sort_props getSort_props() {
        return sort_props;
    }

    public void setSort_props(Sort_props sort_props) {
        this.sort_props = sort_props;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    class Sort_props {
        private String A;

        private String B;

        private String C;

        public String getA() {
            return A;
        }

        public void setA(String a) {
            A = a;
        }

        public String getB() {
            return B;
        }

        public void setB(String b) {
            B = b;
        }

        public String getC() {
            return C;
        }

        public void setC(String c) {
            C = c;
        }
    }
}
