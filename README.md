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

## Environment Variables

## Using Properties File