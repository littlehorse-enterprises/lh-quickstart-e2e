# E2E Tests for LH

This guide shows you how to implement E2E test for LH.

## Running with Testcontainer

Run tests:

```shell
./gradlew clean test -Dbootstrapper.class=io.littlehorse.e2e.StandaloneBootstrapper
```

## Running with external LH

Run LH:

```shell
docker compose up -d
```

Run tests:

```shell
./gradlew clean test -Dbootstrapper.class=io.littlehorse.e2e.ExternalLittleHorseBootstrapper
```

## Test Structure

Add the LH dependencies `build.gradle` file:

```groovy
def lhVersion = '0.11.2'

dependencies {
    ...
    implementation "io.littlehorse:littlehorse-client:${lhVersion}"
    testImplementation "io.littlehorse:littlehorse-test-utils:${lhVersion}"
    testImplementation "io.littlehorse:littlehorse-test-utils-container:${lhVersion}"
    ...
}
```

WIP

## Configurations

### Using Environment Variables

You can define which test bootstrapper to use passing env variables.
This is useful when running the test inside a docker container or
a pipeline. Example:

```shell
BOOTSTRAPPER_CLASS="io.littlehorse.e2e.ExternalLittleHorseBootstrapper" ./gradlew clean test
```

### Using System Properties

Add a `bootstrapper.class` system property to `build.gradle` file:

```groovy
def bootstrapperClassProperty = 'bootstrapper.class'

test {
    useJUnitPlatform()
    systemProperty bootstrapperClassProperty, System.getProperty(bootstrapperClassProperty) ?: 'io.littlehorse.e2e.StandaloneBootstrapper'
}
```

Then you should pass with bootstrapper you want to use. Example:

```shell
./gradlew clean test -Dbootstrapper.class=io.littlehorse.e2e.ExternalLittleHorseBootstrapper
```

> Notice thw `-D`.

### Using a Properties File

LH test utils allows you set the default `bootstrapper.class`
using a property file. This is specially useful when running
the test form the IDE.

Create a `test/resources/test.properties` file with:

```properties
bootstrapper.class = io.littlehorse.e2e.StandaloneBootstrapper
```