package com.project.commercial.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class adds autoincrement feature to all type of objects. Each time when creating an object
 * this class check the next id number and give a new id to the object.
 * @author fatih akguc
 */

@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;

    private int seq;

    public DatabaseSequence() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

}
