package abstractfactory;

/** CONCRETE FACTORY #1 - builds the complete, consistent AWS family. */
public class AwsFactory implements CloudProviderFactory {

    @Override
    public VirtualMachine createVirtualMachine() {
        return new AwsVirtualMachine();
    }

    @Override
    public ObjectStorage createObjectStorage() {
        return new AwsObjectStorage();
    }
}
