package ebloodbanking.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import ebloodbanking.choices.TestEnv;
import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * Env configuration once loaded, is to remain constant for all classes using it. Thus we will follow Singleton design pattern here.
 * For future reference on this topic: https://github.com/lightbend/config
 */
public class TestEnvFactory {
    /**
     * With this approach, we are relying on JVM to create the unique instance of EnvFactory when the class is loaded.
     * The JVM guarantees that the instance will be created before any thread accesses the static uniqueInstance variable.
     * This code is thus guaranteed to be thread safe.
     */
    private static TestEnvFactory uniqueInstance = new TestEnvFactory();
    private static Config config;

    private TestEnvFactory() {
    }

    public static TestEnvFactory getInstance() {
        return uniqueInstance;
    }

    public Config getConfig() {
        if (config == null) {
            config = loadConfig();
        } else {
            log.info("Config was already set. Reusing instance.");
        }

        return config;
    }

    private Config loadConfig() {
        log.info("Setting config first time.");
        // Below line loads default properties (first from System properties and then from application.conf file under main -> resources folder)
        Config applicationConfig = ConfigFactory.load();
        Config choicesConfig = ConfigFactory.load("choices");

        // Create a base config using application config and user choices
        Config baseConfig = applicationConfig.withFallback(choicesConfig);

        // Throw an exception if choice mentioned in choices.conf is not a valid env choice from TestEnv enum class.
        String testEnv = baseConfig.getString("TEST_ENV");
        TestEnv.parse(testEnv);

        /* Assumption is, if you specified TEST_ENV value (say local) in choices.conf, you have also created a valid
            file with its name ex: env.local.conf and secrets.local.conf files under main -> resources folder.
         */
        // Todo: Create unit tests to test this assumption and then remove this todo line.
        Config testEnvConfig = ConfigFactory.load(String.format("env.%s", testEnv));
        Config secretsConfig = ConfigFactory.load(String.format("secrets.%s", testEnv));

        return baseConfig.withFallback(testEnvConfig).withFallback(secretsConfig);
    }
}
