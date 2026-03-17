package io.github.isguliyev.examples.pirate;

public class Pirate {
    private Long id;
    private String name;
    private String role;
    private String crew;

    public Pirate(String name, String role, String crew) {
        this.setName(name);
        this.role = role;
        this.crew = crew;
    }

    public Pirate(Long id, String name, String role, String crew) {
        this.setId(id);
        this.setName(name);
        this.role = role;
        this.crew = crew;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Pirate pirate)) {
            return false;
        }

        return this.id != null && this.id.equals(pirate.id);
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getRole() {
        return this.role;
    }

    public String getCrew() {
        return this.crew;
    }

    public void setId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Failed to set id: id is null");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("Failed to set id: id is not positive");
        }

        if (this.id != null) {
            throw new IllegalStateException("Failed to set id: id is already set");
        }

        this.id = id;
    }

    private void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Failed to set name: name is null");
        }

        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Failed to set name: name is blank");
        }

        this.name = name;
    }


    public void setRole(String role) {
        this.role = role;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }
}