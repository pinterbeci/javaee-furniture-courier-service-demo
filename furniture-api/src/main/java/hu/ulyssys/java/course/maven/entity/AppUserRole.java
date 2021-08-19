package hu.ulyssys.java.course.maven.entity;

public enum AppUserRole {
    USER("Vásárló"),
    ADMIN("Admin");

    private final String role;

    AppUserRole(final String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
