package sml;

import java.util.*;
import java.util.stream.Collectors;



/**
 * Register class consisting of a HashMap which stores the value held in each register for the sml.
 *
 * @author Faizaa Fazal and Lecturer
 */
public final class Registers {
    private final Map<Register, Integer> registers = new HashMap<>();
    private static Registers registerObject;

    /**
     * Enum representing register names available to be used in the Machine.
     */
    public enum Register implements RegisterName {
        EAX, EBX, ECX, EDX, ESP, EBP, ESI, EDI
    }

    private Registers() {
        clear(); // the class is final
    }

    /**
     * Returns the instance of registers. Only one instance of registers is allowed in this program as there is
     * usually only one set of registers in a machine.
     * Applied singleton pattern here.
     * @return Register instance
     */
    public static Registers getInstance() {
        if (registerObject ==null) {
            registerObject = new Registers();}
        return registerObject;
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

    /**
     * Checks to see if object o is equal to the register instance.
     *
     * @param o object compared with instance of Registers
     * @return boolean true or false.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Registers other) {
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
