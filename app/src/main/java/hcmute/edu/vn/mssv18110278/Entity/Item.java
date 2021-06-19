package hcmute.edu.vn.mssv18110278.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
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

    protected Item(Parcel in) {
        id = in.readInt();
        idcate = in.readInt();
        name = in.readString();
        price = in.readInt();
        detail = in.readString();
        status = in.readInt();
        image = in.createByteArray();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(idcate);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeString(detail);
        dest.writeInt(status);
        dest.writeByteArray(image);
    }
}
