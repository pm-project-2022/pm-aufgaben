package computerfactory;

public abstract class Drive {
    private int storageSize;
    private String description;

    public Drive(int storageSize, String description) {
        this.storageSize = storageSize;
        this.description = description;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public String getDescription() {
        return this.description;
    }
}
