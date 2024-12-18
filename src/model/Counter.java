package model;

import java.io.Serializable;

public class Counter implements AutoCloseable, Serializable {
    private Integer count;

    public Counter(){
        this.count = 0;
    }
    public Integer add(){
        this.count += 1;
        return this.count;
    }
    public Integer getCount(){
        return this.count;
    }

    @Override
    public void close() throws Exception {
        System.out.println("Counter closed");
    }
}
