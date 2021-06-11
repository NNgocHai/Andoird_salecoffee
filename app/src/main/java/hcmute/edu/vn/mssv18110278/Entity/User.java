package hcmute.edu.vn.mssv18110278.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Abstract class for user
 *
 * @author Eric
 */
public class User implements Parcelable {

  private int id;
  private String username;
  private String gmail;
  private int role;
  private byte[] image;

  protected User(Parcel in) {
    id = in.readInt();
    username = in.readString();
    gmail = in.readString();
    role = in.readInt();
    image = in.createByteArray();
  }

  public static final Creator<User> CREATOR = new Creator<User>() {
    @Override
    public User createFromParcel(Parcel in) {
      return new User(in);
    }

    @Override
    public User[] newArray(int size) {
      return new User[size];
    }
  };

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public User(int id, String username, String gmail, int role, byte[] image) {
    this.id = id;
    this.username = username;
    this.gmail = gmail;
    this.role = role;
    this.image = image;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getGmail() {
    return gmail;
  }

  public void setGmail(String gmail) {
    this.gmail = gmail;
  }

  public int getRole() {
    return role;
  }

  public void setRole(int role) {
    this.role = role;
  }


  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(username);
    dest.writeString(gmail);
    dest.writeInt(role);
    dest.writeByteArray(image);
  }
}
