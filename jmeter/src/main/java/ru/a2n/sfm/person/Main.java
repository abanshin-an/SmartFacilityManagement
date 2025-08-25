package ru.a2n.sfm.person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.Header;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.protocol.http.gui.HeaderPanel;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.ListedHashTree;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"java:S112", "java:S125", "java:S2245"})
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        var jmeterHome = System.getenv("JMETER_HOME");

        if (jmeterHome == null) {
            throw new RuntimeException("JMETER_HOME environment variable is not set.");
        }

        JMeterUtils.loadJMeterProperties(Strings.concat(jmeterHome, "/bin/jmeter.properties"));
        JMeterUtils.setJMeterHome(jmeterHome);
        logger.info("JMETER_HOME: {}", jmeterHome);
        JMeterUtils.initLocale();

        var jmeter = new StandardJMeterEngine();

        var threadGroup = getThreadGroup();
        var testPlan = getTestPlan(threadGroup);

        var testPlanTree = new ListedHashTree();
        var threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);
        var sampler = getHttpSamplerProxy();
        var headerManager = getHeaderManager();
        threadGroupHashTree.add(sampler, headerManager);

        SaveService.saveTree(testPlanTree, Files.newOutputStream(Paths.get("./jmeter/script.jmx")));

        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (!summariserName.isEmpty()) {
            summer = new Summariser(summariserName);
        }
        var logger = new ResultCollector(summer);
        logger.setFilename("./jmeter/pt-logs.jtl");
        testPlanTree.add(testPlanTree.getArray()[0], logger);

        jmeter.configure(testPlanTree);
        jmeter.run();
    }

    private static HeaderManager getHeaderManager() {
        var headerManager = new HeaderManager();
        headerManager.add(new Header("Content-Type", "application/json"));
        headerManager.setName("HTTP Header Manager");
        headerManager.setProperty(TestElement.TEST_CLASS, HeaderManager.class.getName());
        headerManager.setProperty(TestElement.GUI_CLASS, HeaderPanel.class.getName());
        return headerManager;
    }

    private static HTTPSamplerProxy getHttpSamplerProxy() {
        var httpSampler = new HTTPSamplerProxy();
        httpSampler.setDomain("localhost");
        httpSampler.setPort(8081);
        httpSampler.setPath("/api/person");
        httpSampler.setMethod("POST");
        httpSampler.setFollowRedirects(true);
        httpSampler.setUseKeepAlive(true);

        httpSampler.addNonEncodedArgument(
                "",
                "{\"fullName\":\"full name\", "
                        + " \"login\":\"${__RandomString(20,abcdefghijklmnopqrstuvwxyz0123456789)}\","
                        + " \"email\":\"email@mail.ru\", "
                        + " \"phone\":\"+70123456789\", \"password\":\"${__RandomString(15)}\"}",
                "");
        httpSampler.setPostBodyRaw(true);
        //        httpSampler.addNonEncodedArgument("",
        // """
        // {"fullName":"${__RandomString(7,abcdefghijklmnopqrstuvwxyz)} ${__RandomString(15,abcdefghijklmnopqrstuvwxyz)}
        // ${__RandomString(5,abcdefghijklmnopqrstuvwxyz)}",
        // "login": "${__RandomString(20,abcdefghijklmnopqrstuvwxyz0123456789)}",
        // "email":"${__RandomString(5, abcdefghijklmnopqrstuvwxyz)}@mail.ru",
        // "phone":"+7${__RandomString(10, 0123456789)}",
        // "password":"${__RandomString(25, abcdefghijklmnopqrstuvwxyz0123456789)}"}
        // """,
        //                "");
        //        httpSampler.setPostBodyRaw(true);

        httpSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
        httpSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());

        httpSampler.setName("HTTP Request");
        return httpSampler;
    }

    private static ThreadGroup getThreadGroup() {

        var loopController = new LoopController();
        loopController.setLoops(10000);
        loopController.setFirst(true);
        loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
        loopController.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
        loopController.initialize();

        var threadGroup = new ThreadGroup();
        threadGroup.setName("createPerson");
        threadGroup.setNumThreads(10);
        threadGroup.setRampUp(1);

        threadGroup.setSamplerController(loopController);
        threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
        threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());
        return threadGroup;
    }

    private static TestPlan getTestPlan(ThreadGroup threadGroup) {
        var testPlan = new TestPlan("Test Plan");
        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
        testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
        testPlan.addThreadGroup(threadGroup);
        return testPlan;
    }
}
