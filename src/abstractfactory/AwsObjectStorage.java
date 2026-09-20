package abstractfactory;

/** CONCRETE PRODUCT - AWS family */
public class AwsObjectStorage implements ObjectStorage {

    @Override
    public void store(String fileName) {
        System.out.println("AWS: '" + fileName + "' saved to S3 bucket");
    }
}
