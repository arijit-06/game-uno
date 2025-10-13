import java.util.*;

public class FlipComputerPlayer extends FlipPlayer {
    private Random random;
    
    public FlipComputerPlayer(String name) {
        super(name, true);
        this.random = new Random();
    }
    
    @Override
    public FlipCard playCard(FlipCard topCard, UnoFlipGame game) {
        FlipCard playableCard = findPlayableCard(topCard);
        
        if (playableCard != null) {
            System.out.println(name + " plays: " + playableCard + " on " + topCard);
            return removeCard(playableCard);
        }
        
        System.out.println(name + " draws a card (no valid moves on " + topCard + ")");
        return null;
    }
    
    @Override
    public FlipCard.LightColor chooseLightColor() {
        FlipCard.LightColor[] colors = {FlipCard.LightColor.TEAL, FlipCard.LightColor.PINK, 
                                       FlipCard.LightColor.PURPLE, FlipCard.LightColor.ORANGE};
        FlipCard.LightColor chosen = colors[random.nextInt(colors.length)];
        System.out.println(name + " chooses LIGHT color: " + chosen);
        return chosen;
    }
    
    @Override
    public FlipCard.DarkColor chooseDarkColor() {
        FlipCard.DarkColor[] colors = {FlipCard.DarkColor.BLUE, FlipCard.DarkColor.GREEN, 
                                      FlipCard.DarkColor.RED, FlipCard.DarkColor.YELLOW};
        FlipCard.DarkColor chosen = colors[random.nextInt(colors.length)];
        System.out.println(name + " chooses DARK color: " + chosen);
        return chosen;
    }
}