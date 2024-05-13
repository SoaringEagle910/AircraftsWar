package edu.hitsz.Score;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Record implements Serializable {
    private int rank;
    private String name;
    private int mark;
    private LocalDateTime time;
    Record(int rank,String name,int mark,LocalDateTime time){
        this.rank=rank;
        this.name=name;
        this.mark=mark;
        this.time=time;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

