package factorymethod;

/** PRODUCT: the common contract for every server the factory method can create. */
public interface CloudServer {

    void provision();

    void start();
}
