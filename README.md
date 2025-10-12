# UNO Game Implementation

## Features

### 🎯 Core Requirements Met
- **Inheritance-based architecture** - Abstract `Player` class prevents cheating
- **Linked List implementation** - Custom `CardList` class manages cards
- **Precedence system** - Higher precedence cards are discarded first
- **Multi-player support** - 2-10 players (minimum 2)
- **Computer vs Human** - Mix of AI and human players
- **Computer vs Computer** - Full AI gameplay possible

### 🃏 Card System
- **Precedence Order** (highest to lowest):
  1. Wild Draw Four (100)
  2. Wild (90)
  3. Draw Two (80)
  4. Skip (70)
  5. Reverse (60)
  6. Number cards (0-9)

### 🎮 Game Features
- Complete UNO rules implementation
- Special card effects (Skip, Reverse, Draw Two, Wild cards)
- Direction changes and player skipping
- Win condition detection
- Automatic card sorting by precedence

### 🏗️ Architecture

```
Player (Abstract)
├── HumanPlayer - Interactive gameplay
└── ComputerPlayer - AI with strategy

CardList - Custom linked list with precedence sorting
Card - Implements Comparable for precedence ordering
Deck - Standard UNO deck (108 cards)
UnoGame - Main game controller
```

### 🚀 How to Run
```bash
javac *.java
java UnoDemo
```

### 🎲 Gameplay
1. Choose number of players (2-10)
2. Enter player names or "computer" for AI
3. Game deals 7 cards to each player
4. Players take turns playing cards or drawing
5. First player to empty their hand wins!

### 🔒 Anti-Cheat Features
- Abstract Player class prevents direct hand manipulation
- Encapsulated card management through controlled methods
- Inheritance ensures consistent game rules for all player types