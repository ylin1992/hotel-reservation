package model;

import java.util.Date;

public interface IRoom {
    public String getRoomNumber();

    public Double getRoomPrice();

    public RoomType getRoomType();

    public boolean isFree();

    public Date[] getAvailableDates();

    public void setAvailableDates(Date[] dates);
}
