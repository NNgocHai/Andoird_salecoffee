package hcmute.edu.vn.mssv18110278.Entity;

public class Item {
    private int id;
    private int idcate;
    private String name;
    private int price;
    private String detail;
    private int status;
    private byte[] image;

    public Item(int id, int idcate, String name, int price, String detail, int status, byte[] image) {
        this.id = id;
        this.idcate = idcate;
        this.name = name;
        this.price = price;
        this.detail = detail;
        this.status = status;
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



}
