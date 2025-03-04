package org.example.storage.lfu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.storage.Unit;


@Getter
@Setter
@Builder
public class LFUUnit<V> implements Unit<V>{
    private V value;
    private Integer count;

    @Override
    public String toString() {
        return "{" + "value=" + this.value + ",count=" + this.count + "}";
    }
}
