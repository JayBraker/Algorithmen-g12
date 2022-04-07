public interface IList<T> {
	void insertAt(int index, T element);
	T removeAt(int index);
	T getAt(int index);
	int search(T element);
	void clear();
	int getCount();
}
