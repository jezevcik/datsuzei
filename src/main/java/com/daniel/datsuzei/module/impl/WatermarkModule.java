package com.daniel.datsuzei.module.impl;

import com.daniel.datsuzei.DatsuzeiClient;
import com.daniel.datsuzei.event.impl.Render2DEvent;
import com.daniel.datsuzei.module.ModuleCategory;
import com.daniel.datsuzei.module.ModuleFeature;
import com.github.jezevcik.eventbus.Listener;
import com.github.jezevcik.eventbus.annotations.Listen;
import net.minecraft.client.gui.FontRenderer;
import org.lwjglx.input.Keyboard;

public class WatermarkModule extends ModuleFeature {

    public WatermarkModule() {
        super(new ModuleData("Watermark", "Displays a watermark", ModuleCategory.RENDER),
                new BindableData(Keyboard.KEY_K), null);
    }

    @Listen
    public final Listener<Render2DEvent> render2DEventListener = render2DEvent -> {
        final FontRenderer fontRenderer = DatsuzeiClient.getSingleton().getFontManager().get("Arial", "Regular", 50);
        fontRenderer.drawStringWithShadow("Datsuzei = 脱税", 3, 3, -1);
    };

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

}
