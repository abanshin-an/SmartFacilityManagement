package ru.a2n.sfm.person.jmh;

import org.apache.commons.lang3.RandomStringUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.a2n.sfm.person.PersonApplication;
import ru.a2n.sfm.person.dto.PersonCreateDto;
import ru.a2n.sfm.person.service.PersonService;

@SuppressWarnings("java:S2245")
@State(Scope.Benchmark)
public class RegisterPersonBenchmark {

    private PersonService personService;
    private ConfigurableApplicationContext context;

    @Setup(Level.Trial)
    public void setUp() {
        context = SpringApplication.run(PersonApplication.class);
        personService = context.getBean(PersonService.class);
    }

    @TearDown
    public void tearDown() {
        context.close();
    }

    @Benchmark
    @BenchmarkMode(value = {Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 5)
    @Measurement(iterations = 1, time = 5)
    public void createPerson(Blackhole blackhole) {
        blackhole.consume(personService.createPerson(new PersonCreateDto(
                "fullName",
                "login",
                "e@mail.ru",
                "+7987-654-3210",
                RandomStringUtils.insecure().next(20, "abcdefghijklmnopqrstuvwxyz0123456789"))));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(RegisterPersonBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
