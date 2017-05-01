package ziphome.fxtmdiscount2;

/**
 * Created by 1 on 19.03.2017.
 */

public class NamObj {
    String name;
    String offer;
    String address;
    String wHours;
    String gps;

    public NamObj(String name2, String offer2, String address2, String wHours2, String gps2) {
        name = name2;
        offer = offer2;
        address = address2;
        wHours= wHours2;
        gps =gps2;
    }

    public String getName() {
        return name ;
    }

    public String getoffer() {
        return offer ;
    }
    public String getAddress() {
        return address;

    }

    public String getwHours() {
        return wHours ;
    }

    public  String getGps() {return gps; }

}
