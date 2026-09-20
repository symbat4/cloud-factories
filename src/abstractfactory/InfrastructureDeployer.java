package abstractfactory;

/**
 * CLIENT: knows only CloudProviderFactory, VirtualMachine and ObjectStorage.
 * It never writes "new AwsVirtualMachine()" or any other concrete class.
 */
public class InfrastructureDeployer {

    private final VirtualMachine virtualMachine;
    private final ObjectStorage objectStorage;

    public InfrastructureDeployer(CloudProviderFactory factory) {
        this.virtualMachine = factory.createVirtualMachine();
        this.objectStorage = factory.createObjectStorage();
    }

    public void deploy(String backupFileName) {
        virtualMachine.launch();
        objectStorage.store(backupFileName);
    }
}
