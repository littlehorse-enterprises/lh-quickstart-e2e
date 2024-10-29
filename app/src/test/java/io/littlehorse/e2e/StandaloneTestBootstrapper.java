package io.littlehorse.e2e;

import io.littlehorse.sdk.common.config.LHConfig;
import io.littlehorse.test.internal.TestBootstrapper;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.concurrent.TimeUnit;

public class StandaloneTestBootstrapper implements TestBootstrapper {


    public StandaloneTestBootstrapper() {
        GenericContainer littlehorse = new GenericContainer(DockerImageName.parse("ghcr.io/littlehorse-enterprises/littlehorse/lh-standalone:0.11.2"))
                .withExposedPorts(2023);
        littlehorse.start();

        // TODO: look for an elegant way to wait for the service using testcontainers
        try {
            Thread.sleep(TimeUnit.MINUTES.toMillis(1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LHConfig getWorkerConfig() {
        return new LHConfig();
    }
}
