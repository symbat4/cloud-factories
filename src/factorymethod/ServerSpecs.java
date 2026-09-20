package factorymethod;

/**
 * Immutable hardware specification of a server.
 * Clean Code: validated construction + no magic numbers.
 */
public final class ServerSpecs {

    public static final int MIN_CPU_CORES = 1;
    public static final int MIN_RAM_GB = 1;

    private final int cpuCores;
    private final int ramGb;

    public ServerSpecs(int cpuCores, int ramGb) {
        requireAtLeast("cpuCores", cpuCores, MIN_CPU_CORES);
        requireAtLeast("ramGb", ramGb, MIN_RAM_GB);
        this.cpuCores = cpuCores;
        this.ramGb = ramGb;
    }

    private static void requireAtLeast(String fieldName, int value, int minimum) {
        if (value < minimum) {
            throw new IllegalArgumentException(
                    fieldName + " must be at least " + minimum + " but was " + value);
        }
    }

    @Override
    public String toString() {
        return cpuCores + " CPU, " + ramGb + " GB RAM";
    }
}
