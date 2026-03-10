package io.github.isguliyev.examples.mordor;

import io.github.isguliyev.examples.mordor.food.Food;
import io.github.isguliyev.examples.mordor.food.Cram;
import io.github.isguliyev.examples.mordor.food.Lembas;
import io.github.isguliyev.examples.mordor.food.Apple;
import io.github.isguliyev.examples.mordor.food.Melon;
import io.github.isguliyev.examples.mordor.food.HoneyCake;
import io.github.isguliyev.examples.mordor.food.Mushroom;
import io.github.isguliyev.examples.mordor.food.OtherFood;

public class Main {
    public static void main(String[] args) {
        Character character = new Character("Gandalf");

        character.eat(
            new Food[] {
                new Cram(),
                new Lembas(),
                new Apple(),
                new Melon(),
                new HoneyCake(),
                new Mushroom(),
                new OtherFood()
            }
        );

        System.out.println(character);
    }
}