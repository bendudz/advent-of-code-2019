package aoc.classes.intcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Instruction {
    OpCode opCode;
    Integer param1;
    Integer param2;
    Integer param3;

    public static Instruction of(List<Integer> program, int address) {
        int code = program.get(address);
        OpCode opCode = OpCode.of(code % 100);
        Integer param1 = getParam(program, ParameterMode.of((code / 100) % 2), address + 1);
        Integer param2 = getParam(program, ParameterMode.of((code / 1000) % 2), address + 2);
        Integer param3 = getParam(program, ParameterMode.of((code / 10000) % 2), address + 3);
        return new Instruction(opCode,param1,param2,param3);
    }

    private static Integer getParam(List<Integer> program, ParameterMode mode, int offset) {
        if (offset >= program.size()) {
            return null;
        }
        int reference = program.get(offset);
        switch (mode) {
            case IMMEDIATE:
                return reference;
            case POSITION:
                return reference >= 0 && reference < program.size() ? program.get(reference) : null;
            default:
                throw new IllegalArgumentException();
        }
    }
}
