package pl.bartoszf.procgen.Generators.NameGenerators.MarkovChain;

import pl.bartoszf.procgen.Utils.GeneratorUtils;
import pl.bartoszf.procgen.Utils.MapUtil;

import java.util.HashMap;
import java.util.Map;

public class ChainState {
    Character character;
    Map<Character, Integer> possibilities;

    public ChainState(Character character) {
        this.character = character;
        possibilities = new HashMap<>();
    }

    public void addCharacter(Character c) {
        if (!possibilities.containsKey(c)) {
            possibilities.put(c, 1);
        } else {
            Integer i = possibilities.get(c);
            possibilities.put(c, ++i);
        }
    }

    public Character getRandom() {
        int chanceTotal = 0;

        Map<Character, Integer> sorted = MapUtil.sortByValueReversed(possibilities);

        for (Character name : sorted.keySet()) chanceTotal += sorted.get(name);

        int choice = GeneratorUtils.random.nextInt(chanceTotal), subtotal = 0;

        for (Character name : sorted.keySet()) {
            subtotal += sorted.get(name);
            if (choice < subtotal) return name;
        }

        Object[] chars = sorted.keySet().toArray();
        return (Character) chars[GeneratorUtils.random.nextInt(chars.length)];
    }
}
