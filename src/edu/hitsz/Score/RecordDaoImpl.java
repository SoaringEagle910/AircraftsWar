package edu.hitsz.Score;

import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl implements RecordDao{
    private List<Record>records;
    public RecordDaoImpl(){
        records=new ArrayList<Record>();
    }
    @Override
    public List<Record>getAllRecord(){
        return records;
    }
    @Override
    public void doAdd(Record newRecord){
        records.add(newRecord);
        return;
    }
}



