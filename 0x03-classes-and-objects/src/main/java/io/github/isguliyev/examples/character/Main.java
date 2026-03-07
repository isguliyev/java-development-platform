package io.github.isguliyev.examples.character;

public class Main {
    public static void main(String[] args) {
        GameCharacter cj = new GameCharacter("Carl Johnson", 100);
        GameCharacter sweet = new GameCharacter("Sweet Johnson", 100);
        GameCharacter smoke = new GameCharacter("Big Smoke", 100);
        GameCharacter ryder = new GameCharacter("Ryder", 100);

        System.out.println(cj);
        System.out.println(sweet);
        System.out.println(smoke);
        System.out.println(ryder);
    }
}