package abstractfactory;

/** CONCRETE PRODUCT - AWS family */
public class AwsVirtualMachine implements VirtualMachine {

    @Override
    public void launch() {
        System.out.println("AWS: EC2 instance launched");
    }
}
