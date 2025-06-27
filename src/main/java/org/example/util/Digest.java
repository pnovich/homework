package org.example.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Digest {

    private Map<MapKey, MapValue> cache = new HashMap<>();


    public byte[] digest(byte[] input) {
        MapKey key = new MapKey(input);
        MapValue result = cache.get(input);
        if (result == null) {
            synchronized (cache) {
                result = cache.get(input);
                if (result == null) {
                    result = new MapValue(doDigest(input));
                    cache.put(key, result);
                }
            }
        }
        return result.getValueArray();
    }

    protected abstract byte[] doDigest(byte[] input);
}

class MapKey {
    private byte[] keyArray;

    public MapKey(byte[] keyArray) {
        this.keyArray = keyArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapKey mapKey = (MapKey) o;
        return Objects.deepEquals(keyArray, mapKey.keyArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(keyArray);
    }

    public byte[] getKeyArray() {
        return keyArray;
    }

    public void setKeyArray(byte[] keyArray) {
        this.keyArray = keyArray;
    }
}

class MapValue {
    private byte[] valueArray;

    public MapValue(byte[] valueArray) {
        this.valueArray = valueArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapValue mapValue = (MapValue) o;
        return Objects.deepEquals(valueArray, mapValue.valueArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(valueArray);
    }

    public byte[] getValueArray() {
        return valueArray;
    }

    public void setValueArray(byte[] valueArray) {
        this.valueArray = valueArray;
    }
}