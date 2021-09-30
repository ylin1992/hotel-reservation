package menu;

import api.AdminResource;
import api.HotelResource;
import exception.UndefinedActionException;

public class Menu {

    public static AdminResource adminResource = AdminResource.getInstance();
    public static HotelResource hotelResource = HotelResource.getInstance();
    protected int itemNum;

    public void checkTagValidity(int tag) throws UndefinedActionException {
        if (tag < 1 || tag > itemNum) {
            throw new UndefinedActionException("");
        }
    }

}
