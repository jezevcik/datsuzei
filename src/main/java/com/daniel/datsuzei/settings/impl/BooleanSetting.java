package com.daniel.datsuzei.settings.impl;

import com.daniel.datsuzei.settings.SettingFeature;

public class BooleanSetting extends SettingFeature<Boolean> {

    private boolean value;

    public BooleanSetting(String name, boolean value) {
        super(name);
        this.value = value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }
}
