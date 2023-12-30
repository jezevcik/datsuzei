package com.daniel.datsuzei.module.impl;

import com.daniel.datsuzei.module.ModuleCategory;
import com.daniel.datsuzei.module.ModuleFeature;
import org.lwjglx.input.Keyboard;

import java.security.Key;

public class ExampleModule extends ModuleFeature {

    public ExampleModule() {
        super(new ModuleData("ExampleModule", "A module used as an example", ModuleCategory.FUN),
                new BindableData(Keyboard.KEY_K), null);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

}
