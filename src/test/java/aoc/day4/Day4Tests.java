package aoc.day4;

import org.junit.Test;
import static aoc.day4.Day4.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Day4Tests {


    @Test
    public void day4Examples() {
        assertTrue(meetCriteria(111111));
        assertTrue(meetCriteria(123444));
        assertFalse(meetCriteria(223450));
        assertFalse(meetCriteria(123789));
    }

    @Test
    public void day4ExamplesPt2() {
        assertFalse(meetCriteriaPart2(123444));
        assertTrue(meetCriteriaPart2(112233));
        assertTrue(meetCriteriaPart2(111122));
    }

    @Test
    public void day4SecureContainerAnswer() {
        assertThat(calculateNumberOfPasswords(), equalTo(1640));
        assertThat(calculateNumberOfPasswordsPt2(), equalTo(1126));
    }
}
