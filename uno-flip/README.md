# 🔄 UNO Flip

Modern UNO variant featuring double-sided cards with Light and Dark sides, adding strategic depth to the classic game.

## 🎯 Game Features

### 🃏 Flip Cards System
- **Light Side**: Traditional UNO colors (Red, Yellow, Green, Blue)
- **Dark Side**: Special colors (Pink, Teal, Orange, Purple)
- **Flip Cards**: Switch between Light/Dark sides during gameplay

### 🎮 Special Cards
- **Draw Five**: Forces next player to draw 5 cards
- **Skip Everyone**: Skips all other players
- **Wild Draw Color**: Draw until you get the specified color
- **Flip Card**: Switches all cards to opposite side

### 🏗️ Architecture
```
FlipPlayer (Abstract)
├── FlipHumanPlayer - Interactive gameplay
└── FlipComputerPlayer - AI strategy

FlipCard - Double-sided card implementation
FlipCardList - Custom linked list with flip support
FlipDeck - 112-card Flip deck
UnoFlipGame - Main game controller
```

## 🚀 How to Run

```bash
javac *.java
java UnoFlipDemo
```

## 🎲 Gameplay Rules

1. **Setup**: Each player gets 7 cards, start with Light side
2. **Turns**: Play matching color/number or special cards
3. **Flip**: When Flip card played, all cards switch sides
4. **Win**: First player to empty hand wins
5. **Strategy**: Manage both Light and Dark side cards

## 🔧 Technical Details

- **Cards**: 112 total (56 Light + 56 Dark side)
- **Players**: 2-10 (Human/Computer mix)
- **Data Structure**: Custom linked list implementation
- **AI**: Strategic computer players with side awareness

## 🎯 Key Classes

- `FlipCard`: Implements double-sided card logic
- `FlipDeck`: Manages 112-card Flip deck
- `UnoFlipGame`: Controls game flow and side switching
- `FlipPlayer`: Abstract base for player types