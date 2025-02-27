package org.example.storage.lfu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class LFUUnit<V> implements Serializable {
    private V value;
    private Integer count;

    @Override
    public String toString() {
        return "{" + "value=" + this.value + ",count=" + this.count + "}";
    }
}
