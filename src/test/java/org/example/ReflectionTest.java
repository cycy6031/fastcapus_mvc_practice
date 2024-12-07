package org.example;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.*;
import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    void controllerScan(){
        Set<Class<?>> beans = getTypeAnnotatedWith(List.of(Controller.class, Service.class));

        logger.debug("beans: {}", beans);
    }

    @Test
    void showClass(){
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        logger.debug("User all declared fields: {}", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        logger.debug("User all declared constructors: {}", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        logger.debug("User all declared methods: {}", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }

    @Test
    void load() throws ClassNotFoundException {
        Class<User> clazz = User.class;

        User user = new User("serverwizard", "홍길동");
        Class<? extends User> clazz2 = user.getClass();

        Class<?> clazz3 = Class.forName("org.example.model.User");

        logger.debug("clazz: {}", clazz);
        logger.debug("clazz2: {}", clazz2);
        logger.debug("clazz3: {}", clazz3);

        assertThat(clazz == clazz2).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
        assertThat(clazz3 == clazz).isTrue();
    }

    private static Set<Class<?>> getTypeAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();

        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        /*beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));
        beans.addAll(reflections.getTypesAnnotatedWith(Service.class));*/
        return beans;
    }
}
