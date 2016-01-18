package comcsse483.github.scoutr;

/**
 * Created by schmitml on 1/18/16.
 */
public class MenuItem {
    private String mTitle;
    private int mMenuNumber;

    public MenuItem(String mTitle, int mMenuNumber) {
        this.mMenuNumber = mMenuNumber;
        this.mTitle = mTitle;
    }

    public String getTitle(){
        return this.mTitle;
    }

    public int getMenuNumber(){
        return this.mMenuNumber;
    }
}
