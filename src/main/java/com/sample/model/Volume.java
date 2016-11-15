package com.sample.model;

public class Volume {
    private int value = -1;
    private int rank = -1;

    /**
     * Constructor for Volume.
     * 
     * @param value
     */
    public Volume(int value) {
        super();
        this.value = value;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @param rank
     *            the rank to set
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Volume [value=" + value + ", rank=" + rank + "]";
    }
}