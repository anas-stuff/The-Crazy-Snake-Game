package com.anas.code.enums;

public enum Directions {
    RIGHT("LEFT"),
    LEFT("RIGHT"),
    UP("DOWN"),
    DOWN("UP");

    private final String opposite;
    private Directions(String opposite) {
        this.opposite = opposite;
    }

    public String getOpposite() {
        return opposite;
    }
}
