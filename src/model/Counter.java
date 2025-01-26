package model;

import java.io.IOException;
import java.io.Serializable;

public class Counter implements AutoCloseable, Serializable {
    private Integer count;
    private boolean closed = false;

    public Counter(){
        this.count = 0;
    }
    public Integer add() throws IOException{
        if (closed) throw new IOException("Counter is closed");
        this.count += 1;
        return this.count;
    }
    public Integer getCount(){
        return this.count;
    }

    @Override
    public void close() throws Exception {
        closed = true;
        System.out.println("Counter closed");
    }
}
