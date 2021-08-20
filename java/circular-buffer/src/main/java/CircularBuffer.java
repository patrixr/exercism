class CircularBuffer<T> {
    private int size;
    private T[] buffer;
    private int lastReadIndex;
    private int lastWriteIndex;

    public CircularBuffer(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("CircularBuffer must be of size greater than 0");
        }

        this.lastReadIndex = -1;
        this.lastWriteIndex = -1;
        this.size = size;
        this.buffer = (T[]) new Object[size];
    }

    public void write(T val) throws BufferIOException {
        if (isFull()) {
            throw new BufferIOException("Tried to write to full buffer");
        }
        overwrite(val);
    }

    public void overwrite(T val) {
        lastReadIndex += isFull() ? 1 : 0;
        set(++lastWriteIndex, val);
    }

    public T read() throws BufferIOException {
        if (lastReadIndex == lastWriteIndex) {
            throw new BufferIOException("Tried to read from empty buffer");
        }
        return get(++lastReadIndex);
    }

    public void clear() {
        lastReadIndex = -1;
        lastWriteIndex = -1;
    }

    public boolean isFull() {
        return lastWriteIndex - lastReadIndex >= size;
    }

    // ------------------
    // ~ Helpers
    // ------------------

    private void set(int idx, T val) {
        buffer[idx % size] = val;
    }

    private T get(int idx) {
        return buffer[idx % size];
    }
}