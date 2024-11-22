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

Add LH dependencies `build.gradle` file:

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

## Environment Variables

## Using Properties File

Add a `bootstrapper.class` system property to `build.gradle` file:

```groovy
def bootstrapperClassProperty = 'bootstrapper.class'

test {
    useJUnitPlatform()
    systemProperty bootstrapperClassProperty, System.getProperty(bootstrapperClassProperty) ?: 'io.littlehorse.e2e.StandaloneBootstrapper'
}
```