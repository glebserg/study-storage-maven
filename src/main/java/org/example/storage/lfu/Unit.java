package org.example.storage.lfu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Unit<V> {
    private V value;
    private Integer count;

    @Override
    public String toString() {
        return "{" + "value=" + this.value + ",count=" + this.count + "}";
    }
}
