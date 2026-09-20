package abstractfactory;

/** ABSTRACT FACTORY: one creation method per product type in the family. */
public interface CloudProviderFactory {

    VirtualMachine createVirtualMachine();

    ObjectStorage createObjectStorage();
}
