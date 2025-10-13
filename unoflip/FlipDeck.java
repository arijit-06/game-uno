import java.util.*;

public class FlipDeck {
    private List<FlipCard> cards;
    private Random random;
    
    public FlipDeck() {
        this.cards = new ArrayList<>();
        this.random = new Random();
        initializeDeck();
        shuffle();
    }
    
    private void initializeDeck() {
        // Light side colors and corresponding dark side colors
        FlipCard.LightColor[] lightColors = {FlipCard.LightColor.TEAL, FlipCard.LightColor.PINK, 
                                           FlipCard.LightColor.PURPLE, FlipCard.LightColor.ORANGE};
        FlipCard.DarkColor[] darkColors = {FlipCard.DarkColor.BLUE, FlipCard.DarkColor.GREEN, 
                                         FlipCard.DarkColor.RED, FlipCard.DarkColor.YELLOW};
        
        // Number cards (0-9, with 0 appearing once and 1-9 appearing twice per color)
        for (int i = 0; i < lightColors.length; i++) {
            // One 0 card per color
            cards.add(new FlipCard(lightColors[i], FlipCard.LightType.NUMBER, 0,
                                 darkColors[i], FlipCard.DarkType.NUMBER, 0));
            
            // Two of each 1-9 per color
            for (int num = 1; num <= 9; num++) {
                for (int copy = 0; copy < 2; copy++) {
                    cards.add(new FlipCard(lightColors[i], FlipCard.LightType.NUMBER, num,
                                         darkColors[i], FlipCard.DarkType.NUMBER, num));
                }
            }
        }
        
        // Action cards (2 of each per color)
        for (int i = 0; i < lightColors.length; i++) {
            for (int copy = 0; copy < 2; copy++) {
                // Reverse
                cards.add(new FlipCard(lightColors[i], FlipCard.LightType.REVERSE, -1,
                                     darkColors[i], FlipCard.DarkType.REVERSE, -1));
                
                // Skip / Skip Everyone
                cards.add(new FlipCard(lightColors[i], FlipCard.LightType.SKIP, -1,
                                     darkColors[i], FlipCard.DarkType.SKIP_EVERYONE, -1));
                
                // Draw Two / Draw Five
                cards.add(new FlipCard(lightColors[i], FlipCard.LightType.DRAW_TWO, -1,
                                     darkColors[i], FlipCard.DarkType.DRAW_FIVE, -1));
                
                // Flip cards
                cards.add(new FlipCard(lightColors[i], FlipCard.LightType.FLIP, -1,
                                     darkColors[i], FlipCard.DarkType.FLIP, -1));
            }
        }
        
        // Wild cards (4 of each type)
        for (int i = 0; i < 4; i++) {
            // Wild / Wild
            cards.add(new FlipCard(FlipCard.LightColor.WILD, FlipCard.LightType.WILD, -1,
                                 FlipCard.DarkColor.WILD, FlipCard.DarkType.WILD, -1));
            
            // Wild Draw Two / Wild Draw Color
            cards.add(new FlipCard(FlipCard.LightColor.WILD, FlipCard.LightType.WILD_DRAW_TWO, -1,
                                 FlipCard.DarkColor.WILD, FlipCard.DarkType.WILD_DRAW_COLOR, -1));
        }
    }
    
    public void shuffle() {
        Collections.shuffle(cards, random);
    }
    
    public FlipCard drawCard() {
        if (cards.isEmpty()) {
            System.out.println("Deck is empty! Reshuffling...");
            return null;
        }
        return cards.remove(cards.size() - 1);
    }
    
    public void flipAllCards() {
        for (FlipCard card : cards) {
            card.flip();
        }
    }
    
    public boolean isEmpty() {
        return cards.isEmpty();
    }
    
    public int size() {
        return cards.size();
    }
}