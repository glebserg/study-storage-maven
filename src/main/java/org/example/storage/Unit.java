package org.example.storage;
import java.io.Serializable;

public interface Unit<V> extends Serializable {
    V getValue();
    void setValue(V value);
}
