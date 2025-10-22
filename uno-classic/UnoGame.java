import java.util.*;

/**
 * Simple UNO game implementation (console-based).
 *
 * This class manages players, the deck, and the main game loop.
 * Comments were updated to provide clearer documentation for library users
 * and to make the intent of key methods explicit.
 */
public class UnoGame {
    private List<Player> players;
    private Deck deck;
    private Card topCard;
    private int currentPlayerIndex;
    private boolean clockwise;
    private Scanner scanner;

    public UnoGame() {
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.currentPlayerIndex = 0;
        this.clockwise = true;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Set up the game by asking for the number of players and their names,
     * dealing initial cards, and choosing a non-wild card as the initial
     * top card.
     */
    public void setupGame() {
        System.out.println("=== UNO GAME SETUP ===");
        System.out.print("Number of players (2-10): ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (numPlayers < 2 || numPlayers > 10) {
            System.out.println("Invalid number of players! Setting to 4.");
            numPlayers = 4;
        }

        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Player " + (i + 1) + " - Enter name (or 'computer' for AI): ");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("computer")) {
                players.add(new ComputerPlayer("Computer" + (i + 1)));
            } else {
                players.add(new HumanPlayer(name));
            }
        }

        // Deal initial cards (7 each)
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.addCard(deck.drawCard());
            }
        }

        // Choose an initial top card that is not a wild card
        do {
            topCard = deck.drawCard();
        } while (topCard.getType() == Card.Type.WILD || topCard.getType() == Card.Type.WILD_DRAW_FOUR);

        System.out.println("\nGame started! Initial top card: " + topCard);
    }

    /**
     * Run the main game loop until a player wins or the deck is empty.
     */
    public void playGame() {
        setupGame();

        while (!isGameOver()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("\n" + "=".repeat(50));
            System.out.println("Current player: " + currentPlayer);

            Card playedCard = currentPlayer.playCard(topCard, this);

            if (playedCard != null) {
                topCard = playedCard;
                System.out.println("Card played: " + playedCard);

                // Handle any special card behaviour (skip, reverse, draw, etc.)
                handleSpecialCard(playedCard, currentPlayer);

                // Check for win
                if (currentPlayer.hasWon()) {
                    System.out.println("\n🎉 " + currentPlayer.getName() + " wins! 🎉");
                    return;
                }
            } else {
                // Player could not play — draw one card from the deck
                Card drawnCard = deck.drawCard();
                if (drawnCard != null) {
                    currentPlayer.addCard(drawnCard);
                    System.out.println(currentPlayer.getName() + " drew a card");
                }
            }

            nextPlayer();
        }
    }

    private void handleSpecialCard(Card card, Player currentPlayer) {
        switch (card.getType()) {
            case SKIP:
                System.out.println("Next player is skipped!");
                nextPlayer();
                break;

            case REVERSE:
                clockwise = !clockwise;
                System.out.println("Direction reversed!");
                break;

            case DRAW_TWO:
                nextPlayer();
                Player nextPlayer = players.get(currentPlayerIndex);
                for (int i = 0; i < 2; i++) {
                    Card drawnCard = deck.drawCard();
                    if (drawnCard != null)
                        nextPlayer.addCard(drawnCard);
                }
                System.out.println(nextPlayer.getName() + " draws 2 cards and is skipped!");
                break;

            case WILD:
                Card.Color newColor = currentPlayer.chooseColor();
                topCard.setColor(newColor);
                System.out.println("Color changed to: " + newColor);
                break;

            case WILD_DRAW_FOUR:
                nextPlayer();
                Player nextPlayer4 = players.get(currentPlayerIndex);
                for (int i = 0; i < 4; i++) {
                    Card drawnCard = deck.drawCard();
                    if (drawnCard != null)
                        nextPlayer4.addCard(drawnCard);
                }
                Card.Color newColor4 = currentPlayer.chooseColor();
                topCard.setColor(newColor4);
                System.out.println(nextPlayer4.getName() + " draws 4 cards and is skipped!");
                System.out.println("Color changed to: " + newColor4);
                break;
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
        return players.stream().anyMatch(Player::hasWon) || deck.isEmpty();
    }

    public Card getTopCard() {
        return topCard;
    }

    public static void main(String[] args) {
        UnoGame game = new UnoGame();
        game.playGame();
    }
}