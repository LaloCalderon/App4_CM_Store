package com.example.ejercicio3cm.modelo;

public class producto2 {
    String name, desc, imag_url;

    public producto2(String name, String desc, String imag_url) {
        this.name = name;
        this.desc = desc;
        this.imag_url = imag_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImag_url() {
        return imag_url;
    }

    public void setImag_url(String imag_url) {
        this.imag_url = imag_url;
    }
}
