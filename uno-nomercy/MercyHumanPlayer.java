import java.util.*;

public class MercyHumanPlayer extends MercyPlayer {
    private Scanner scanner;
    
    public MercyHumanPlayer(String name) {
        super(name, false);
        this.scanner = new Scanner(System.in);
    }
    
    @Override
    public MercyCard playCard(MercyCard topCard, UnoMercyGame game) {
        System.out.println("\n" + name + "'s turn!");
        System.out.println("Top card: " + topCard);
        System.out.println("Your cards:");
        
        List<MercyCard> cards = hand.getCards();
        List<MercyCard> playableCards = new ArrayList<>();
        
        for (int i = 0; i < cards.size(); i++) {
            MercyCard card = cards.get(i);
            boolean canPlay = card.canPlayOn(topCard);
            String stackInfo = card.isStackingCard() ? " [STACKABLE]" : "";
            System.out.println((i + 1) + ". " + card + (canPlay ? " [PLAYABLE]" : "") + stackInfo);
            if (canPlay) playableCards.add(card);
        }
        
        if (playableCards.isEmpty()) {
            System.out.println("No playable cards! Drawing a card...");
            return null;
        }
        
        // Check if player should call UNO
        if (cards.size() == 2 && !hasCalledUno) {
            System.out.print("You have 2 cards! Call UNO? (y/n): ");
            String unoChoice = scanner.next();
            if (unoChoice.equalsIgnoreCase("y")) {
                callUno();
                System.out.println("UNO called!");
            }
        }
        
        System.out.print("Choose card to play (1-" + cards.size() + ") or 0 to draw: ");
        int choice = scanner.nextInt();
        
        if (choice == 0) return null;
        
        if (choice < 1 || choice > cards.size()) {
            System.out.println("Invalid choice! Drawing a card...");
            return null;
        }
        
        MercyCard selectedCard = cards.get(choice - 1);
        if (!selectedCard.canPlayOn(topCard)) {
            System.out.println("Cannot play that card! Drawing a card...");
            return null;
        }
        
        return removeCard(selectedCard);
    }
    
    @Override
    public MercyCard.Color chooseColor() {
        System.out.println("Choose color: 1=RED, 2=BLUE, 3=GREEN, 4=YELLOW");
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1: return MercyCard.Color.RED;
            case 2: return MercyCard.Color.BLUE;
            case 3: return MercyCard.Color.GREEN;
            case 4: return MercyCard.Color.YELLOW;
            default: return MercyCard.Color.RED;
        }
    }
    
    @Override
    public boolean wantsToStack(MercyCard topCard) {
        MercyCard stackCard = findStackingCard(topCard);
        if (stackCard == null) return false;
        
        System.out.println("You can stack " + stackCard + " on " + topCard + "!");
        System.out.print("Do you want to stack? (y/n): ");
        String choice = scanner.next();
        return choice.equalsIgnoreCase("y");
    }
}