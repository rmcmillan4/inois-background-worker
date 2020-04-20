package edu.gsu.ays.gpi.inoisbatch.entity;


import java.io.IOException;
import java.util.List;

public interface InoisEntity {

    public void hash(String saltKey);

    public void readBatch(String csv) throws IOException;

    public void writeBatch();

    public <T extends InoisEntity> List<T> retrieveBatch();

}
