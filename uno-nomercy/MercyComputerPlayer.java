import java.util.*;

public class MercyComputerPlayer extends MercyPlayer {
    private Random random;
    
    public MercyComputerPlayer(String name) {
        super(name, true);
        this.random = new Random();
    }
    
    @Override
    public MercyCard playCard(MercyCard topCard, UnoMercyGame game) {
        // Auto-call UNO when at 2 cards
        if (hand.size() == 2 && !hasCalledUno) {
            callUno();
            System.out.println(name + " calls UNO!");
        }
        
        MercyCard playableCard = findPlayableCard(topCard);
        
        if (playableCard != null) {
            System.out.println(name + " plays: " + playableCard + " on " + topCard);
            return removeCard(playableCard);
        }
        
        System.out.println(name + " draws a card (no valid moves on " + topCard + ")");
        return null;
    }
    
    @Override
    public MercyCard.Color chooseColor() {
        // Choose color based on most cards in hand
        Map<MercyCard.Color, Integer> colorCount = new HashMap<>();
        colorCount.put(MercyCard.Color.RED, 0);
        colorCount.put(MercyCard.Color.BLUE, 0);
        colorCount.put(MercyCard.Color.GREEN, 0);
        colorCount.put(MercyCard.Color.YELLOW, 0);
        
        for (MercyCard card : hand.getCards()) {
            if (card.getColor() != MercyCard.Color.WILD) {
                colorCount.put(card.getColor(), colorCount.get(card.getColor()) + 1);
            }
        }
        
        MercyCard.Color bestColor = MercyCard.Color.RED;
        int maxCount = 0;
        for (Map.Entry<MercyCard.Color, Integer> entry : colorCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                bestColor = entry.getKey();
            }
        }
        
        System.out.println(name + " chooses color: " + bestColor);
        return bestColor;
    }
    
    @Override
    public boolean wantsToStack(MercyCard topCard) {
        MercyCard stackCard = findStackingCard(topCard);
        if (stackCard == null) return false;
        
        // Computer always stacks if possible (No Mercy!)
        System.out.println(name + " decides to stack " + stackCard + "!");
        return true;
    }
}