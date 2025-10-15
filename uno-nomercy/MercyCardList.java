import java.util.*;

public class MercyCardList {
    private Node head;
    private int size;
    
    private class Node {
        MercyCard card;
        Node next;
        
        Node(MercyCard card) {
            this.card = card;
            this.next = null;
        }
    }
    
    public void addCard(MercyCard card) {
        Node newNode = new Node(card);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        sortByPrecedence();
    }
    
    public MercyCard removeHighestPrecedence() {
        if (head == null) return null;
        
        MercyCard card = head.card;
        head = head.next;
        size--;
        return card;
    }
    
    public MercyCard removeCard(MercyCard targetCard) {
        if (head == null) return null;
        
        if (head.card.equals(targetCard)) {
            MercyCard card = head.card;
            head = head.next;
            size--;
            return card;
        }
        
        Node current = head;
        while (current.next != null) {
            if (current.next.card.equals(targetCard)) {
                MercyCard card = current.next.card;
                current.next = current.next.next;
                size--;
                return card;
            }
            current = current.next;
        }
        return null;
    }
    
    public MercyCard findPlayableCard(MercyCard topCard) {
        Node current = head;
        while (current != null) {
            if (current.card.canPlayOn(topCard)) {
                return current.card;
            }
            current = current.next;
        }
        return null;
    }
    
    public MercyCard findStackingCard(MercyCard topCard) {
        Node current = head;
        while (current != null) {
            if (current.card.isStackingCard() && current.card.canPlayOn(topCard)) {
                return current.card;
            }
            current = current.next;
        }
        return null;
    }
    
    public List<MercyCard> removeAllOfColor(MercyCard.Color color) {
        List<MercyCard> removed = new ArrayList<>();
        Node current = head;
        Node prev = null;
        
        while (current != null) {
            if (current.card.getColor() == color) {
                removed.add(current.card);
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                current = current.next;
            } else {
                prev = current;
                current = current.next;
            }
        }
        return removed;
    }
    
    private void sortByPrecedence() {
        if (head == null || head.next == null) return;
        
        List<MercyCard> cards = new ArrayList<>();
        Node current = head;
        while (current != null) {
            cards.add(current.card);
            current = current.next;
        }
        
        Collections.sort(cards);
        
        head = null;
        size = 0;
        for (MercyCard card : cards) {
            addCardUnsorted(card);
        }
    }
    
    private void addCardUnsorted(MercyCard card) {
        Node newNode = new Node(card);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    
    public List<MercyCard> getCards() {
        List<MercyCard> cards = new ArrayList<>();
        Node current = head;
        while (current != null) {
            cards.add(current.card);
            current = current.next;
        }
        return cards;
    }
}