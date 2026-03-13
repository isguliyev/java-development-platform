package io.github.isguliyev.examples.streams;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.groupingBy;

public class Query {
    public static Map<String, Set<AnimeCharacter>> groupAnimeCharactersByAnimeName(List<AnimeCharacter> animeCharacters) {
        return animeCharacters.stream().collect(groupingBy(AnimeCharacter::getAnimeName, toSet()));
    }

    public static List<AnimeCharacter> filterAnimeCharactersByAnimeName(List<AnimeCharacter> animeCharacters, String animeName) {
        return animeCharacters.stream().filter(
            (animeCharacter) -> Objects.equals(animeCharacter.getAnimeName(), animeName)
        ).toList();
    }

    public static Map<String, Set<String>> groupAnimeCharacterNamesByAbility(List<AnimeCharacter> animeCharacters) {
        return animeCharacters.stream().flatMap(
            (character) -> character.getAbilities().stream().map(ability -> Map.entry(ability, character.getName()))
        ).collect(
            groupingBy(Map.Entry::getKey, mapping(Map.Entry::getValue, toSet()))
        );
    }
}