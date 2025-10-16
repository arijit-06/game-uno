import java.util.*;

public class ClassicComputerPlayer extends BasePlayer<ClassicCard> {
    private Random random;
    
    public ClassicComputerPlayer(String name) {
        super(name, true);
        this.random = new Random();
    }
    
    @Override
    public ClassicCard playCard(ClassicCard topCard, BaseGame<ClassicCard> game) {
        ClassicCard playableCard = findPlayableCard(topCard);
        
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