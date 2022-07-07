package domain;

import java.io.Serializable;

public class Score implements Comparable<Score>, Serializable {

    private String sid;

    private String cid;

    private double Sscore;

    public Score() {
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public double getSscore() {
        return Sscore;
    }

    public void setSscore(double sscore) {
        Sscore = sscore;
    }

    @Override
    public int compareTo(Score s) {
        return this.sid.compareTo(s.sid);
    }
}
