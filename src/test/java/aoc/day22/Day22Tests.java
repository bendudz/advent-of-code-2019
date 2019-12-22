package aoc.day22;

import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//import static aoc.day22.Day22.dealIntoNewStack;
import static aoc.day22.Day22.*;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Day22Tests {

    @Test
    public void newStackTest() {
        LinkedList<Integer> stack = new LinkedList<>(Arrays.asList(0,1,2,3,4));
        stack.forEach(System.out::println);
        LinkedList<Integer> newStack = dealIntoNewStack(stack);
        newStack.forEach(System.out::println);
        assertThat(newStack, contains(4,3,2,1,0));
    }

    @Test
    public void cutCards() {
        LinkedList<Integer> stack = new LinkedList<>(Arrays.asList(0,1,2,3,4));
        LinkedList<Integer> newStack = cutNCards(stack, 2);
        assertThat(newStack, contains(2,3,4,0,1));
    }

    @Test
    public void cutCardsRev() {
        LinkedList<Integer> stack = new LinkedList<>(Arrays.asList(0,1,2,3,4));
        LinkedList<Integer> newStack = cutNCards(stack, -2);
        assertThat(newStack, contains(3,4,0,1,2));
    }

    @Test
    public void dealWithInc() throws Exception {
        LinkedList<Integer> stack = new LinkedList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        LinkedList<Integer> newStack = dealWithIncrement(stack, 3);
        assertThat(newStack, contains(0, 7, 4, 1, 8, 5, 2, 9, 6, 3));
    }

    @Test
    public void patternMatcherIncrement() {
        String text = "deal with increment 7";
        Pattern increment = Pattern.compile("deal with increment (-?[0-9]+)");
        Matcher matcher = increment.matcher(text);

        while (matcher.find()) {
            assertThat(matcher.group(1), equalTo("7"));
            System.out.println("Found value: " + matcher.group(0));
            System.out.println("Found value: " + matcher.group(1));
        }
    }

    @Test
    public void patternMatcherCutStack() {
        String text = "cut -7";
        Pattern cut = Pattern.compile("^cut (-?[0-9]+)$");
        Matcher matcher = cut.matcher(text);

        while (matcher.find()) {
            assertThat(matcher.group(1), equalTo("-7"));
            System.out.println("Found value: " + matcher.group(0));
            System.out.println("Found value: " + matcher.group(1));
        }
    }

    @Test
    public void patternMatcherNewStack() {
        String text = "deal into new stack";
        Pattern newStack = Pattern.compile("deal into new stack");
        Matcher matcher = newStack.matcher(text);

        while (matcher.find()) {
            assertThat(matcher.group(0), equalTo("deal into new stack"));
            System.out.println("Found value: " + matcher.group(0));
        }
    }

    @Test
    public void example1Test() throws Exception {
        LinkedList<Integer> stack = new LinkedList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<String> instructions = new ArrayList<>();
        instructions.add("deal with increment 7");
        instructions.add("deal into new stack");
        instructions.add("deal into new stack");
        for (String i : instructions) {
            stack = listInstructions(i,stack);

        }
        assertThat(stack, contains(0,3,6,9,2,5,8,1,4,7));
    }

    @Test
    public void example2Test() throws Exception {
        LinkedList<Integer> stack = new LinkedList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<String> instructions = new ArrayList<>();
        instructions.add("cut 6");
        instructions.add("deal with increment 7");
        instructions.add("deal into new stack");
        for (String i : instructions) {
            stack = listInstructions(i,stack);

        }
        assertThat(stack, contains(3,0,7,4,1,8,5,2,9,6));
    }

    @Test
    public void example3Test() throws Exception {
        LinkedList<Integer> stack = new LinkedList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<String> instructions = new ArrayList<>();
        instructions.add("deal with increment 7");
        instructions.add("deal with increment 9");
        instructions.add("cut -2");
        for (String i : instructions) {
            stack = listInstructions(i,stack);

        }
        assertThat(stack, contains(6,3,0,7,4,1,8,5,2,9));
    }

    @Test
    public void example4Test() throws Exception {
        LinkedList<Integer> stack = new LinkedList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<String> instructions = new ArrayList<>();
        instructions.add("deal into new stack");
        instructions.add("cut -2");
        instructions.add("deal with increment 7");
        instructions.add("cut 8");
        instructions.add("cut -4");
        instructions.add("deal with increment 7");
        instructions.add("cut 3");
        instructions.add("deal with increment 9");
        instructions.add("deal with increment 3");
        instructions.add("cut -1");
        for (String i : instructions) {
            stack = listInstructions(i,stack);
            System.out.println(i + " " + stack);
        }
        assertThat(stack, contains(9,2,5,8,1,4,7,0,3,6));
    }

    @Test
    public void dealWithInc7() throws Exception {
        LinkedList<Integer> stack = new LinkedList<>(Arrays.asList(3, 0, 7, 4, 1, 8, 5, 2, 9, 6));
        LinkedList<Integer> newStack = dealWithIncrement(stack, 7);
        System.out.println(newStack);// 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        assertThat(newStack, contains(3, 4, 5, 6, 7, 8, 9, 0, 1, 2));
    }

    @Test
    public void dealWithInc9() throws Exception {
        LinkedList<Integer> stack = new LinkedList<>(Arrays.asList(6, 9, 2, 5, 8, 1, 4, 7, 0, 3));
        LinkedList<Integer> newStack = dealWithIncrement(stack, 9);
        System.out.println(newStack);// 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
//        assertThat(newStack, contains(6,3,0,7,4,1,8,5,2,9));
    }

    @Test
    public void dealWithInc3() throws Exception {
        LinkedList<Integer> stack = new LinkedList<>(Arrays.asList(6, 3, 0, 7, 4, 1, 8, 5, 2, 9));
        LinkedList<Integer> newStack = dealWithIncrement(stack, 3);
        System.out.println(newStack);// 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        assertThat(newStack, contains(6,5,4,3,2,1,0,9,8,7));
    }

    @Test
    public void solvePart1() throws Exception {
        LinkedList<Integer> stack = IntStream.range(0,10007).boxed().collect(Collectors.toCollection(LinkedList::new));
        List<String> instructions = new ArrayList<>();
        instructions.add("cut -8737");
        instructions.add("deal with increment 36");
        instructions.add("deal into new stack");
        instructions.add("deal with increment 32");
        instructions.add("cut -3856");
        instructions.add("deal with increment 27");
        instructions.add("deal into new stack");
        instructions.add("cut 8319");
        instructions.add("deal with increment 15");
        instructions.add("deal into new stack");
        instructions.add("deal with increment 53");
        instructions.add("cut 2157");
        instructions.add("deal with increment 3");
        instructions.add("deal into new stack");
        instructions.add("cut 9112");
        instructions.add("deal with increment 59");
        instructions.add("cut 957");
        instructions.add("deal with increment 28");
        instructions.add("cut -9423");
        instructions.add("deal with increment 51");
        instructions.add("deal into new stack");
        instructions.add("deal with increment 8");
        instructions.add("cut 3168");
        instructions.add("deal with increment 16");
        instructions.add("cut 6558");
        instructions.add("deal with increment 32");
        instructions.add("deal into new stack");
        instructions.add("cut -8246");
        instructions.add("deal with increment 40");
        instructions.add("cut 4405");
        instructions.add("deal with increment 9");
        instructions.add("cut -2225");
        instructions.add("deal with increment 36");
        instructions.add("cut -5080");
        instructions.add("deal with increment 59");
        instructions.add("cut -648");
        instructions.add("deal with increment 64");
        instructions.add("cut -1845");
        instructions.add("deal into new stack");
        instructions.add("cut -7726");
        instructions.add("deal with increment 44");
        instructions.add("cut 1015");
        instructions.add("deal with increment 12");
        instructions.add("cut 960");
        instructions.add("deal with increment 30");
        instructions.add("deal into new stack");
        instructions.add("deal with increment 65");
        instructions.add("deal into new stack");
        instructions.add("deal with increment 27");
        instructions.add("cut 6877");
        instructions.add("deal with increment 5");
        instructions.add("deal into new stack");
        instructions.add("cut -3436");
        instructions.add("deal with increment 63");
        instructions.add("deal into new stack");
        instructions.add("deal with increment 71");
        instructions.add("deal into new stack");
        instructions.add("deal with increment 7");
        instructions.add("cut -9203");
        instructions.add("deal with increment 38");
        instructions.add("cut 9008");
        instructions.add("deal with increment 59");
        instructions.add("deal into new stack");
        instructions.add("deal with increment 13");
        instructions.add("cut 5979");
        instructions.add("deal with increment 55");
        instructions.add("cut 9483");
        instructions.add("deal with increment 65");
        instructions.add("cut -9250");
        instructions.add("deal with increment 75");
        instructions.add("deal into new stack");
        instructions.add("cut -1830");
        instructions.add("deal with increment 55");
        instructions.add("deal into new stack");
        instructions.add("deal with increment 67");
        instructions.add("cut -8044");
        instructions.add("deal into new stack");
        instructions.add("cut 8271");
        instructions.add("deal with increment 51");
        instructions.add("cut 6002");
        instructions.add("deal into new stack");
        instructions.add("deal with increment 47");
        instructions.add("cut 3638");
        instructions.add("deal with increment 18");
        instructions.add("cut -785");
        instructions.add("deal with increment 63");
        instructions.add("cut -2460");
        instructions.add("deal with increment 25");
        instructions.add("cut 5339");
        instructions.add("deal with increment 61");
        instructions.add("cut -5777");
        instructions.add("deal with increment 54");
        instructions.add("deal into new stack");
        instructions.add("cut 8075");
        instructions.add("deal into new stack");
        instructions.add("deal with increment 22");
        instructions.add("cut 3443");
        instructions.add("deal with increment 34");
        instructions.add("cut 5193");
        instructions.add("deal with increment 3");

        for (String i : instructions) {
            stack = listInstructions(i,stack);
        }
        assert stack != null;
        assertThat(stack.indexOf(2019), equalTo(3324));
    }
}
