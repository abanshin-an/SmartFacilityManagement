package ru.a2n.sfm.person.jmh;

import org.apache.commons.lang3.RandomStringUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.a2n.sfm.person.service.HashService;

@SuppressWarnings({"java:S2245", "java:S112"})
@BenchmarkMode(value = {Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
@Fork(value = 1, warmups = 0)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 5)
public class HashBenchmark {

    @State(Scope.Benchmark)
    public static class ExecutionPlan {

        private final HashService hashService = new HashService(new BCryptPasswordEncoder());

        @Param({"MD5", "SHA-256", "SHA-512", "BCrypt"})
        public String algorithm;

        private String password;

        @Setup(Level.Invocation)
        public void generatePassword() {
            password = RandomStringUtils.insecure().next(20, "abcdefghijklmnopqrstuvwxyz0123456789");
        }
    }

    @Benchmark
    public void hashBenchmark(ExecutionPlan plan, Blackhole bh) {
        bh.consume(plan.hashService.hash(plan.algorithm, plan.password));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(HashBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
