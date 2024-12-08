package org.example.mvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.eclipse.jdt.internal.compiler.lookup.AnnotationHolder;
import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.RequestMapping;
import org.reflections.Reflections;

public class AnnotationHandlerMapping implements HandlerMapping{

    private final Object[] basePackage;

    private Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePackage){
        this.basePackage = basePackage;
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        System.out.println("00000000000");
        return handlers.get(handlerKey);
    }

    public void initialize() {
        Reflections reflections = new Reflections(basePackage);

        Set<Class<?>> clazzesWithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);
        clazzesWithControllerAnnotation.forEach(clazz ->
            Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod ->{
                RequestMapping requestMapping = declaredMethod.getDeclaredAnnotation(RequestMapping.class);
                Arrays.stream(getRequestMethods(requestMapping))
                    .forEach(requestMethod -> handlers.put(
                        new HandlerKey(requestMethod, requestMapping.value()), new AnnotationHandler(clazz, declaredMethod)
                    ));
            }));
    }

    private RequestMethod[] getRequestMethods(RequestMapping requestMapping) {
        return requestMapping.method();
    }
}
