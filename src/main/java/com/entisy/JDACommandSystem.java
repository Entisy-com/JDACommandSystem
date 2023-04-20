package com.entisy;

import com.entisy.util.Config;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Getter
public class JDACommandSystem {
    private Config config;
    private static JDACommandSystem instance;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JDACommandSystem() {}

    public void setup() throws FileNotFoundException {
        logger.info("Setting up JDACommandSystem");
        config = new Yaml(new Constructor(Config.class, new LoaderOptions())).load(new FileInputStream("./command-system.yml"));
    }

    public static JDACommandSystem get() {
        if (JDACommandSystem.instance == null)
            JDACommandSystem.instance = new JDACommandSystem();
        return JDACommandSystem.instance;
    }
}
