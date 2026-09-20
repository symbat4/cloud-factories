# Cloud Server Factories — Factory Method & Abstract Factory (Java)

Software Design Patterns · Assignment #2 · Domain: **Cloud infrastructure**

## How to run

```bash
mkdir out
javac -d out $(find src -name "*.java")
java -cp out Main
```

## Part A — Factory Method (`src/factorymethod`)

| Component | Class(es) |
| --- | --- |
| Product | `CloudServer` (interface) |
| Concrete Products | `WebServer`, `DatabaseServer` |
| Creator | `ServerProvisioner` (abstract, declares `createServer()`) |
| Concrete Creators | `WebServerProvisioner`, `DatabaseServerProvisioner` |

`ServerProvisioner.deploy()` calls the factory method `createServer()`, then provisions and starts the result.
It never knows which concrete server it received.

## Part B — Abstract Factory (`src/abstractfactory`)

| Component | Class(es) |
| --- | --- |
| Abstract Products | `VirtualMachine`, `ObjectStorage` |
| Concrete Products | AWS family: `AwsVirtualMachine`, `AwsObjectStorage` · Azure family: `AzureVirtualMachine`, `AzureObjectStorage` |
| Abstract Factory | `CloudProviderFactory` (`createVirtualMachine()`, `createObjectStorage()`) |
| Concrete Factories | `AwsFactory`, `AzureFactory` |
| Client | `InfrastructureDeployer` — receives a `CloudProviderFactory`, uses only interfaces, never calls `new` on a concrete product |

Because a factory produces the whole family, an AWS VM can never be paired with Azure storage.

## Clean Code principles applied

### 1. Meaningful, intention-revealing names
**Before**
```java
class Srv { Srv(int a, int b) { ... } }
Srv s = new Srv(8, 32);
s.go();
```
**After** (`ServerSpecs`, `ServerProvisioner.deploy()`)
```java
ServerSpecs specs = new ServerSpecs(cpuCores, ramGb);
CloudServer server = provisioner.deploy();
```
Every class, method and parameter says what it is; no comment is needed to decode it.

### 2. Small methods, each doing one thing
**Before** — one method that builds, provisions, starts and prints everything.

**After** (`ServerProvisioner`)
```java
public CloudServer deploy() {
    CloudServer server = createServer();
    server.provision();
    server.start();
    return server;
}
```
Four lines, one job: deploy. Creating the server is delegated to `createServer()`.

### 3. Validated construction
**Before**
```java
this.cpuCores = cpuCores;   // accepts 0, -5, anything
```
**After** (`ServerSpecs`)
```java
requireAtLeast("cpuCores", cpuCores, MIN_CPU_CORES);
requireAtLeast("ramGb", ramGb, MIN_RAM_GB);

private static void requireAtLeast(String fieldName, int value, int minimum) {
    if (value < minimum) {
        throw new IllegalArgumentException(
                fieldName + " must be at least " + minimum + " but was " + value);
    }
}
```
An invalid server cannot exist. The exception message names the field, the rule and the bad value.
Demonstrated in `Main.runValidationDemo()`.

### 4. No magic numbers / strings
**Before**
```java
return new WebServer(new ServerSpecs(2, 4));
System.out.println("listening on port 443");
```
**After** (`WebServerProvisioner`, `WebServer`)
```java
private static final int WEB_CPU_CORES = 2;
private static final int WEB_RAM_GB = 4;
private static final int HTTPS_PORT = 443;

return new WebServer(new ServerSpecs(WEB_CPU_CORES, WEB_RAM_GB));
```
Each value has a name that explains it and lives in exactly one place.

### 5. Small, focused classes (Single Responsibility) with consistent formatting
**Before** — one `CloudManager` class that creates servers, stores files and knows every provider.

**After** — each class has one reason to change:
- `ServerSpecs` only holds and validates hardware values.
- `WebServer` / `DatabaseServer` only describe how that server type behaves.
- `WebServerProvisioner` only decides *which* server to create.
- `AwsFactory` only knows how to build the AWS family.

All files use the same 4-space indentation, one public type per file, and the same member order
(constants → fields → constructor → public methods → private helpers).

## Design justification (for the defense)

- **Factory Method** creates *one* product and uses inheritance: a subclass overrides `createServer()`.
  Adding a `ComputeServer` means adding two classes and changing none of the existing ones (Open/Closed).
- **Abstract Factory** creates a *family* of related products and uses composition: the client is handed a factory object.
  Adding GCP means adding a factory plus its products; `InfrastructureDeployer` is untouched.
- Abstract Factory is often built from several factory methods — each `createXxx()` method in `AwsFactory` is itself a factory method.
