package com.daniel.datsuzei.module.impl;

import com.daniel.datsuzei.event.impl.Render2DEvent;
import com.daniel.datsuzei.font.FontManager;
import com.daniel.datsuzei.module.ModuleCategory;
import com.daniel.datsuzei.module.ModuleFeature;
import com.daniel.datsuzei.settings.impl.BooleanSetting;
import com.github.jezevcik.eventbus.Listener;
import com.github.jezevcik.eventbus.annotations.Listen;
import net.minecraft.client.gui.FontRenderer;
import org.lwjglx.input.Keyboard;

public class WatermarkModule extends ModuleFeature {

    public final BooleanSetting dropShadow = new BooleanSetting("DropShadow", true);

    public WatermarkModule() {
        super(new ModuleData("Watermark", "Displays a watermark", ModuleCategory.RENDER),
                new BindableData(Keyboard.KEY_K), null);
    }

    @Listen
    public final Listener<Render2DEvent> render2DEventListener = _ -> {
        final FontRenderer fontRenderer = FontManager.getSingleton().get("Arial", "Regular", 50);
        if(dropShadow.getValue())
            fontRenderer.drawStringWithShadow("Datsuzei", 3, 2, -1);
        else
            fontRenderer.drawString("Datsuzei", 3, 2, -1);
    };

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

}
