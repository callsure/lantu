package org.lantu.utils.concurrent;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * 复制读写list
 * Created by runshu.lin on 2018/12/17.
 */
public class ReadOnCopyArrayList<T> implements List<T> {

	private final AtomicBoolean changed = new AtomicBoolean(true);

	private List<T> list = Collections.synchronizedList(new ArrayList<>());

	private List<T> readOnlyList = Collections.emptyList();

	@Override
	public boolean add(T e) {
		boolean result = list.add(e);
		changed.set(true);
		return result;
	}

	@Override
	public void add(int index, T element) {
		list.add(index, element);
		changed.set(true);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean result = list.addAll(c);
		changed.set(true);
		return result;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		boolean result = list.addAll(index, c);
		changed.set(true);
		return result;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return getReadOnlyList().contains(c);
	}

	@Override
	public boolean contains(Object o) {
		return getReadOnlyList().contains(o);
	}

	@Override
	public void forEach(Consumer<? super T> action) {
		getReadOnlyList().forEach(action);
	}

	@Override
	public T get(int index) {
		return list.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return getReadOnlyList().indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return getReadOnlyList().iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return getReadOnlyList().lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		return getReadOnlyList().listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return getReadOnlyList().listIterator(index);
	}

	@Override
	public T remove(int index) {
		T result = list.remove(index);
		if (null != result) {
			changed.set(true);
		}
		return result;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean result = list.removeAll(c);
		if (result) {
			changed.set(true);
		}
		return result;
	}

	@Override
	public T set(int index, T element) {
		T result = list.set(index, element);
		changed.set(true);
		return result;
	}

	@Override
	public int size() {
		return getReadOnlyList().size();
	}

	@Override
	public boolean remove(Object o) {
		boolean result = list.remove(o);
		if (result) {
			changed.set(true);
		}
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean result = list.retainAll(c);
		if (result) {
			changed.set(true);
		}
		return result;
	}

	@Override
	public void clear() {
		list.clear();
		changed.set(true);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return getReadOnlyList().subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return getReadOnlyList().toArray();
	}

	@Override
	public <U extends Object> U[] toArray(U[] a) {
		return getReadOnlyList().toArray(a);
	}

	@Override
	public Stream<T> parallelStream() {
		return getReadOnlyList().parallelStream();
	}

	@Override
	public boolean removeIf(Predicate<? super T> filter) {
		boolean result = list.removeIf(filter);
		if (result) {
			changed.set(true);
		}
		return result;
	}

	@Override
	public void replaceAll(UnaryOperator<T> operator) {
		list.replaceAll(operator);
		changed.set(true);
	}

	@Override
	public void sort(Comparator<? super T> c) {
		list.sort(c);
		changed.set(true);
	}

	@Override
	public Spliterator<T> spliterator() {
		return getReadOnlyList().spliterator();
	}

	@Override
	public Stream<T> stream() {
		return getReadOnlyList().stream();
	}

	private List<T> getReadOnlyList() {
		if (changed.compareAndSet(true, false)) {
			readOnlyList = new ArrayList<>(list);
		}
		return readOnlyList;
	}
}
