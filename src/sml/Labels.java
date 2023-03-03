package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Labels class which consists of HashMap containing any labels that occur along with
 * the instruction number it would occur at. Used to update program counter
 * in the SML interpreter when jnz instruction is executed.
 *
 * @author Faizaa Fazal
 */
public final class Labels {
    private final Map<String, Integer> labels = new HashMap<>();

    /**
     * Adds a label with the associated address to the map.
     *
     * @param label   the label
     * @param address the address the label refers to
     */
    public void addLabel(String label, int address) throws Exception {
        Objects.requireNonNull(label);
        if (labels.containsKey(label)) {
            throw new Exception("Cannot have duplicate labels.");
        }
        labels.put(label, address);
    }

    /**
     * Returns the address associated with the label.
     *
     * @param label the label
     * @return the address the label refers to
     */
    public int getAddress(String label) {
        // Null pointer exceptions can occur when labels are used which don't exist in the labels map.
        try {
            return labels.get(label);
        } catch (NullPointerException e) {
            System.out.println("Label does not exist. Program terminated.");
            return 1000000001; //Assuming the program size does not exceed this number
        }

    }

    /**
     * representation of this instance,
     * in the form "[label -> address, label -> address, ..., label -> address]"
     *
     * @return the string representation of the labels map
     */
    @Override
    public String toString() {
        return labels.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + " -> " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]"));
    }

    /**
     * Checks if Labels parameter is equal to the current instance of Labels.
     */

    public boolean equals(Labels l) {
        return l.labels.equals(this.labels);
    }

    /**
     * Returns the hashcode of labels object.
     */
    public int hashCode() {
        return labels.hashCode();
    }

    /**
     * Removes the labels
     */
    public void reset() {
        labels.clear();
    }
}
