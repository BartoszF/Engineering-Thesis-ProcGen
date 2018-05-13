package pl.bartoszf.procgen.Generators.NameGenerators.MarkovChain;

import pl.bartoszf.procgen.Utils.GeneratorUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MarkovChain {
    public static int MIN_LENGTH = 5;
    HashMap<Character, ChainState> states;
    List<Character> firstChars;

    public MarkovChain() {
        states = new HashMap<>();
        firstChars = new ArrayList<>();
    }

    public void analyzeFile(String path) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line = br.readLine();
            while (line != null) {

                line = line.trim();
                line = line + "\n";
                char[] chars = line.toCharArray();
                for (int i = 0; i < chars.length - 1; i++) {
                    Character c = chars[i];
                    c = Character.toLowerCase(c);
                    if (i == 0) {
                        firstChars.add(c);
                    }
                    if (!states.containsKey(c)) {
                        states.put(c, new ChainState(c));
                    }

                    ChainState state = states.get(c);
                    Character next = new Character(chars[i + 1]);
                    state.addCharacter(Character.toLowerCase(next));
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRandom() {
        StringBuilder builder = new StringBuilder();
        Random random = GeneratorUtils.random;

        do {
            Character current = firstChars.get(random.nextInt(firstChars.size()));
            while (current != '\n') {
                builder.append(current);

                ChainState state = states.get(current);
                current = state.getRandom();
            }
        } while (builder.length() < MIN_LENGTH);

        return builder.toString();
    }
}
