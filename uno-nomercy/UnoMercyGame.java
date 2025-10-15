import java.util.*;

public class UnoMercyGame {
    private List<MercyPlayer> players;
    private MercyDeck deck;
    private MercyCard topCard;
    private int currentPlayerIndex;
    private boolean clockwise;
    private Scanner scanner;
    private int stackedDrawAmount;
    private boolean stackingInProgress;
    
    public UnoMercyGame() {
        this.players = new ArrayList<>();
        this.deck = new MercyDeck();
        this.currentPlayerIndex = 0;
        this.clockwise = true;
        this.scanner = new Scanner(System.in);
        this.stackedDrawAmount = 0;
        this.stackingInProgress = false;
    }
    
    public void setupGame() {
        System.out.println("=== UNO NO MERCY GAME SETUP ===");
        System.out.println("WARNING: This is the most brutal UNO variant!");
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
                players.add(new MercyComputerPlayer("Computer" + (i + 1)));
            } else {
                players.add(new MercyHumanPlayer(name));
            }
        }
        
        // Deal initial cards (7 each)
        for (int i = 0; i < 7; i++) {
            for (MercyPlayer player : players) {
                player.addCard(deck.drawCard());
            }
        }
        
        // Set initial top card (must be a number)
        do {
            topCard = deck.drawCard();
        } while (topCard.getType() != MercyCard.Type.NUMBER);
        
        System.out.println("\nNO MERCY BEGINS! Initial top card: " + topCard);
        System.out.println("Remember: NO MERCY means NO FORGIVENESS!");
    }
    
    public void playGame() {
        setupGame();
        
        while (!isGameOver()) {
            MercyPlayer currentPlayer = players.get(currentPlayerIndex);
            
            // Decrement mercy protection
            currentPlayer.decrementMercyProtection();
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("Current player: " + currentPlayer);
            System.out.println("Stacked draw amount: " + stackedDrawAmount);
            
            // Handle stacking situation
            if (stackingInProgress) {
                handleStackingTurn(currentPlayer);
            } else {
                handleNormalTurn(currentPlayer);
            }
            
            // Check for win
            if (currentPlayer.hasWon()) {
                System.out.println("\n" + currentPlayer.getName() + " SHOWS NO MERCY AND WINS!");
                return;
            }
            
            nextPlayer();
        }
    }
    
    private void handleNormalTurn(MercyPlayer currentPlayer) {
        MercyCard playedCard = currentPlayer.playCard(topCard, this);
        
        if (playedCard != null) {
            topCard = playedCard;
            System.out.println("Card played: " + playedCard);
            
            // Check UNO violation
            if (currentPlayer.getHandSize() == 1 && !currentPlayer.hasCalledUno()) {
                System.out.println("UNO VIOLATION! " + currentPlayer.getName() + " draws 4 cards!");
                drawCards(currentPlayer, 4);
                currentPlayer.resetUnoCall();
            }
            
            // Handle special cards
            handleSpecialCard(playedCard, currentPlayer);
        } else {
            // Player draws a card
            MercyCard drawnCard = deck.drawCard();
            if (drawnCard != null) {
                currentPlayer.addCard(drawnCard);
                System.out.println(currentPlayer.getName() + " drew a card");
            }
        }
    }
    
    private void handleStackingTurn(MercyPlayer currentPlayer) {
        if (currentPlayer.wantsToStack(topCard)) {
            MercyCard stackCard = currentPlayer.findStackingCard(topCard);
            if (stackCard != null) {
                currentPlayer.removeCard(stackCard);
                topCard = stackCard;
                stackedDrawAmount += stackCard.getDrawAmount();
                System.out.println(currentPlayer.getName() + " stacks " + stackCard + 
                                 "! Total draw: " + stackedDrawAmount);
                return; // Continue stacking
            }
        }
        
        // Player can't or won't stack - they draw all stacked cards
        System.out.println(currentPlayer.getName() + " draws " + stackedDrawAmount + " cards! NO MERCY!");
        drawCards(currentPlayer, stackedDrawAmount);
        stackedDrawAmount = 0;
        stackingInProgress = false;
    }
    
    private void handleSpecialCard(MercyCard card, MercyPlayer currentPlayer) {
        switch (card.getType()) {
            case SKIP:
                System.out.println("Next player is skipped!");
                nextPlayer();
                break;
                
            case SKIP_EVERYONE:
                System.out.println("EVERYONE ELSE IS SKIPPED! " + currentPlayer.getName() + " plays again!");
                return; // Don't advance to next player
                
            case REVERSE:
                clockwise = !clockwise;
                System.out.println("Direction reversed!");
                break;
                
            case DRAW_TWO:
            case STACKING_DRAW_TWO:
                startStacking(2);
                break;
                
            case WILD_DRAW_FOUR:
            case STACKING_DRAW_FOUR:
                MercyCard.Color newColor4 = currentPlayer.chooseColor();
                topCard.setColor(newColor4);
                System.out.println("Color changed to: " + newColor4);
                startStacking(4);
                break;
                
            case DRAW_TEN:
                startStacking(10);
                break;
                
            case WILD_DRAW_TEN:
                MercyCard.Color newColor10 = currentPlayer.chooseColor();
                topCard.setColor(newColor10);
                System.out.println("Color changed to: " + newColor10);
                startStacking(10);
                break;
                
            case WILD:
                MercyCard.Color newColor = currentPlayer.chooseColor();
                topCard.setColor(newColor);
                System.out.println("Color changed to: " + newColor);
                break;
                
            case DISCARD_ALL:
                handleDiscardAll(currentPlayer);
                break;
                
            case MERCY_RULE:
                handleMercyRule(currentPlayer);
                break;
        }
    }
    
    private void startStacking(int drawAmount) {
        stackedDrawAmount = drawAmount;
        stackingInProgress = true;
        System.out.println("STACKING STARTED! Next player must stack or draw " + drawAmount + " cards!");
    }
    
    private void handleDiscardAll(MercyPlayer currentPlayer) {
        MercyCard.Color chosenColor = currentPlayer.chooseColor();
        System.out.println("DISCARD ALL " + chosenColor + " CARDS!");
        
        for (MercyPlayer player : players) {
            if (player != currentPlayer) {
                List<MercyCard> discarded = player.discardAllOfColor(chosenColor);
                if (!discarded.isEmpty()) {
                    System.out.println(player.getName() + " discards " + discarded.size() + 
                                     " " + chosenColor + " cards!");
                    for (MercyCard card : discarded) {
                        deck.addCard(card);
                    }
                }
            }
        }
    }
    
    private void handleMercyRule(MercyPlayer currentPlayer) {
        System.out.println("MERCY RULE ACTIVATED!");
        MercyCard.Color chosenColor = currentPlayer.chooseColor();
        topCard.setColor(chosenColor);
        
        // Find player with most cards
        MercyPlayer targetPlayer = null;
        int maxCards = 0;
        for (MercyPlayer player : players) {
            if (player != currentPlayer && player.getHandSize() > maxCards && !player.hasMercyProtection()) {
                maxCards = player.getHandSize();
                targetPlayer = player;
            }
        }
        
        if (targetPlayer != null) {
            System.out.println("NO MERCY FOR " + targetPlayer.getName() + "! They draw 10 cards!");
            drawCards(targetPlayer, 10);
            currentPlayer.setMercyProtection(3); // Protected for 3 turns
            System.out.println(currentPlayer.getName() + " gains Mercy Rule protection!");
        }
    }
    
    private void drawCards(MercyPlayer player, int count) {
        for (int i = 0; i < count; i++) {
            MercyCard drawnCard = deck.drawCard();
            if (drawnCard != null) {
                player.addCard(drawnCard);
            }
        }
    }
    
    private void nextPlayer() {
        if (clockwise) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
    }
    
    private boolean isGameOver() {
        return players.stream().anyMatch(MercyPlayer::hasWon) || deck.isEmpty();
    }
    
    public MercyCard getTopCard() {
        return topCard;
    }
    
    public static void main(String[] args) {
        UnoMercyGame game = new UnoMercyGame();
        game.playGame();
    }
}