import java.util.*;

public class MercyDeck {
    private List<MercyCard> cards;
    private Random random;
    
    public MercyDeck() {
        this.cards = new ArrayList<>();
        this.random = new Random();
        initializeDeck();
        shuffle();
    }
    
    private void initializeDeck() {
        MercyCard.Color[] colors = {MercyCard.Color.RED, MercyCard.Color.BLUE, 
                                   MercyCard.Color.GREEN, MercyCard.Color.YELLOW};
        
        // Standard UNO cards
        for (MercyCard.Color color : colors) {
            // One 0 per color
            cards.add(new MercyCard(color, MercyCard.Type.NUMBER, 0));
            
            // Two of each 1-9 per color
            for (int i = 1; i <= 9; i++) {
                cards.add(new MercyCard(color, MercyCard.Type.NUMBER, i));
                cards.add(new MercyCard(color, MercyCard.Type.NUMBER, i));
            }
            
            // Two of each action card per color
            for (int i = 0; i < 2; i++) {
                cards.add(new MercyCard(color, MercyCard.Type.SKIP, -1));
                cards.add(new MercyCard(color, MercyCard.Type.REVERSE, -1));
                cards.add(new MercyCard(color, MercyCard.Type.DRAW_TWO, -1));
            }
        }
        
        // Standard Wild cards (4 each)
        for (int i = 0; i < 4; i++) {
            cards.add(new MercyCard(MercyCard.Color.WILD, MercyCard.Type.WILD, -1));
            cards.add(new MercyCard(MercyCard.Color.WILD, MercyCard.Type.WILD_DRAW_FOUR, -1));
        }
        
        // NO MERCY EXCLUSIVE CARDS
        
        // Skip Everyone (2 per color)
        for (MercyCard.Color color : colors) {
            for (int i = 0; i < 2; i++) {
                cards.add(new MercyCard(color, MercyCard.Type.SKIP_EVERYONE, -1));
            }
        }
        
        // Draw 10 cards (1 per color)
        for (MercyCard.Color color : colors) {
            cards.add(new MercyCard(color, MercyCard.Type.DRAW_TEN, -1));
        }
        
        // Discard All (1 per color)
        for (MercyCard.Color color : colors) {
            cards.add(new MercyCard(color, MercyCard.Type.DISCARD_ALL, -1));
        }
        
        // Stacking Draw cards (2 per color)
        for (MercyCard.Color color : colors) {
            for (int i = 0; i < 2; i++) {
                cards.add(new MercyCard(color, MercyCard.Type.STACKING_DRAW_TWO, -1));
            }
        }
        
        // Wild No Mercy cards (4 each)
        for (int i = 0; i < 4; i++) {
            cards.add(new MercyCard(MercyCard.Color.WILD, MercyCard.Type.WILD_DRAW_TEN, -1));
            cards.add(new MercyCard(MercyCard.Color.WILD, MercyCard.Type.MERCY_RULE, -1));
            cards.add(new MercyCard(MercyCard.Color.WILD, MercyCard.Type.STACKING_DRAW_FOUR, -1));
        }
    }
    
    public void shuffle() {
        Collections.shuffle(cards, random);
    }
    
    public MercyCard drawCard() {
        if (cards.isEmpty()) {
            System.out.println("Deck is empty! No mercy for anyone!");
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
    
    public void addCard(MercyCard card) {
        cards.add(0, card); // Add to bottom of deck
    }
}