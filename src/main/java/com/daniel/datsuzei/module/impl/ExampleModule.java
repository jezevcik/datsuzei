package com.daniel.datsuzei.module.impl;

import com.daniel.datsuzei.DatsuzeiClient;
import com.daniel.datsuzei.event.impl.Render2DEvent;
import com.daniel.datsuzei.font.ClientFontRenderer;
import com.daniel.datsuzei.module.ModuleCategory;
import com.daniel.datsuzei.module.ModuleFeature;
import com.github.jezevcik.eventbus.Listener;
import com.github.jezevcik.eventbus.annotations.Listen;
import org.lwjglx.input.Keyboard;

public class ExampleModule extends ModuleFeature {

    public ExampleModule() {
        super(new ModuleData("ExampleModule", "A module used as an example", ModuleCategory.FUN),
                new BindableData(Keyboard.KEY_K), null);
    }

    private final ClientFontRenderer clientFontRenderer = DatsuzeiClient.getSingleton().getFontManager().get("Roboto", "Regular", 18);

    @Listen
    private final Listener<Render2DEvent> render2DEventListener = render2DEvent -> {
        System.out.println("ff");
          clientFontRenderer.drawString("Hello, World!", 2, 2, -1);
    };

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

}
