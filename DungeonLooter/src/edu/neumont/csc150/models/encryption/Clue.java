package edu.neumont.csc150.models.encryption;

import edu.neumont.csc150.models.items.Item;

public class Clue extends Item {

    public Clue() {
        super("Tattered Note", "A crumpled note found near the scroll. The handwriting is hurried.", 0);
    }

    public String readClue() {
        return "=== TATTERED NOTE ===\n" +
                "To whoever finds this scroll,\n\n" +
                "The prisoner encoded his message before we could stop him.\n" +
                "He used three of our own ciphers against us, one after another.\n\n" +
                "First, he used CAESAR's cipher — the one that shifts every\n" +
                "character forward by III. Then he ran it through the CUTTER,\n" +
                "that blasted tool that splits a message in half and swaps\n" +
                "the two pieces. Finally, he scrambled the VOWELS — each vowel\n" +
                "replaced by the next one in line (A->E, E->I, I->O, O->U, U->A),\n" +
                "uppercase and lowercase alike.\n\n" +
                "If you can undo what he did, the message will tell you how\n" +
                "to prove you've cracked it.\n\n" +
                "- The Warden";
    }
}
