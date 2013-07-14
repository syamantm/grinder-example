package com.syamantakm.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Syamantak Mukhopadhyay
 */
public class PropertyHolder {

    public static final String JDBC_URL = "jdbc.url";
    public static final String JDBC_DRIVER_CLASS = "jdbc.driver.class";
    public static final String JDBC_USERNAME = "jdbc.username";
    public static final String JDBC_PASSWORD = "jdbc.password";
    public static final String TEST_RUN_NUMBER = "test.run.number";

    public static final String SQL_SELECT_LIMIT = "sql.select.limit";

    private Properties properties;

    public PropertyHolder() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try {
            properties.load(PropertyHolder.class.getResourceAsStream("/load-test.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String getJdbcUrl() {
        return properties.getProperty(JDBC_URL);
    }

    public String getJdbcDriverClass() {
        return properties.getProperty(JDBC_DRIVER_CLASS);
    }

    public String getJdbcUsername() {
        return properties.getProperty(JDBC_USERNAME);
    }

    public String getJdbcPassword() {
        return properties.getProperty(JDBC_PASSWORD);
    }

    public int getTestRunNumber() {
        return Integer.parseInt(properties.getProperty(TEST_RUN_NUMBER));
    }

    public int getSelectLimit() {
        return Integer.parseInt(properties.getProperty(SQL_SELECT_LIMIT));
    }
}
