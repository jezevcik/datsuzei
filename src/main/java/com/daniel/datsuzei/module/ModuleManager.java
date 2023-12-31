package com.daniel.datsuzei.module;

import com.daniel.datsuzei.DatsuzeiClient;
import com.daniel.datsuzei.event.impl.KeyPressEvent;
import com.daniel.datsuzei.feature.Manager;
import com.github.jezevcik.eventbus.Listener;
import com.github.jezevcik.eventbus.annotations.Listen;

public class ModuleManager extends Manager<ModuleFeature> {

    public ModuleManager() {
        super(ModuleFeature.class);
    }

    @Override
    public void preMinecraftLaunch() {
        super.preMinecraftLaunch();

        DatsuzeiClient.getSingleton().getEventBus().subscribe(this);
    }

    @Listen
    public final Listener<KeyPressEvent> keyPressEventListener = keyPressEvent -> {
        this.map.values().stream().filter(moduleFeature -> moduleFeature.getKey() == keyPressEvent.getKey()).forEach(ModuleFeature::toggleEnabled);
    };

}
