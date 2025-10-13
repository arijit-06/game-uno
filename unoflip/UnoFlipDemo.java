public class UnoFlipDemo {
    public static void main(String[] args) {
        System.out.println("=== UNO FLIP GAME DEMO ===");
        System.out.println("🎴 The Ultimate UNO Experience!");
        System.out.println();
        System.out.println("✨ LIGHT SIDE Features:");
        System.out.println("  🎨 Colors: Teal, Pink, Purple, Orange");
        System.out.println("  Cards: Numbers, Skip, Reverse, Draw 2, Flip, Wild, Wild Draw 2");
        System.out.println();
        System.out.println("💀 DARK SIDE Features:");
        System.out.println("  🎨 Colors: Blue, Green, Red, Yellow");
        System.out.println("  Cards: Numbers, Skip Everyone, Reverse, Draw 5, Flip, Wild, Wild Draw Color");
        System.out.println();
        System.out.println("🔄 FLIP MECHANICS:");
        System.out.println("  ⚡ Flip cards turn the entire game upside down!");
        System.out.println("  🔀 All hands, deck, and discard pile flip to other side");
        System.out.println("  🎯 Precedence system: Higher precedence cards played first");
        System.out.println();
        System.out.println("ANTI-CHEAT FEATURES:");
        System.out.println("  ✓ Inheritance-based Player system");
        System.out.println("  ✓ Linked List card management");
        System.out.println("  ✓ Encapsulated flip mechanics");
        System.out.println("  ✓ 2-10 players (Human vs Computer vs Computer)");
        System.out.println();
        System.out.println("💥 DARK SIDE SPECIAL EFFECTS:");
        System.out.println("  🔥 Skip Everyone: All other players lose their turn!");
        System.out.println("  💀 Draw 5: Next player draws 5 cards!");
        System.out.println("  🌈 Wild Draw Color: Draw until you get the chosen color!");
        System.out.println();
        System.out.println("Starting UNO FLIP...\n");
        
        // Start the actual game
        UnoFlipGame game = new UnoFlipGame();
        game.playGame();
    }
}