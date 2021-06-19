package hcmute.edu.vn.mssv18110278.Entity;

public class Order {
    public Order(int ID, int USERID, int TOTALPRICE, int STATUS, String DATE, String ADDRESS, String PHONE) {
        this.ID = ID;
        this.USERID = USERID;
        this.TOTALPRICE = TOTALPRICE;
        this.STATUS = STATUS;
        this.DATE = DATE;
        this.ADDRESS = ADDRESS;
        this.PHONE = PHONE;
    }

    private int ID;
    private int USERID;
    private int TOTALPRICE;
    private int STATUS;
    private String DATE;
    private String ADDRESS;
    private String PHONE;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUSERID() {
        return USERID;
    }

    public void setUSERID(int USERID) {
        this.USERID = USERID;
    }

    public int getTOTALPRICE() {
        return TOTALPRICE;
    }

    public void setTOTALPRICE(int TOTALPRICE) {
        this.TOTALPRICE = TOTALPRICE;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }
}
