package com.musicshop.context;

import com.musicshop.config.ApplicationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApplicationContextTest {

    @Test
    public void testSingletonInstance() {
        ApplicationContext instance1 = ApplicationContext.getInstance();
        ApplicationContext instance2 = ApplicationContext.getInstance();

        Assertions.assertSame(instance1, instance2, "Instances of ApplicationContext should be the same");
    }
}
