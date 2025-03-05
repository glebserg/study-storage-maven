package org.example.storage.lfu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.storage.CacheStorageUnit;

@ToString
@Getter
@Setter
@Builder
public class LFUUnit<V> implements CacheStorageUnit<V> {
    private V value;
    private Integer count;
}
