package me.tue.monopolytue.turn.participant;

import me.tue.monopolytue.board.Board;
import me.tue.monopolytue.board.Square;
import me.tue.monopolytue.turn.DiceRoller;

public class AIOpponent extends Participant{
    public AIOpponent(int id) {
        super(id);
    }

    public void executeTurn(DiceRoller diceroller) {

        diceroller.rollDice();
        Square newSquare = this.getCurrentSquare(diceroller.getBoard());
        if (newSquare.getOwner() == null && this.makeBuyDecision(diceroller.getBoard(), newSquare)) {
            this.buySquare(newSquare, diceroller.getBoard());
        }
        diceroller.setRolled(false);
        diceroller.nextTurn();
    }


    public boolean makeBuyDecision(Board board, Square squareToBuy) {
        int squarePrice = squareToBuy.getSquareGroup().getPrice();
        if (squarePrice == 0) {
            return false;
        }
        int balance = this.getBalance();

        int currentBalanceRanking = 1;
        for (Participant participant : board.getParticipants()) {
            if (this.equals(participant)) continue;
            if (participant.getBalance() > balance) {
                currentBalanceRanking++;
            }
        }

        int currentMonopolyRanking = 1;
        for (Participant participant : board.getParticipants()) {
            if (this.equals(participant)) continue;
            if (participant.getTotalWorth(board) > this.getTotalWorth(board)) {
                currentMonopolyRanking++;
            }
        }

        boolean predicate1 = (balance - 100) > squarePrice;
        boolean predicate2 = currentBalanceRanking != board.getParticipants().length;
        boolean predicate3 = (currentMonopolyRanking != board.getParticipants().length) || ((balance / 2) > squarePrice);
        boolean predicate4 = ((balance / 2) > squarePrice);

        return (predicate1 && predicate2 && predicate3) || predicate4;
    }



}
