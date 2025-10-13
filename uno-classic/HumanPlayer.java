import java.util.*;

public class HumanPlayer extends Player {
    private Scanner scanner;
    
    public HumanPlayer(String name) {
        super(name, false);
        this.scanner = new Scanner(System.in);
    }
    
    @Override
    public Card playCard(Card topCard, UnoGame game) {
        System.out.println("\n" + name + "'s turn!");
        System.out.println("Top card: " + topCard);
        System.out.println("Your cards:");
        
        List<Card> cards = hand.getCards();
        List<Card> playableCards = new ArrayList<>();
        
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
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
        
        Card selectedCard = cards.get(choice - 1);
        if (!selectedCard.canPlayOn(topCard)) {
            System.out.println("Cannot play that card! Drawing a card...");
            return null;
        }
        
        return removeCard(selectedCard);
    }
    
    @Override
    public Card.Color chooseColor() {
        System.out.println("Choose color: 1=RED, 2=BLUE, 3=GREEN, 4=YELLOW");
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1: return Card.Color.RED;
            case 2: return Card.Color.BLUE;
            case 3: return Card.Color.GREEN;
            case 4: return Card.Color.YELLOW;
            default: return Card.Color.RED;
        }
    }
}