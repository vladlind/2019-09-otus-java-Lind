package ru.otus.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;


public class MyCache<K, V> implements HwCache<K, V> {

  private final WeakHashMap<K, V> cache = new WeakHashMap();
  private final List<HwListener> listeners = new ArrayList();


  @Override
  public void put(K key, V value) {
    cache.put(key, value);
  }

  @Override
  public void remove(K key) {
    V value = cache.get(key);
    cache.remove(key);
    listeners.forEach(listener -> {
      try {
        listener.notify(key, value, "updating...");
      } catch  (Exception e) {
        System.out.println("Failed to update: "+e);}});
  }

  @Override
  public V get(K key) {
    V value = cache.get(key);
    listeners.forEach(listener -> {
      try {
        listener.notify(key, value, "getting...");
      } catch  (Exception e) {
        System.out.println("Not present in cache: "+e);}});
    return value;
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
