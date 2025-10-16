import java.util.*;

public class FlipComputerPlayer extends BasePlayer<FlipCard> {
    private Random random;
    
    public FlipComputerPlayer(String name) {
        super(name, true);
        this.random = new Random();
    }
    
    @Override
    public FlipCard playCard(FlipCard topCard, BaseGame<FlipCard> game) {
        FlipCard playableCard = findPlayableCard(topCard);
        
        if (playableCard != null) {
            System.out.println(name + " plays: " + playableCard + " on " + topCard);
            return removeCard(playableCard);
        }
        
        System.out.println(name + " draws a card (no valid moves on " + topCard + ")");
        return null;
    }
    
    @Override
    public BaseCard.Color chooseColor() {
        BaseCard.Color[] colors = {BaseCard.Color.RED, BaseCard.Color.BLUE, 
                                  BaseCard.Color.GREEN, BaseCard.Color.YELLOW};
        BaseCard.Color chosen = colors[random.nextInt(colors.length)];
        System.out.println(name + " chooses color: " + chosen);
        return chosen;
    }
}