package com.daniel.datsuzei.feature;

import com.daniel.datsuzei.DatsuzeiClient;
import com.daniel.datsuzei.util.interfaces.MinecraftClient;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@RequiredArgsConstructor
public class Manager<T extends Feature> implements MinecraftClient {
    private final List<Class<T>> classes = new ArrayList<>();
    private final Map<String, T> map = new TreeMap<>();
    private final Class<T> mainType;

    @SuppressWarnings("unchecked")
    public void preMinecraftLaunch() {
        // Use reflection to discover and instantiate all feature subclasses in the "com.daniel.datsuzei" package
        final Reflections reflections = new Reflections("com.daniel.datsuzei");
        reflections.getSubTypesOf(mainType).forEach(featureClass -> {
            // Add the class to a list, to be instantiated after Minecraft has launcher
            classes.add((Class<T>) featureClass);
        });
        // Log the amount of found classes
        DatsuzeiClient.getSingleton().getLogger().info(STR."Loaded \{classes.size()} subtypes of \{mainType.getSimpleName()}");
    }

    public void postMinecraftLaunch() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for(Class<T> clazz : classes) {
            T feature = clazz.getDeclaredConstructor().newInstance();
            map.put(feature.getName(), feature);
        }
    }

    public final T getByName(String name) {
        return map.get(name);
    }

    public final Collection<T> getFeatures() {
        return map.values();
    }

}
