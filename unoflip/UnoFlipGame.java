import java.util.*;

public class UnoFlipGame {
    private List<FlipPlayer> players;
    private FlipDeck deck;
    private FlipCard topCard;
    private int currentPlayerIndex;
    private boolean clockwise;
    private Scanner scanner;
    private FlipCard.Side currentSide;
    
    public UnoFlipGame() {
        this.players = new ArrayList<>();
        this.deck = new FlipDeck();
        this.currentPlayerIndex = 0;
        this.clockwise = true;
        this.scanner = new Scanner(System.in);
        this.currentSide = FlipCard.Side.LIGHT;
    }
    
    public void setupGame() {
        System.out.println("=== UNO FLIP GAME SETUP ===");
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
                players.add(new FlipComputerPlayer("Computer" + (i + 1)));
            } else {
                players.add(new FlipHumanPlayer(name));
            }
        }
        
        // Deal initial cards (Light side up)
        for (int i = 0; i < 7; i++) {
            for (FlipPlayer player : players) {
                player.addCard(deck.drawCard());
            }
        }
        
        // Set initial top card (must be Light side, no special cards)
        do {
            topCard = deck.drawCard();
        } while (topCard.getLightType() != FlipCard.LightType.NUMBER);
        
        System.out.println("\n🌟 Game started on LIGHT SIDE! 🌟");
        System.out.println("Initial top card: " + topCard);
    }
    
    public void playGame() {
        setupGame();
        
        while (!isGameOver()) {
            FlipPlayer currentPlayer = players.get(currentPlayerIndex);
            System.out.println("\n" + "=".repeat(60));
            System.out.println("Current side: " + currentSide + " | Current player: " + currentPlayer);
            
            FlipCard playedCard = currentPlayer.playCard(topCard, this);
            
            if (playedCard != null) {
                topCard = playedCard;
                System.out.println("Card played: " + playedCard);
                
                // Handle special cards
                handleSpecialCard(playedCard, currentPlayer);
                
                // Check for win
                if (currentPlayer.hasWon()) {
                    System.out.println("\n🎉 " + currentPlayer.getName() + " wins UNO FLIP! 🎉");
                    return;
                }
            } else {
                // Player draws a card
                FlipCard drawnCard = deck.drawCard();
                if (drawnCard != null) {
                    currentPlayer.addCard(drawnCard);
                    System.out.println(currentPlayer.getName() + " drew a card");
                }
            }
            
            nextPlayer();
        }
    }
    
    private void handleSpecialCard(FlipCard card, FlipPlayer currentPlayer) {
        if (currentSide == FlipCard.Side.LIGHT) {
            handleLightSideCard(card, currentPlayer);
        } else {
            handleDarkSideCard(card, currentPlayer);
        }
    }
    
    private void handleLightSideCard(FlipCard card, FlipPlayer currentPlayer) {
        switch (card.getLightType()) {
            case SKIP:
                System.out.println("💨 Next player is skipped!");
                nextPlayer();
                break;
                
            case REVERSE:
                clockwise = !clockwise;
                System.out.println("🔄 Direction reversed!");
                break;
                
            case DRAW_TWO:
                nextPlayer();
                FlipPlayer nextPlayer = players.get(currentPlayerIndex);
                drawCards(nextPlayer, 2);
                System.out.println("📥 " + nextPlayer.getName() + " draws 2 cards and is skipped!");
                break;
                
            case FLIP:
                System.out.println("🌑 FLIPPING TO DARK SIDE! 🌑");
                flipToOtherSide();
                break;
                
            case WILD:
                FlipCard.LightColor newColor = currentPlayer.chooseLightColor();
                topCard.setLightColor(newColor);
                System.out.println("🎨 Color changed to: " + newColor);
                break;
                
            case WILD_DRAW_TWO:
                nextPlayer();
                FlipPlayer nextPlayer2 = players.get(currentPlayerIndex);
                drawCards(nextPlayer2, 2);
                FlipCard.LightColor newColor2 = currentPlayer.chooseLightColor();
                topCard.setLightColor(newColor2);
                System.out.println("📥 " + nextPlayer2.getName() + " draws 2 cards and is skipped!");
                System.out.println("🎨 Color changed to: " + newColor2);
                break;
        }
    }
    
    private void handleDarkSideCard(FlipCard card, FlipPlayer currentPlayer) {
        switch (card.getDarkType()) {
            case SKIP_EVERYONE:
                System.out.println("💀 EVERYONE ELSE IS SKIPPED! " + currentPlayer.getName() + " plays again!");
                // Don't advance to next player - current player goes again
                return;
                
            case REVERSE:
                clockwise = !clockwise;
                System.out.println("🔄 Direction reversed!");
                break;
                
            case DRAW_FIVE:
                nextPlayer();
                FlipPlayer nextPlayer = players.get(currentPlayerIndex);
                drawCards(nextPlayer, 5);
                System.out.println("💥 " + nextPlayer.getName() + " draws 5 cards and is skipped!");
                break;
                
            case FLIP:
                System.out.println("🌟 FLIPPING TO LIGHT SIDE! 🌟");
                flipToOtherSide();
                break;
                
            case WILD:
                FlipCard.DarkColor newColor = currentPlayer.chooseDarkColor();
                topCard.setDarkColor(newColor);
                System.out.println("🎨 Color changed to: " + newColor);
                break;
                
            case WILD_DRAW_COLOR:
                nextPlayer();
                FlipPlayer nextPlayer2 = players.get(currentPlayerIndex);
                FlipCard.DarkColor chosenColor = currentPlayer.chooseDarkColor();
                drawUntilColor(nextPlayer2, chosenColor);
                topCard.setDarkColor(chosenColor);
                System.out.println("🎨 Color changed to: " + chosenColor);
                break;
        }
    }
    
    private void flipToOtherSide() {
        currentSide = (currentSide == FlipCard.Side.LIGHT) ? FlipCard.Side.DARK : FlipCard.Side.LIGHT;
        
        // Flip all cards in all hands
        for (FlipPlayer player : players) {
            player.flipHand();
        }
        
        // Flip the deck
        deck.flipAllCards();
        
        // Flip the top card
        topCard.flip();
        
        System.out.println("🔄 Everything flipped to " + currentSide + " side!");
        System.out.println("New top card: " + topCard);
    }
    
    private void drawCards(FlipPlayer player, int count) {
        for (int i = 0; i < count; i++) {
            FlipCard drawnCard = deck.drawCard();
            if (drawnCard != null) {
                player.addCard(drawnCard);
            }
        }
    }
    
    private void drawUntilColor(FlipPlayer player, FlipCard.DarkColor targetColor) {
        int drawn = 0;
        System.out.println("💀 " + player.getName() + " must draw until they get a " + targetColor + " card!");
        
        while (drawn < 20) { // Safety limit
            FlipCard drawnCard = deck.drawCard();
            if (drawnCard == null) break;
            
            player.addCard(drawnCard);
            drawn++;
            
            if (drawnCard.getDarkColor() == targetColor) {
                System.out.println("🎯 " + player.getName() + " drew " + drawn + " cards and got " + targetColor + "!");
                return;
            }
        }
        System.out.println("Safety limit reached. " + player.getName() + " drew " + drawn + " cards.");
    }
    
    private void nextPlayer() {
        if (clockwise) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
    }
    
    private boolean isGameOver() {
        return players.stream().anyMatch(FlipPlayer::hasWon) || deck.isEmpty();
    }
    
    public FlipCard getTopCard() {
        return topCard;
    }
    
    public static void main(String[] args) {
        UnoFlipGame game = new UnoFlipGame();
        game.playGame();
    }
}