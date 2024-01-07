package com.daniel.datsuzei.module.impl;

import com.daniel.datsuzei.module.ModuleCategory;
import com.daniel.datsuzei.module.ModuleFeature;
import com.daniel.datsuzei.screen.clickgui.ClickGui;
import org.lwjglx.input.Keyboard;

public class ModuleScreenModule extends ModuleFeature {

    public ClickGui clickGui = new ClickGui(this);

    public ModuleScreenModule() {
        super(new ModuleData("ModuleScreen", "A screen for toggling and configuring modules", ModuleCategory.RENDER),
                new BindableData(Keyboard.KEY_RSHIFT), null);
    }

    @Override
    protected void onEnable() {
        mc.displayGuiScreen(clickGui);
        setEnabled(false);
    }

    @Override
    protected void onDisable() {

    }

}
