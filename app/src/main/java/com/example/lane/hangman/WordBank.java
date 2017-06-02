package com.example.lane.hangman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by lane on 5/17/17.
 */

public class WordBank {
    private final static Random RANDOM = new Random();
    private static Map<String, String> wordbank;
    private WordBank(){}
    private static void setUpWordBank() {
        wordbank = new HashMap<>();
        wordbank.put("abruptly", "suddenly");
        wordbank.put("buckaroo", "cowboy");
        wordbank.put("jackpot", "a lot of money");
        wordbank.put("woozy", "dizzy");
        wordbank.put("absurd", "illogical");
        wordbank.put("abyss", "pit");
        wordbank.put("affix", "attach");
        wordbank.put("askew", "slanted");
        wordbank.put("avenue", "road in a town");
        wordbank.put("awkward", "embarrassing");
        wordbank.put("azure", "blue");
        wordbank.put("axiom", "general truth");
        wordbank.put("bagpipes", "wind instrument");
        wordbank.put("bandwagon", "a trending activity");
        wordbank.put("banjo", "instrument");
        wordbank.put("bayou", "outlet of a lake");
        wordbank.put("beekeeper", "bound to get stung");
        wordbank.put("bikini", "swimwear");
        wordbank.put("blitz", "a sudden effort");
        wordbank.put("blizzard", "snowstorm");
        wordbank.put("boggle", "be astonished");
        wordbank.put("bookworm", "best friend is a book");
        wordbank.put("boxcar", "carries freight");
        wordbank.put("boxful", "capacity full");
        wordbank.put("buffalo", "wild ox");
        wordbank.put("buffoon", "someone acting stupid");
        wordbank.put("buzzard", "gray hawk");
        wordbank.put("buzzing", "humming");
        wordbank.put("buzzwords", "word or phrase");
        wordbank.put("cobweb", "a spider's old masterpiece");
        wordbank.put("cockiness", "very arrogant");
        wordbank.put("croquet", "sport");
        wordbank.put("crypt", "vault");
        wordbank.put("curacao", "caribbean island");
        wordbank.put("cycle", "complete series");
        wordbank.put("daiquiri", "family of cocktails");
        wordbank.put("dirndl", "dress worn in austria");
        wordbank.put("disavow", "reject");
        wordbank.put("dizzying", "feel unsteady");
        wordbank.put("duplex", "two households");
        wordbank.put("dwarves", "short human-like being");
        wordbank.put("embezzle", "steal");
        wordbank.put("equip", "provide");
        wordbank.put("espionage", "spying");
        wordbank.put("exodus", "hebrew bible");
        wordbank.put("faking", "pretend");
        wordbank.put("fishhook", "used to catch fish");
        wordbank.put("fix", "to restore to proper");
        wordbank.put("flapjack", "a pancake");
        wordbank.put("flopping", "soccer players do this");
        wordbank.put("fluffiness", "soft and light");
        wordbank.put("flyby", "a flight past a point");
        wordbank.put("foxglove", "type of plant");
        wordbank.put("frazzled", "worn out");
        wordbank.put("frizzled", "fry until crisp");
        wordbank.put("fuchsia", "type of plant or color");
        wordbank.put("funny", "comedians are this");
        wordbank.put("gabby", "talking too much");
        wordbank.put("galaxy", "earth lives inside of one");
        wordbank.put("galvanize", "excite");
        wordbank.put("gazebo", "perfect for backyard");
        wordbank.put("gizmo", "an unknown gadget");
        wordbank.put("glowworm", "type of worm");
        wordbank.put("glyph", "a hieroglyphic");
        wordbank.put("gnarly", "beyond radical");
        wordbank.put("gossip", "casual conversation");
        wordbank.put("grogginess", "dazed and weakened");
        wordbank.put("haiku", "type of poetry");
        wordbank.put("haphazard", "disorganized");
        wordbank.put("hyphen", "punctuation mark");
        wordbank.put("iatrogenic", "illness caused by treatment");
        wordbank.put("injury", "a cut");
        wordbank.put("icebox", "another name for cooler");
        wordbank.put("ivory", "material of old piano keys");
        wordbank.put("ivy", "plant");
        wordbank.put("jawbreaker", "candy");
        wordbank.put("jaywalk", "get a ticket for doing it");
        wordbank.put("jelly", "goes on a sandwich");
        wordbank.put("jigsaw", "a puzzle");
        wordbank.put("jinx", "a superstition");
        wordbank.put("jiujitsu", "a martial art");
        wordbank.put("jockey", "someone who rides horses");
        wordbank.put("jogging", "an exercise");
        wordbank.put("joking", "playing around");
        wordbank.put("jovial", "friendly");
        wordbank.put("microwave", "an appliance");
        wordbank.put("snazzy", "stylish");
        wordbank.put("khaki", "a fabric");
        wordbank.put("kilobyte", "unit of memory");
        wordbank.put("klutz", "clumsy");
        wordbank.put("kiwifruit", "a fruit");
        wordbank.put("jukebox", "plays music");
        wordbank.put("vodka", "liquor");
        wordbank.put("whiskey", "liquor");
        wordbank.put("peekaboo", "form of play (infant)");
        wordbank.put("numbskull", "stupid person");

    }
    public static void createWordBank(){
        if(wordbank == null) {
            setUpWordBank();
        }
    }

    public static String getRandomWord(){
        ArrayList<String> keys = new ArrayList<>(wordbank.keySet());
        int SIZE = keys.size();
        return keys.get(RANDOM.nextInt(SIZE));
    }

    public static String getHint(String word){
        return wordbank.get(word);
    }




}
