package com.syamantakm.grinder;

import com.syamantakm.annotation.Resource;
import net.grinder.plugin.http.HTTPRequest;
import net.grinder.script.NotWrappableTypeException;
import net.grinder.script.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Syamantak Mukhopadhyay
 */
public abstract class AbstractTestRunner {
    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractTestRunner.class);

    @Resource
    protected HTTPRequest test;

    public void init() throws NotWrappableTypeException {
        LOGGER.info("AbstractTestRunner :: init");
        this.test = (HTTPRequest) new Test(100, "http test").wrap(test);
    }
}
