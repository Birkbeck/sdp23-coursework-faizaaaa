package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// TODO: write a JavaDoc for the class

//Labels class which consists of HashMap containing any labels that occur along with
// the instruction number it would occur at. Used to update program counter
// in the SML interpreter when jnz instruction is executed.

/**
 *
 * @author ...
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) throws Exception {
		Objects.requireNonNull(label);
		if(labels.containsKey(label)) {
			throw new Exception("Cannot have duplicate labels.");
		}
		// TODO: Add a check that there are no label duplicates.
		labels.put(label, address);
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// TODO: Where can NullPointerException be thrown here?
		//       (Write an explanation.)
		// Null pointer exceptions can occur when labels are used which don't exist in the labels map.
		try {
			return labels.get(label);
		}
		catch(NullPointerException e) {
			System.out.println("Label does not exist. Program terminated.");
			return 1000000001; //Assumming the program size does not exceed this number
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
		// TODO: Implement the method using the Stream API (see also class Registers).
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(e -> e.getKey() + " = " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]")) ;
	}

	// TODO: Implement equals and hashCode (needed in class Machine).

	public boolean equals(Labels l){
		return l.labels.equals(this.labels);
	}
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
