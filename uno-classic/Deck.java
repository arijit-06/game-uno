import java.util.*;

public class Deck {
    private List<Card> cards;
    private Random random;
    
    public Deck() {
        this.cards = new ArrayList<>();
        this.random = new Random();
        initializeDeck();
        shuffle();
    }
    
    private void initializeDeck() {
        Card.Color[] colors = {Card.Color.RED, Card.Color.BLUE, Card.Color.GREEN, Card.Color.YELLOW};
        
        // Number cards (0-9, with 0 appearing once and 1-9 appearing twice)
        for (Card.Color color : colors) {
            cards.add(new Card(color, Card.Type.NUMBER, 0));
            for (int i = 1; i <= 9; i++) {
                cards.add(new Card(color, Card.Type.NUMBER, i));
                cards.add(new Card(color, Card.Type.NUMBER, i));
            }
        }
        
        // Action cards (2 of each per color)
        for (Card.Color color : colors) {
            for (int i = 0; i < 2; i++) {
                cards.add(new Card(color, Card.Type.SKIP, -1));
                cards.add(new Card(color, Card.Type.REVERSE, -1));
                cards.add(new Card(color, Card.Type.DRAW_TWO, -1));
            }
        }
        
        // Wild cards (4 of each)
        for (int i = 0; i < 4; i++) {
            cards.add(new Card(Card.Color.WILD, Card.Type.WILD, -1));
            cards.add(new Card(Card.Color.WILD, Card.Type.WILD_DRAW_FOUR, -1));
        }
    }
    
    public void shuffle() {
        Collections.shuffle(cards, random);
    }
    
    public Card drawCard() {
        if (cards.isEmpty()) {
            System.out.println("Deck is empty! Reshuffling...");
            // In a real game, you'd reshuffle the discard pile
            return null;
        }
        return cards.remove(cards.size() - 1);
    }
    
    public boolean isEmpty() {
        return cards.isEmpty();
    }
    
    public int size() {
        return cards.size();
    }
}