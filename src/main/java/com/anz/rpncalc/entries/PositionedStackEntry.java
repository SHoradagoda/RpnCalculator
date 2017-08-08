package com.anz.rpncalc.entries;

/**
 *
 * The holder of a position and StackEntry.
 *
 */
public class PositionedStackEntry {

    private final int position;
    private final StackEntry entry;

    public PositionedStackEntry ( int position, StackEntry entry ) {
        this.position = position;
        this.entry = entry;
    }

    public int getPosition() {
        return position;
    }

    public StackEntry getEntry() {
        return entry;
    }

    @Override
    public String toString() {
        return "PositionedStackEntry{" +
                "position=" + position +
                ", entry=" + entry +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionedStackEntry that = (PositionedStackEntry) o;

        if (position != that.position) return false;
        return entry.equals(that.entry);

    }

    @Override
    public int hashCode() {
        int result = position;
        result = 31 * result + entry.hashCode();
        return result;
    }
}
