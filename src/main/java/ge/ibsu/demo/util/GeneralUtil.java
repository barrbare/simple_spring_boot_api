package ge.ibsu.demo.util;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public final class GeneralUtil {

    public static String getGetterName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static String getSetterName(String fieldName) {
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static void checkRequiredProperties(Object objectToCheck, List<String> requiredProperties) throws Exception {
        ArrayList<String> errorKeywords = new ArrayList<>();
        Iterator<String> it = requiredProperties.iterator();
        while (it.hasNext()) {
            String property = (String) it.next();
            boolean requiredPropertyIsPresent = true; 
            Object value = null;
            try {
                value = objectToCheck
                        .getClass()
                        .getMethod(getGetterName(property))
                        .invoke(objectToCheck);
            } catch (Exception ex) {
                requiredPropertyIsPresent = false;
            }
            if (value == null) {
                requiredPropertyIsPresent = false;
            }
            if (!requiredPropertyIsPresent) {
                errorKeywords.add(property+"_REQUIRED");
            }
        }
        if (!errorKeywords.isEmpty()) {
            throw new RuntimeException(errorKeywords.stream().collect(Collectors.joining(";")));
        }
    }

    private static List<Field> getFieldsUpTo(Class<?> startClass, Class<?> exclusiveParent) {
        List<Field> currentClassFields = new ArrayList();
        currentClassFields.addAll(Arrays.asList(startClass.getDeclaredFields()));
        Class<?> parentClass = startClass.getSuperclass();
        if (parentClass != null && (exclusiveParent == null || !parentClass.equals(exclusiveParent))) {
            List<Field> parentClassFields = getFieldsUpTo(parentClass, exclusiveParent);
            currentClassFields.addAll(parentClassFields);
        }

        return currentClassFields;
    }

    private static <T1, T2> void copyValue(T1 sourceObject, T2 destinationObject, String propertyName)
            throws Exception {

        Object sourceValue = sourceObject.getClass().getMethod(getGetterName(propertyName)).invoke(sourceObject);
        Class sourceReturnType = sourceObject.getClass().getMethod(getGetterName(propertyName)).getReturnType();
        Class destinationReturnType = destinationObject.getClass().getMethod(getGetterName(propertyName))
                .getReturnType();
        Class returnType = null;
        if (sourceReturnType.equals(destinationReturnType)) {
            if (sourceValue != null) {
                destinationObject.getClass().getMethod(getSetterName(propertyName), sourceReturnType)
                        .invoke(destinationObject, sourceValue);
            }
        }

    }

    public static <T1, T2> T2 getCopyOf(T1 source, T2 destination, String... excludeProps) throws Exception {

        List<Field> destinationFields = getFieldsUpTo(destination.getClass(), Object.class);
        List<String> destinationFieldNames = new ArrayList();
        Iterator var5 = destinationFields.iterator();

        while (var5.hasNext()) {
            Field item = (Field) var5.next();
            destinationFieldNames.add(item.getName());
        }

        List<String> excludeProperties = Arrays.asList(excludeProps);
        Iterator var10 = getFieldsUpTo(source.getClass(), Object.class).iterator();

        while (var10.hasNext()) {
            Field f = (Field) var10.next();
            if (!Modifier.isStatic(f.getModifiers()) && excludeProperties.indexOf(f.getName()) <= -1
                    && destinationFieldNames.indexOf(f.getName()) != -1) {
                copyValue(source, destination, f.getName());
            }
        }

        return destination;

    }

}
