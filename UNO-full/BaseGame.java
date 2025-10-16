import java.util.*;

public abstract class BaseGame<T extends BaseCard> {
    protected List<BasePlayer<T>> players;
    protected List<T> deck;
    protected T topCard;
    protected int currentPlayerIndex;
    protected boolean clockwise;
    protected Scanner scanner;
    
    public BaseGame() {
        this.players = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.clockwise = true;
        this.scanner = new Scanner(System.in);
    }
    
    protected abstract void initializeDeck();
    protected abstract BasePlayer<T> createHumanPlayer(String name);
    protected abstract BasePlayer<T> createComputerPlayer(String name);
    protected abstract void handleSpecialCard(T card, BasePlayer<T> currentPlayer);
    
    public void setupGame() {
        System.out.println("=== UNO GAME SETUP ===");
        System.out.print("Number of players (2-10): ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();
        
        if (numPlayers < 2 || numPlayers > 10) {
            System.out.println("Invalid number of players! Setting to 4.");
            numPlayers = 4;
        }
        
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Player " + (i + 1) + " - Enter name (or 'computer' for AI): ");
            String name = scanner.nextLine();
            
            if (name.equalsIgnoreCase("computer")) {
                players.add(createComputerPlayer("Computer" + (i + 1)));
            } else {
                players.add(createHumanPlayer(name));
            }
        }
        
        initializeDeck();
        shuffleDeck();
        
        // Deal initial cards
        for (int i = 0; i < 7; i++) {
            for (BasePlayer<T> player : players) {
                player.addCard(drawCard());
            }
        }
        
        // Set initial top card (must be a number)
        do {
            topCard = drawCard();
        } while (topCard.getType() != BaseCard.Type.NUMBER);
        
        System.out.println("\nGame started! Initial top card: " + topCard);
    }
    
    public void playGame() {
        setupGame();
        
        while (!isGameOver()) {
            BasePlayer<T> currentPlayer = players.get(currentPlayerIndex);
            System.out.println("\n" + "=".repeat(50));
            System.out.println("Current player: " + currentPlayer);
            
            T playedCard = currentPlayer.playCard(topCard, this);
            
            if (playedCard != null) {
                topCard = playedCard;
                System.out.println("Card played: " + playedCard);
                
                handleSpecialCard(playedCard, currentPlayer);
                
                if (currentPlayer.hasWon()) {
                    System.out.println("\n🎉 " + currentPlayer.getName() + " wins! 🎉");
                    return;
                }
            } else {
                T drawnCard = drawCard();
                if (drawnCard != null) {
                    currentPlayer.addCard(drawnCard);
                    System.out.println(currentPlayer.getName() + " drew a card");
                }
            }
            
            nextPlayer();
        }
    }
    
    protected void shuffleDeck() {
        Collections.shuffle(deck);
    }
    
    protected T drawCard() {
        if (deck.isEmpty()) {
            System.out.println("Deck is empty!");
            return null;
        }
        return deck.remove(deck.size() - 1);
    }
    
    protected void drawCards(BasePlayer<T> player, int count) {
        for (int i = 0; i < count; i++) {
            T drawnCard = drawCard();
            if (drawnCard != null) {
                player.addCard(drawnCard);
            }
        }
    }
    
    protected void nextPlayer() {
        if (clockwise) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
    }
    
    protected boolean isGameOver() {
        return players.stream().anyMatch(BasePlayer::hasWon) || deck.isEmpty();
    }
    
    public T getTopCard() { return topCard; }
}