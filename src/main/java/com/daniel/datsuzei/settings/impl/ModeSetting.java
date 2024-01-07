package com.daniel.datsuzei.settings.impl;

import com.daniel.datsuzei.settings.SettingFeature;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ModeSetting extends SettingFeature<String> {

    private String value;
    private final List<String> modes;

    public ModeSetting(String name, String value, String... modes) {
        super(name);

        this.value = value;
        this.modes = Arrays.asList(modes);
    }

    @Override
    public void setValue(String value) {
        if(modes.contains(value))
            this.value = value;
        else
            throw new IllegalArgumentException(STR."Value \{value} is not in the mode list \{modes.toArray()}");
    }
}
