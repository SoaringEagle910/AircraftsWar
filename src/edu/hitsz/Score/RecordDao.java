package edu.hitsz.Score;

import java.util.List;

public interface RecordDao {
    List<Record>getAllRecord();
    void doAdd(Record record);
    //void doDelete(int rank);
}



