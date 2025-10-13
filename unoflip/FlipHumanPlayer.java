import java.util.*;

public class FlipHumanPlayer extends FlipPlayer {
    private Scanner scanner;
    
    public FlipHumanPlayer(String name) {
        super(name, false);
        this.scanner = new Scanner(System.in);
    }
    
    @Override
    public FlipCard playCard(FlipCard topCard, UnoFlipGame game) {
        System.out.println("\n" + name + "'s turn!");
        System.out.println("Top card: " + topCard);
        System.out.println("Current side: " + topCard.getCurrentSide());
        System.out.println("Your cards:");
        
        List<FlipCard> cards = hand.getCards();
        List<FlipCard> playableCards = new ArrayList<>();
        
        for (int i = 0; i < cards.size(); i++) {
            FlipCard card = cards.get(i);
            boolean canPlay = card.canPlayOn(topCard);
            System.out.println((i + 1) + ". " + card + (canPlay ? " [PLAYABLE]" : ""));
            if (canPlay) playableCards.add(card);
        }
        
        if (playableCards.isEmpty()) {
            System.out.println("No playable cards! Drawing a card...");
            return null;
        }
        
        System.out.print("Choose card to play (1-" + cards.size() + ") or 0 to draw: ");
        int choice = scanner.nextInt();
        
        if (choice == 0) return null;
        
        if (choice < 1 || choice > cards.size()) {
            System.out.println("Invalid choice! Drawing a card...");
            return null;
        }
        
        FlipCard selectedCard = cards.get(choice - 1);
        if (!selectedCard.canPlayOn(topCard)) {
            System.out.println("Cannot play that card! Drawing a card...");
            return null;
        }
        
        return removeCard(selectedCard);
    }
    
    @Override
    public FlipCard.LightColor chooseLightColor() {
        System.out.println("Choose LIGHT color: 1=TEAL, 2=PINK, 3=PURPLE, 4=ORANGE");
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1: return FlipCard.LightColor.TEAL;
            case 2: return FlipCard.LightColor.PINK;
            case 3: return FlipCard.LightColor.PURPLE;
            case 4: return FlipCard.LightColor.ORANGE;
            default: return FlipCard.LightColor.TEAL;
        }
    }
    
    @Override
    public FlipCard.DarkColor chooseDarkColor() {
        System.out.println("Choose DARK color: 1=BLUE, 2=GREEN, 3=RED, 4=YELLOW");
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1: return FlipCard.DarkColor.BLUE;
            case 2: return FlipCard.DarkColor.GREEN;
            case 3: return FlipCard.DarkColor.RED;
            case 4: return FlipCard.DarkColor.YELLOW;
            default: return FlipCard.DarkColor.BLUE;
        }
    }
}