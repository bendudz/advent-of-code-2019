package aoc.classes.intcode;

import aoc.day2.Day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class IntcodeComputer {

//    Deprecated
    public static String intCodeProgram(List<Integer> memory) throws Exception {
        int address = 0;
        int result = 0;
        int resultPos = 0;

        while (memory.get(address) != 99) {
        //This is mode 0
            int parameter1 = memory.get(address + 1);
            int parameter2 = memory.get(address + 2);
            int memoryAddress = memory.get(address);
            switch (memoryAddress) {
                case 1:
                    result = memory.get(parameter1) + memory.get(parameter2);
                    resultPos = memory.get(address + 3);
                    memory.set(resultPos, result);
                    address += 4; //no longer the case
                    break;
                case 2:
                    result = memory.get(parameter1) * memory.get(parameter2);
                    resultPos = memory.get(address + 3);
                    memory.set(resultPos, result);
                    address += 4;
                    break;
                case 3:
                    //take input store at position
                    result = memory.get(parameter1);
                    resultPos = memory.get(address + 1);
                    memory.set(resultPos, result);
                    address += 2;
                    break;
                case 4:
                    //take whats at position & output
                    result = memory.get(parameter1);
                    System.out.println(result);
                    address += 2;
                    break;
                case 99:
                    address += 4;
                    break;
                default:
                    throw new Exception(format("Expected 1,2 or 99; found %d", memory.get(0)));
            }
        }
        return intListToCSV(memory);
    }

    private static String intListToCSV(List<Integer> memory) {
        return memory.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    public static String intCodeProgram(String intCode) throws Exception {
        ArrayList<Integer> memory = new ArrayList<>(Day2.csvToIntList(intCode));
        return intCodeProgram(memory);
    }

    public static List<Integer> stringToIntcode(String intCode) {
        return new ArrayList<>(Day2.csvToIntList(intCode));
    }

    public static void runIntcodeProgram(List<Integer> program) {
        runIntcodeProgram(program, new Stack<>(), new Stack<>());
    }

    //Never used stack before but looks like a nice interface. Can pop, search easy through the list.
    public static void runIntcodeProgram(List<Integer> program, Stack<Integer> in, Stack<Integer> out) {
        int address = 0;
        do {
            Instruction instruction = Instruction.of(program, address);
            Integer param1 = instruction.getParam1();
            Integer param2 = instruction.getParam2();

            //Whats the opcode? Decide which operation to carry out
            switch (instruction.getOpCode()) {
                case ADD:
                    program.set(program.get(address + 3), param1 + param2);
                    address += 4;
                    break;
                case MULTIPLY:
                    program.set(program.get(address + 3), param1 * param2);
                    address += 4;
                    break;
                case INPUT:
                    program.set(program.get(address + 1), in.pop());
                    address += 2;
                    break;
                case OUTPUT:
                    out.push(param1);
                    address += 2;
                    break;
                case JUMP_IF_TRUE:
                    address = param1!=0 ? param2 :address+3;
                    break;
                case JUMP_IF_FALSE:
                    address = param1==0 ? param2 :address+3;
                    break;
                case LESS_THAN:
                    program.set(program.get(address+3), param1 < param2 ? 1 : 0);
                    address+=4;
                    break;
                case EQUALS:
                    program.set(program.get(address + 3), param1.equals(param2) ? 1 : 0);
                    address += 4;
                    break;
                case TERMINATE:
                    return;

            }
        } while (true);
    }
}
