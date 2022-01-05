package ru.job4j.isp;

import java.util.*;

/**
 * Класс позволяет создавать древовидное меню с возможностью добавления элементов меню
 * их удалению и изменению. И возможностью осуществить действие из меню посредством
 * метода {@link #getAction(K keyNode)}.
 * И возможность получить созданное меню в виде "строки" из метода {@link #printMenu()}.
 * @param <K>
 */

public class SimpleMenu<K> implements Menu<K>, Printing<K> {

    private Node<K> rootNode;

    public SimpleMenu(K keyNode, Action action) {
        this.rootNode = new Node<>(keyNode, action);
    }

    @Override
    public boolean add(K keyNode, K parent, Action action) {
        Optional<Node<K>> parentNode = findNode(parent);
        Optional<Node<K>> existedNode = findNode(keyNode);
        if (parentNode.isEmpty() || existedNode.isPresent()) {
            return false;
        } else {
            parentNode.get().children.add(new Node<>(keyNode, parentNode.get(), action));
            return true;
        }
    }

    private Optional<Node<K>> findNode(K keyNode) {
        Optional<Node<K>> result = Optional.empty();
        Queue<Node<K>> nodeQueue = new LinkedList<>(List.of(rootNode));
        while (!nodeQueue.isEmpty()) {
            Node<K> tmp = nodeQueue.poll();
            if (tmp.key.equals(keyNode)) {
                result = Optional.of(tmp);
                break;
            }
            nodeQueue.addAll(tmp.children);
        }
        return result;
    }

    @Override
    public boolean remove(K keyNode) {
        Optional<Node<K>> result = findNode(keyNode);
        if (result.isEmpty()) {
            return false;
        }
        if (result.get() == rootNode) {
            rootNode = null;
            return true;
        }
        Node<K> parentResult = result.get().parent;
        parentResult.children.addAll(result.get().children);
        parentResult.children.remove(result.get());
        return true;
    }

    @Override
    public boolean update(K keyNode, Action action) {
        Optional<Node<K>> result = findNode(keyNode);
        if (result.isEmpty()) {
            return false;
        } else {
            result.get().action = action;
            return true;
        }
    }

    @Override
    public Action getAction(K keyNode) {
        Optional<Node<K>> result = findNode(keyNode);
        return result.isEmpty() ? null : result.get().action;
    }

    @Override
    public String printMenu() {
        return createMenu(rootNode, "Задача ", 1, new StringJoiner(System.lineSeparator()))
                .toString();
    }

    private StringJoiner createMenu(Node<K> node, String prefix, int level, StringJoiner input) {
        int subLevel = 1;
        prefix = prefix + level + ".";
        input.add(prefix + " " + node);
        prefix = "  " + prefix;
        for (Node<K> n : node.children) {
            createMenu(n, prefix, subLevel++, input);
        }
        return input;
    }

    private static class Node<K> {

        private final K key;
        private final List<Node<K>> children = new LinkedList<>();
        private Node<K> parent;
        private Action action;

        private Node(K key, Action action) {
            this.key = key;
            this.action = action;
        }

        public Node(K key, Node<K> parent, Action action) {
            this(key, action);
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return key.equals(node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        @Override
        public String toString() {
            return "Node = " + key;
        }
    }
}
