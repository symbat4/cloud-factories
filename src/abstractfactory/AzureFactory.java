package abstractfactory;

/** CONCRETE FACTORY #2 - builds the complete, consistent Azure family. */
public class AzureFactory implements CloudProviderFactory {

    @Override
    public VirtualMachine createVirtualMachine() {
        return new AzureVirtualMachine();
    }

    @Override
    public ObjectStorage createObjectStorage() {
        return new AzureObjectStorage();
    }
}
