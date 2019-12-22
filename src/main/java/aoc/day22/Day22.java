package aoc.day22;

import java.util.Collections;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day22 {

    public static LinkedList<Integer> dealIntoNewStack(LinkedList<Integer> stack) {
        Collections.reverse(stack);
        return stack;
    }

    public static LinkedList<Integer> cutNCards(LinkedList<Integer> stack, int cut){
        LinkedList<Integer> cutStack;
        if(cut > 0) {
            cutStack = new LinkedList<>(stack.subList(0, cut));
        } else {
            cutStack = new LinkedList<>(stack.subList(0, stack.size() + cut));
        }
        cutStack.forEach(stack::remove);
        cutStack.forEach(stack::addLast);
        return stack;
    }

    public static LinkedList<Integer> dealWithIncrement(LinkedList<Integer> stack, int increment) throws Exception {
        LinkedList<Integer> table = new LinkedList<>();
        for (int e : stack) {
            table.add(-1);
        }
        int pos = 0;
        table.set(pos,stack.get(0));
        stack.remove(0);
        pos += increment;
        for (int card : stack) {
            if (table.get(pos).equals(-1)) {
                table.set(pos, card);
            } else {
                throw new Exception("Table already has card at position: " + pos);
            }

            if ((pos + increment) > (stack.size() +1)) {
                pos = (pos+increment) - (stack.size()+1);
            } else {
                pos +=increment;
            }
        }
        return table;
    }

    public static LinkedList<Integer> listInstructions(String instruction, LinkedList<Integer> currentStack) throws Exception {
        Pattern increment = Pattern.compile("deal with increment (-?[0-9]+)");
        Pattern cut = Pattern.compile("^cut (-?[0-9]+)$");
        Pattern newStack = Pattern.compile("deal into new stack");

        Matcher mInc = increment.matcher(instruction);
        Matcher mCut = cut.matcher(instruction);
        Matcher mStack = newStack.matcher(instruction);

        while (mInc.find()) {
            int inc =Integer.parseInt(mInc.group(1));
            return dealWithIncrement(currentStack, inc);
        }

        while (mCut.find()) {
            int cutN = Integer.parseInt(mCut.group(1));
            return cutNCards(currentStack, cutN);
        }

        while (mStack.find()) {
            return dealIntoNewStack(currentStack);
        }
        return null;
    }
}
