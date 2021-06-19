package hcmute.edu.vn.mssv18110278.Entity;

public class DetailOrders {

    private int idorder;
    private int iditem;
    private int mount;
    private int totalprice;

    public DetailOrders(int idorder, int iditem, int mount, int totalprice) {
        this.idorder = idorder;
        this.iditem = iditem;
        this.mount = mount;
        this.totalprice = totalprice;
    }



    public int getIdorder() {
        return idorder;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }

    public int getIditem() {
        return iditem;
    }

    public void setIditem(int iditem) {
        this.iditem = iditem;
    }

    public int getMount() {
        return mount;
    }

    public void setMount(int mount) {
        this.mount = mount;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }
}

