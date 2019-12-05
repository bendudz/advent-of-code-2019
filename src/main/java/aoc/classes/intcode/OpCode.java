package aoc.classes.intcode;

public enum OpCode {
    ADD,
    MULTIPLY,
    INPUT,
    OUTPUT,
    JUMP_IF_TRUE,
    JUMP_IF_FALSE,
    LESS_THAN,
    EQUALS,
    TERMINATE;

    public static OpCode of(int code) {
        switch (code) {
            case 1:
                return ADD;
            case 2:
                return MULTIPLY;
            case 3:
                return INPUT;
            case 4:
                return OUTPUT;
            case 5:
                return JUMP_IF_TRUE;
            case 6:
                return JUMP_IF_FALSE;
            case 7:
                return LESS_THAN;
            case 8:
                return EQUALS;
            case 99:
                return TERMINATE;
            default:
                throw new UnsupportedOperationException("Unknown opcode: " + code);
        }
    }

}
