package com.putoet.day13;

import java.awt.desktop.OpenURIEvent;

public enum Order {
    IN_ORDER,
    NOT_IN_ORDER,
    CONTINUE;

    static Order from(int compareTo) {
        if (compareTo < 0)
            return IN_ORDER;
        else if (compareTo == 0)
            return CONTINUE;
        else
            return NOT_IN_ORDER;
    }
}
