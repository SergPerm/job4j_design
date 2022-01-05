package ru.job4j.isp;

public interface Menu<K> {
    boolean add(K node, K parent, Action action);
    boolean remove(K node);
    boolean update(K node, Action action);
    Action getAction(K node);
}
