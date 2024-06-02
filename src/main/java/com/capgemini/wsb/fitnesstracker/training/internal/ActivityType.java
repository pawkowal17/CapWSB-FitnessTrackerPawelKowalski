package com.capgemini.wsb.fitnesstracker.training.internal;

/**
 * Enum representing different types of training activities.
 */
public enum ActivityType {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tenis");

    private final String displayName;

    /**
     * Constructor for ActivityType.
     *
     * @param displayName the display name of the activity type
     */
    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the display name of the activity type.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

}