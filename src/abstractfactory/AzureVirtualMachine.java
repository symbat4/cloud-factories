package abstractfactory;

/** CONCRETE PRODUCT - Azure family */
public class AzureVirtualMachine implements VirtualMachine {

    @Override
    public void launch() {
        System.out.println("Azure: Virtual Machine launched");
    }
}
