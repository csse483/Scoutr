package comcsse483.github.scoutr.models;

/**
 * Created by schmitml on 1/26/16.
 */
public class DataEntry<T> {
    private String mName;
    private T mData;
    private Class mType;

    public DataEntry(String name, T data) {
        this.mName = name;
        this.mData = data;
    }

    public DataEntry(String name, Class type) {
        this.mName = name;
        mType = type;
    }

    public String getName() {
        return mName;
    }

    public T getData() {
        return mData;
    }

    public Class getType(){
        return mType;
    }
}
