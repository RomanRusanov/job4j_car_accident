package ru.job4j.accident;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 11.02.2021
 * email roman9628@gmail.com
 * The class .
 */
public class AppContext {

    private AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();

    private static final class Lazy {
        private static final AppContext INSTANCE = new AppContext();
    }

    private AppContext() {}

    public static AppContext getInstance() {
        return Lazy.INSTANCE;
    }

    public AnnotationConfigApplicationContext getAppContext() {
        return this.appContext;
    }

}