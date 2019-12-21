package ru.otus.cache;

import java.util.ArrayList;
import java.util.WeakHashMap;


public class MyCache<K, V> implements HwCache<K, V> {

  private final WeakHashMap<K, V> cache = new WeakHashMap();
  private final ArrayList<HwListener> listeners = new ArrayList();


  @Override
  public void put(K key, V value) {
    cache.put(key, value);
    listeners.forEach(listener -> listener.notify(key, value, "put"));
  }

  @Override
  public void remove(K key) {
    V value = cache.get(key);
    cache.remove(key);
    listeners.forEach(listener -> listener.notify(key, value, "remove"));
  }

  @Override
  public V get(K key) {
    return (V) cache.get(key);
  }

  @Override
  public void addListener(HwListener listener) {
    listeners.add(listener);
  }

  @Override
  public void removeListener(HwListener listener) {
    listeners.remove(listener);
  }

}
