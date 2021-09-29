package menu;

import exception.UndefinedActionException;

public interface IMenu {
    public void executeAction(int tag);

    public void checkTagValidity(int tag) throws UndefinedActionException;
}
