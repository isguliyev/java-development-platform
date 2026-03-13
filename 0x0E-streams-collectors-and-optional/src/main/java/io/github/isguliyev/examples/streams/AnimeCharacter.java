package io.github.isguliyev.examples.streams;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class AnimeCharacter {
    private final String name;
    private final String animeName;
    private final List<String> abilities;

    public AnimeCharacter(String name, String animeName, List<String> abilities) {
        this.name = name;
        this.animeName = animeName;
        this.abilities = new ArrayList<String>(abilities);
    }

    @Override
    public String toString() {
        return String.format(
            "%s [name=%s, animeName=%s, abilities=%s]",
            this.getClass().getSimpleName(),
            this.name,
            this.animeName,
            this.abilities
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof AnimeCharacter animeCharacter)) {
            return false;
        }

        final boolean areNamesEqual = Objects.equals(this.name, animeCharacter.name);
        final boolean areAnimeNamesEqual = Objects.equals(this.animeName, animeCharacter.animeName);
        final boolean areAbilitiesEqual = Objects.equals(this.abilities, animeCharacter.abilities);

        return areNamesEqual && areAnimeNamesEqual && areAbilitiesEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.animeName, this.abilities);
    }

    public String getName() {
        return this.name;
    }

    public String getAnimeName() {
        return this.animeName;
    }

    public List<String> getAbilities() {
        return new ArrayList<String>(this.abilities);
    }
}