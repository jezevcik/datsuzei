package com.daniel.datsuzei.settings.impl;

import com.daniel.datsuzei.settings.SettingFeature;
import com.daniel.datsuzei.util.math.MathUtil;
import lombok.Getter;

public class NumberSetting<T extends Number> extends SettingFeature<T> {

    @Getter
    private T value;
    private final T minimum, maximum;

    public NumberSetting(String name, T value, T minimum, T maximum) {
        super(name);

        this.value = value;
        this.minimum = minimum;
        this.maximum = maximum;

        if(!(this.value instanceof Float || this.value instanceof Integer))
            throw new IllegalArgumentException("Value must be either an integer or a float!");
    }

    @Override
    public void setValue(T value) {
        float floatValue = value.floatValue();
        float newValue = MathUtil.clamp(floatValue, minimum.floatValue(), maximum.floatValue());

        if(this.value instanceof Integer) {
            this.value = (T) (Number) Math.round(newValue);
        } else {
            this.value = (T) (Number) newValue;
        }
    }
}
