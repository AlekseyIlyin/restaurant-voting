package ru.ilin.restvote.to;

public class VotingResult {
    private int rest_id;
    private long rate;

    public VotingResult() {
    }

    public VotingResult(int rest_id, long rate) {
        this.rest_id = rest_id;
        this.rate = rate;
    }

    public int getRest_id() {
        return rest_id;
    }

    public void setRest_id(int rest_id) {
        this.rest_id = rest_id;
    }

    public long getVoting() {
        return rate;
    }

    public void setVoting(int voting) {
        this.rate = voting;
    }

    @Override
    public String toString() {
        return "VotingResult{" +
                "restaurant=" + rest_id +
                ", rate=" + rate +
                '}';
    }
}
