package hu.ulyssys.java.course.maven.entity;

public enum PublicSpaceType {
    TER("tér"),
    UT("út"),
    UTCA("utca"),
    KOZ("köz");

    private final String natureOfPublicSpace;

    PublicSpaceType(final String natureOfPublicSpace) {
        this.natureOfPublicSpace = natureOfPublicSpace;
    }

    @Override
    public String toString() {
        return this.natureOfPublicSpace;
    }
}
