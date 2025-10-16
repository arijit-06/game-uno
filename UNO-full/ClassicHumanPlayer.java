import java.util.*;

public class ClassicHumanPlayer extends BasePlayer<ClassicCard> {
    private Scanner scanner;
    
    public ClassicHumanPlayer(String name) {
        super(name, false);
        this.scanner = new Scanner(System.in);
    }
    
    @Override
    public ClassicCard playCard(ClassicCard topCard, BaseGame<ClassicCard> game) {
        System.out.println("\n" + name + "'s turn!");
        System.out.println("Top card: " + topCard);
        System.out.println("Your cards:");
        
        List<ClassicCard> playableCards = new ArrayList<>();
        
        for (int i = 0; i < hand.size(); i++) {
            ClassicCard card = hand.get(i);
            boolean canPlay = card.canPlayOn(topCard);
            System.out.println((i + 1) + ". " + card + (canPlay ? " [PLAYABLE]" : ""));
            if (canPlay) playableCards.add(card);
        }
        
        if (playableCards.isEmpty()) {
            System.out.println("No playable cards! Drawing a card...");
            return null;
        }
        
        System.out.print("Choose card to play (1-" + hand.size() + ") or 0 to draw: ");
        int choice = scanner.nextInt();
        
        if (choice == 0) return null;
        
        if (choice < 1 || choice > hand.size()) {
            System.out.println("Invalid choice! Drawing a card...");
            return null;
        }
        
        ClassicCard selectedCard = hand.get(choice - 1);
        if (!selectedCard.canPlayOn(topCard)) {
            System.out.println("Cannot play that card! Drawing a card...");
            return null;
        }
        
        return removeCard(selectedCard);
    }
    
    @Override
    public BaseCard.Color chooseColor() {
        System.out.println("Choose color: 1=RED, 2=BLUE, 3=GREEN, 4=YELLOW");
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1: return BaseCard.Color.RED;
            case 2: return BaseCard.Color.BLUE;
            case 3: return BaseCard.Color.GREEN;
            case 4: return BaseCard.Color.YELLOW;
            default: return BaseCard.Color.RED;
        }
    }
}