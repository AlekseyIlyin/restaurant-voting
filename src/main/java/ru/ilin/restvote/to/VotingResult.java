package ru.ilin.restvote.to;

public class VotingResult {
    private int rest_id;
    private long voting;

    public VotingResult() {
    }

    public VotingResult(int rest_id, long voting) {
        this.rest_id = rest_id;
        this.voting = voting;
    }

    public int getRest_id() {
        return rest_id;
    }

    public void setRest_id(int rest_id) {
        this.rest_id = rest_id;
    }

    public long getVoting() {
        return voting;
    }

    public void setVoting(int voting) {
        this.voting = voting;
    }

    @Override
    public String toString() {
        return "VotingResult{" +
                "restaurant=" + rest_id +
                ", voting=" + voting +
                '}';
    }
}
