import java.util.*;

public class ComputerPlayer extends Player {
    private Random random;
    
    public ComputerPlayer(String name) {
        super(name, true);
        this.random = new Random();
    }
    
    @Override
    public Card playCard(Card topCard, UnoGame game) {
        // Computer always plays highest precedence card available
        Card playableCard = findPlayableCard(topCard);
        
        if (playableCard != null) {
            System.out.println(name + " plays: " + playableCard + " on " + topCard);
            return removeCard(playableCard);
        }
        
        System.out.println(name + " draws a card (no valid moves on " + topCard + ")");
        return null;
    }
    
    @Override
    public Card.Color chooseColor() {
        // Computer chooses color strategically (random for simplicity)
        Card.Color[] colors = {Card.Color.RED, Card.Color.BLUE, Card.Color.GREEN, Card.Color.YELLOW};
        Card.Color chosen = colors[random.nextInt(colors.length)];
        System.out.println(name + " chooses color: " + chosen);
        return chosen;
    }
}