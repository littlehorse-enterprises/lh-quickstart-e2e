package io.littlehorse.e2e;

import io.littlehorse.BasicExample;
import io.littlehorse.MyWorker;
import io.littlehorse.sdk.common.proto.LHStatus;
import io.littlehorse.sdk.common.util.Arg;
import io.littlehorse.sdk.wfsdk.Workflow;
import io.littlehorse.sdk.worker.LHTaskMethod;
import io.littlehorse.test.LHTest;
import io.littlehorse.test.LHWorkflow;
import io.littlehorse.test.WorkflowVerifier;
import org.junit.jupiter.api.Test;

import static io.littlehorse.BasicExample.EXAMPLE_BASIC_WF;
import static io.littlehorse.BasicExample.GREET_TASK;
import static io.littlehorse.BasicExample.INPUT_NAME_VARIABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@LHTest
public class BasicTest {

    @LHWorkflow(EXAMPLE_BASIC_WF)
    private Workflow basicWf;
    private WorkflowVerifier verifier;
    private MyWorker worker = new MyWorker();

    @Test
    public void shouldSayHello() {
        verifier.prepareRun(basicWf, Arg.of(INPUT_NAME_VARIABLE, "Anakin Skywalker"))
                .waitForStatus(LHStatus.COMPLETED)
                .thenVerifyTaskRunResult(0, 1, variableValue -> assertEquals(variableValue.getStr(), "Hello there! Anakin Skywalker"))
                .start();
    }

    @LHWorkflow(EXAMPLE_BASIC_WF)
    public Workflow registerWf() {
        return BasicExample.getWorkflow();
    }

    @LHTaskMethod(GREET_TASK)
    public String greeting(String name) {
        return worker.greeting(name);
    }
}
