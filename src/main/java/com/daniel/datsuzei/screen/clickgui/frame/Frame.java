package com.daniel.datsuzei.screen.clickgui.frame;

import com.daniel.datsuzei.DatsuzeiClient;
import com.daniel.datsuzei.font.ClientFontRenderer;
import com.daniel.datsuzei.font.FontManager;
import com.daniel.datsuzei.module.ModuleCategory;
import com.daniel.datsuzei.module.ModuleFeature;
import com.daniel.datsuzei.util.render.DrawUtil;
import de.florianmichael.rclasses.math.integration.Boundings;

import java.awt.*;
import java.util.ArrayList;

public class Frame {

    private final ModuleCategory category;
    private final float width, height;
    private float x, y;

    private boolean expanded;

    private boolean dragging;
    private float draggingX, draggingY;

    private final ArrayList<ModuleFeature> modules = new ArrayList<>();

    public Frame(ModuleCategory category, float x, float y, float width, float height) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        modules.addAll(DatsuzeiClient.getSingleton().getModuleManager().getByCategory(category));
    }

    public void draw(int mouseX, int mouseY) {
        if(dragging) {
            x = mouseX - draggingX;
            y = mouseY - draggingY;
        }

        final boolean categoryHovered = Boundings.isInBounds(mouseX, mouseY, x, y, width, height);

        DrawUtil.drawRectRelative(x, y, width, height, new Color(categoryHovered ? 110 : 80, categoryHovered ? 110 : 80, categoryHovered ? 110 : 80, 180).getRGB());
        DrawUtil.drawBorderRelative(x, y, width, height, 1, Color.BLACK.getRGB(), true);

        final ClientFontRenderer nameFont = DatsuzeiClient.getSingleton().getFontManager().get("Arial", 20);
        nameFont.drawTotalCenteredStringWithShadow(category.getName(), x + width / 2, y + height / 2, -1);

        if(expanded && !modules.isEmpty()) {
            float moduleY = y + height + 2;
            final float initialY = moduleY;

            for(ModuleFeature module : modules) {
                final boolean moduleHovered = Boundings.isInBounds(mouseX, mouseY, x + 2, moduleY + 2, width - 4, height - 4);

                DrawUtil.drawRectRelative(x, moduleY, width, height, new Color(80, 80, 80, 180).getRGB());

                DrawUtil.drawRectRelative(x + 2, moduleY + 2, width - 4, height - 4, new Color(moduleHovered ? 50 : 30, moduleHovered ? 50 : 30, moduleHovered ? 50 : 30, 100).getRGB());
                DrawUtil.drawBorderRelative(x + 2, moduleY + 2, width - 4, height - 4, 1, Color.BLACK.getRGB(), true);

                nameFont.drawTotalCenteredStringWithShadow(module.getName(), x + width / 2, moduleY + height / 2, -1);

                moduleY += height - 2;
            }

            DrawUtil.drawBorderRelative(x, initialY, width, (height - 1) * modules.size(), 1, Color.BLACK.getRGB(), true);
        }
    }

    public void keyTyped(char character, int key) {

    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        if(Boundings.isInBounds(mouseX, mouseY, x, y, width, height)) {
            switch (button) {
                case 0 -> {
                    dragging = true;

                    draggingX = mouseX - x;
                    draggingY = mouseY - y;
                }
                case 1 -> expanded = !expanded;
            }
        }

        if(expanded && !modules.isEmpty()) {
            float moduleY = y + height + 5;
            for (ModuleFeature module : modules) {
                final boolean moduleHovered = Boundings.isInBounds(mouseX, mouseY, x + 2, moduleY + 2, width - 4, height - 4);

                if(moduleHovered) {
                    if(button == 0) {
                        module.toggleEnabled();
                    }
                }

                moduleY += height;
            }
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int button) {
        if(button == 0) {
            dragging = false;
        }
    }
}
