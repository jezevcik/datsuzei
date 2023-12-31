package com.daniel.datsuzei.event.impl;

import com.daniel.datsuzei.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter

@RequiredArgsConstructor
public class KeyPressEvent extends Event {
    private final int key;
}
