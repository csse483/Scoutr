package comcsse483.github.scoutr.models;

/**
 * A data entry model used to record data in the RecordDataFragment recycler view.
 */
public class DataEntry {
    private String mName;
    private Object mData;
    private Class mType;


    public DataEntry(String name, Class type) {
        this.mName = name;
        mType = type;
        if (type.equals(Integer.class)) {
            mData = 0;
        } else if (type.equals(Boolean.class)) {
            mData = false;
        }
    }

    public String getName() {
        return mName;
    }

    public Object getData() {
        if (mType.equals(Integer.class)) {
            return (int) mData;
        } else if (mType.equals(Boolean.class)) {
            return (boolean) mData;
        }
        return null;
    }

    public Class getType() {
        return mType;
    }

    public void setData(int num) {
        mData = num;
    }

    public void setData(boolean bool) {
        mData = bool;
    }


    @Override
    public String toString() {
        return mName + ": " + mData;
    }
}
