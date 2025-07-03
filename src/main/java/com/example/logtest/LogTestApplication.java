package com.example.logtest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@SpringBootApplication
public class LogTestApplication implements CommandLineRunner {

    private static final Logger LOGGER = Logger.getLogger(LogTestApplication.class.getName());
    private static final String[] KEYWORDS = {"error", "exception", "shutdown", "failure", "warn", "timeout"};

    public static void main(String[] args) {
        SpringApplication.run(LogTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Service started. Beginning to stream logs...");

        Random random = new Random();

        // Simulate streaming logs for 2 minutes
        for (int i = 1; i <= 60; i++) {
            // Randomly pick a keyword to simulate
            String keyword = KEYWORDS[random.nextInt(KEYWORDS.length)];
            String logMessage = String.format("Simulated log message [%02d]: Potential %s occurred.", i, keyword);

            // Alternate log levels
            if ("error".equalsIgnoreCase(keyword) || "exception".equalsIgnoreCase(keyword)) {
                LOGGER.severe(logMessage);
            } else if ("warn".equalsIgnoreCase(keyword)) {
                LOGGER.warning(logMessage);
            } else {
                LOGGER.info(logMessage);
            }

            TimeUnit.SECONDS.sleep(2); // Sleep to simulate real-time logs
        }

        LOGGER.info("Log streaming simulation finished.");
    }
}
