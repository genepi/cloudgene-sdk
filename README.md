# Cloudgene SDKs

We provide SDKs for different languages to write Steps in your favourite language. Such steps have much more capabilities to communicate with the workflow manager in order to transmit status updates and detailed error messages.

**Important: You need at least Cloudgene 2.0 in order to use the following SDKs**

Currently we support the following languages:

## Java SDK

First, you have to create a new java project and include our cloudgene-java-sdk library. We have a maven repository for that library:

```xml
<repositories>
	<repository>
		<id>genepi-maven-repository</id>
		<url>https://raw.github.com/genepi/maven-repository/mvn-repo/</url>
		<snapshots>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
		</snapshots>
	</repository>
</repositories>
```

The dependency is the following:

```xml
<dependencies>
	<dependency>
		<groupId>cloudgene</groupId>
		<artifactId>cloudgene-java-sdk</artifactId>
		<version>1.0.0</version>
	</dependency>
</dependencies>
```

Next, you have to extend `cloudgene.sdk.CloudgeneStep` and implement the run method :

```java
import cloudgene.sdk.CloudgeneStep;
import cloudgene.sdk.internal.WorkflowContext;

public class SayHelloStep extends CloudgeneStep {

	public boolean run(WorkflowContext context) {

		// parameters defined in yaml file
		String name = context.get("name");

		if (name == null || name.isEmpty()) {
			context.error("Please enter your name.");
			return false;
		}

		// some outputs
		context.ok("Hi " + name + ". This is as okey message");
		context.error("Hi " + name + ". This is as error message");
		context.warning("Hi " + name + ". This is as warning message");		

		// your application logic
		context.beginTask("Working....");
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			context.endTask("Work failed.", e);
			return false;
		}
		context.endTask("Work done.", WorkflowContext.OK);

		return true;

	}

}
```

After creating a jar archive for your project (for example by using maven), you can use the Step in your `cloudgene.yaml` file:

```yaml
name: Hello Java
version: 1.0
workflow:
  steps:
    - name: Say Hello
      jar: cloudgene-java-examples.jar
      classname: cloudgene.examples.SayHelloStep
  inputs:
    - id: name
      description: Name
      type: text
      value: Max Mustermann
```

A Eclipse project including a maven file and a cloudgene.yaml file can be found [here](https://github.com/genepi/cloudgene-examples/tree/master/cloudgene-java-examples).
