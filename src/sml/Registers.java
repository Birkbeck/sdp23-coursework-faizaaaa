package sml;

import java.util.*;
import java.util.stream.Collectors;

// TODO: write a JavaDoc for the class
// Register class consisting of a HashMap which stores the value held in each register for the sml.

/**
 *
 * @author ...
 */
public final class Registers {
    private final Map<Register, Integer> registers = new HashMap<>();
    private static Registers r;

    public enum Register implements RegisterName {
        EAX, EBX, ECX, EDX, ESP, EBP, ESI, EDI;
    }

    private Registers() {
        clear(); // the class is final
    }

    public static Registers getInstance() {
        if (r==null) {r = new Registers();}
        return r;
    }

    public void clear() {
        for (Register register : Register.values())
            registers.put(register, 0);
    }

    /**
     * Sets the given register to the value.
     *
     * @param register register name
     * @param value new value
     */
    public void set(RegisterName register, int value) {
        registers.put((Register)register, value);
    }

    /**
     * Returns the value stored in the register.
     *
     * @param register register name
     * @return value
     */
    public int get(RegisterName register) {
        return registers.get((Register)register);
    }

    // TODO: use pattern matching for instanceof
    // https://docs.oracle.com/en/java/javase/14/language/pattern-matching-instanceof-operator.html
    @Override
    public boolean equals(Object o) {
        if (o instanceof Registers) {
            Registers other = (Registers) o;
            return registers.equals(other.registers);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return registers.hashCode();
    }

    @Override
    public String toString() {
        return registers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]")) ;
    }
}
