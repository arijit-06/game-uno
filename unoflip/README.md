# UNO FLIP! Implementation

## 🎴 What is UNO Flip?

UNO Flip is the ultimate twist on classic UNO! Every card has **two sides** - a Light Side and a Dark Side. When a Flip card is played, the entire game literally turns upside down!

## ✨ Light Side vs 💀 Dark Side

| Feature | Light Side | Dark Side |
|---------|------------|-----------|
| **Colors** | Teal, Pink, Purple, Orange | Blue, Green, Red, Yellow |
| **Mood** | Friendly, forgiving | Vengeful, cruel |
| **Effects** | Normal (+2, Skip, Reverse) | Evil (+5, Skip Everyone, Wild Draw Color) |

## 🎯 Core Features

### 🔄 The Flip Mechanic
- **Flip cards** turn everything upside down
- All hands, deck, and discard pile flip simultaneously
- Game continues on the new side with new rules
- Can flip back and forth multiple times per game

### 🃏 Card Types

**Light Side:**
- Numbers (0-9)
- Skip, Reverse, Draw Two
- **Flip** - Switches to Dark Side
- Wild, Wild Draw Two

**Dark Side:**
- Numbers (0-9) 
- **Skip Everyone** - All other players lose turn
- Reverse, **Draw Five**
- **Flip** - Switches to Light Side
- Wild, **Wild Draw Color** - Draw until you get chosen color

### 🎮 Gameplay Features
- **Precedence System**: Higher value cards discarded first
- **2-10 Players**: Mix of human and computer players
- **Anti-Cheat Architecture**: Inheritance prevents cheating
- **Linked List Implementation**: Custom card management
- **Complete UNO Flip Rules**: All special effects implemented

## 🏗️ Architecture

```
FlipPlayer (Abstract)
├── FlipHumanPlayer - Interactive gameplay with both sides
└── FlipComputerPlayer - AI strategy for both sides

FlipCardList - Custom linked list with flip functionality
FlipCard - Double-sided cards with precedence ordering
FlipDeck - Complete UNO Flip deck (112 cards)
UnoFlipGame - Main game controller with flip mechanics
```

## 🎲 Precedence System

**Light Side:**
1. Wild Draw Two (110)
2. Wild (100)
3. Flip (90)
4. Draw Two (80)
5. Skip (70)
6. Reverse (60)
7. Numbers (0-9)

**Dark Side:**
1. Wild Draw Color (120) - Most powerful!
2. Wild (100)
3. Flip (95)
4. Draw Five (85)
5. Skip Everyone (75)
6. Reverse (60)
7. Numbers (0-9)

## 🚀 How to Play

```bash
javac *.java
java UnoFlipDemo
```

### Game Flow:
1. Start on **Light Side** with friendly rules
2. Play cards matching color, number, or type
3. When **Flip** is played - everything flips!
4. Continue on **Dark Side** with evil rules
5. First to empty hand wins!

## 💀 Dark Side Special Effects

- **Skip Everyone**: You play again immediately while everyone else loses their turn
- **Draw Five**: Next player draws 5 cards and is skipped
- **Wild Draw Color**: Next player draws cards until they get your chosen color (potentially devastating!)

## 🛡️ Anti-Cheat Features

- Abstract FlipPlayer class prevents hand manipulation
- Encapsulated flip mechanics ensure consistency
- Inheritance guarantees all players follow same rules
- Linked list implementation with controlled access

## 🎯 Strategy Tips

- **Remember your other side!** Check what cards you'll have after a flip
- **Time your flips wisely** - flip when the other side favors you
- **Dark side is dangerous** - Wild Draw Color can destroy opponents
- **Precedence matters** - higher value cards are played first automatically

## 🌟 Example Gameplay

```
Light Side: Playing TEAL_7
Dark Side Ready: BLUE_SKIP_EVERYONE

*Flip card played*

🌑 FLIPPING TO DARK SIDE! 🌑
Now playing: BLUE_SKIP_EVERYONE
💀 EVERYONE ELSE IS SKIPPED! You play again!
```

The most chaotic and fun UNO variant ever created! 🎉