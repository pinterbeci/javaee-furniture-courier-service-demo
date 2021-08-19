package hu.ulyssys.java.course.maven.entity;

public enum FurnitureType {
    FURNITURE("Bútor"),
    ORDER("Rendelés"),
    COURIER("Futár");

    private final String role;

    FurnitureType(final String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
