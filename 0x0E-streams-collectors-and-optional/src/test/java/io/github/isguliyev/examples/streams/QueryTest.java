package io.github.isguliyev.examples.streams;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class QueryTest {
    private List<AnimeCharacter> animeCharacters;

    @BeforeEach
    void setUp() {
        this.animeCharacters = List.of(
            new AnimeCharacter(
                "Naruto Uzumaki",
                "Naruto",
                List.of(
                    "Shadow Clone Jutsu",
                    "Rasengan",
                    "Rasenshuriken",
                    "Sage Mode",
                    "Nine-Tails Chakra Mode",
                    "Tailed Beast Bomb",
                    "Six Paths Sage Mode"
                )
            ),
            new AnimeCharacter(
                "Sasuke Uchiha",
                "Naruto",
                List.of(
                    "Chidori",
                    "Lightning Blade",
                    "Fireball Jutsu",
                    "Sharingan",
                    "Genjutsu",
                    "Amaterasu",
                    "Susanoo",
                    "Rinnegan",
                    "Amenotejikara"
                )
            ),
            new AnimeCharacter(
                "Jiraiya",
                "Naruto",
                List.of(
                    "Rasengan",
                    "Sage Mode",
                    "Toad Summoning",
                    "Fireball Jutsu",
                    "Toad Oil Bullet",
                    "Toad Mouth Trap",
                    "Hair Needle Senbon",
                    "Wild Lion's Mane"
                )
            ),
            new AnimeCharacter(
                "Kakashi Hatake",
                "Naruto",
                List.of(
                    "Chidori",
                    "Lightning Blade",
                    "Purple Lightning",
                    "Sharingan",
                    "Kamui",
                    "Shadow Clone Jutsu",
                    "Water Dragon Jutsu",
                    "Earth Wall",
                    "Summoning: Ninja Dogs"
                )
            ),
            new AnimeCharacter(
                "Obito Uchiha",
                "Naruto",
                List.of(
                    "Sharingan",
                    "Mangekyo Sharingan",
                    "Kamui",
                    "Kamui Teleportation",
                    "Intangibility",
                    "Fireball Jutsu",
                    "Bomb Blast Dance",
                    "Wood Release",
                    "Truth-Seeking Orbs"
                )
            ),
            new AnimeCharacter(
                "Itachi Uchiha",
                "Naruto",
                List.of(
                    "Sharingan",
                    "Mangekyo Sharingan",
                    "Tsukuyomi",
                    "Amaterasu",
                    "Susanoo",
                    "Yata Mirror",
                    "Totsuka Blade",
                    "Fireball Jutsu",
                    "Crow Clone",
                    "Finger Genjutsu"
                )
            ),
            new AnimeCharacter(
                "Monkey D. Luffy",
                "One Piece",
                List.of(
                    "Gomu Gomu no Pistol",
                    "Gear Second",
                    "Gear Third",
                    "Gear Fourth",
                    "Gear Fifth",
                    "Haki",
                    "Conqueror's Haki",
                    "Observation Haki",
                    "Armament Haki"
                )
            ),
            new AnimeCharacter(
                "Roronoa Zoro",
                "One Piece",
                List.of(
                    "Three Sword Style",
                    "Santoryu",
                    "Oni Giri",
                    "Asura",
                    "Enma",
                    "Armament Haki",
                    "Observation Haki",
                    "Conqueror's Haki"
                )
            )
        );
    }

    @Test
    void groupAnimeCharactersByAnimeName_returnsMapOfAnimeCharactersGroupedByAnimeName() {
        final Map<String, Set<AnimeCharacter>> expectedResult = Map.ofEntries(
            Map.entry(
                "One Piece",
                this.animeCharacters.stream().filter(
                    (animeCharacter) -> animeCharacter.getAnimeName().equals("One Piece")
                ).collect(toSet())
            ),
            Map.entry(
                "Naruto",
                this.animeCharacters.stream().filter(
                    (animeCharacter) -> animeCharacter.getAnimeName().equals("Naruto")
                ).collect(toSet())
            )
        );

        assertEquals(
            expectedResult,
            Query.groupAnimeCharactersByAnimeName(this.animeCharacters)
        );
    }

    @Test
    void filterAnimeCharactersByAnimeName_returnsListOfFilteredAnimeCharacters() {
        final List<AnimeCharacter> expectedResult = new ArrayList<AnimeCharacter>();
        final String animeName = "One Piece";

        for (AnimeCharacter animeCharacter : this.animeCharacters) {
            if (animeCharacter.getAnimeName().equals(animeName)) {
                expectedResult.add(animeCharacter);
            }
        }

        assertEquals(
            expectedResult,
            Query.filterAnimeCharactersByAnimeName(this.animeCharacters, animeName)
        );
    }

    @Test
    void groupAnimeCharacterNamesByAbility_returnsMapOfAnimeCharacterNamesGroupedByAbility() {
        final Map<String, Set<String>> expectedResult = new HashMap<String, Set<String>>();

        for (AnimeCharacter animeCharacter : this.animeCharacters) {
            for (String ability : animeCharacter.getAbilities()) {
                expectedResult.computeIfAbsent(
                    ability, key -> new HashSet<String>()
                ).add(animeCharacter.getName());
            }
        }

        assertEquals(
            expectedResult,
            Query.groupAnimeCharacterNamesByAbility(this.animeCharacters)
        );
    }
}