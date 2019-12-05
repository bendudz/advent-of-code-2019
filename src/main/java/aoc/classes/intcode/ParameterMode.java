package aoc.classes.intcode;

import lombok.Getter;

@Getter
public enum ParameterMode {
    POSITION,
    IMMEDIATE;

    public static ParameterMode of(int code) {
        switch (code) {
            case 0:
                return POSITION;
            case 1:
                return IMMEDIATE;
            default:
                throw new UnsupportedOperationException("Unknown ParameterMode: " + code);
        }
    }
}
