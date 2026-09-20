package abstractfactory;

/** CONCRETE PRODUCT - Azure family */
public class AzureObjectStorage implements ObjectStorage {

    @Override
    public void store(String fileName) {
        System.out.println("Azure: '" + fileName + "' saved to Blob Storage");
    }
}
