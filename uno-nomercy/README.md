# 💙 UNO Mercy

UNO variant with modified mercy rules for more balanced and strategic gameplay.

## 🎯 Game Features

### 🃏 Mercy Rules
- **Mercy Draw**: Limit consecutive draws
- **Mercy Skip**: Prevent excessive skipping
- **Mercy Reverse**: Balanced direction changes
- **Mercy Wild**: Strategic wild card usage

### 🎮 Special Mechanics
- **Draw Limit**: Maximum draws per turn
- **Skip Protection**: Anti-skip abuse system
- **Reverse Balance**: Fair direction switching
- **Wild Strategy**: Enhanced wild card effects

## 🏗️ Architecture

```
MercyPlayer (Abstract)
├── MercyHumanPlayer - Interactive gameplay
└── MercyComputerPlayer - AI with mercy awareness

MercyCard - Cards with mercy rule support
MercyCardList - Custom linked list implementation
MercyDeck - Standard deck with mercy modifications
UnoMercyGame - Game controller with mercy rules
```

## 🚀 How to Run

```bash
javac *.java
java UnoMercyDemo
```

## 🎲 Gameplay Rules

1. **Setup**: Standard 7 cards per player
2. **Mercy Draws**: Limited consecutive draws
3. **Mercy Skips**: Skip protection mechanisms
4. **Win Condition**: First to empty hand wins
5. **Balance**: Fair gameplay for all players

## 🔧 Technical Details

- **Cards**: 108 total with mercy modifications
- **Players**: 2-10 (Human/Computer mix)
- **Rules**: Enhanced mercy rule engine
- **AI**: Mercy-aware computer strategies

## 🎯 Key Classes

- `MercyCard`: Cards with mercy rule support
- `MercyDeck`: Deck with mercy modifications
- `UnoMercyGame`: Game controller with mercy logic
- `MercyPlayer`: Abstract base with mercy awareness