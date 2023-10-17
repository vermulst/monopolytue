package me.tue.monopolytue.turn;

public class Participant {

    private int balance;
    private boolean bankrupt = false;

    public Participant() {

    }

    public void addToBalance(int amount) {
        this.balance += amount;
    }

    public void removeToBalance(int amount) {
        this.balance -= amount;
        this.checkBankrupt();
    }

    public void transferAmount(Participant receiver, int amount) {
        receiver.addToBalance(amount);
        this.removeToBalance(amount);
    }

    public void checkBankrupt() {
        if (this.balance < 0) {
            this.bankrupt = true;
        }
    }
    
}
