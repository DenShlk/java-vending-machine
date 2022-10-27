package matrixvm;

import java.util.Objects;

/**
 * Хранит место товара в таблице вида
 *  *   * A               * B              *
 *  * 1 * 15₿ - Cola 0.33 * 25₿ - Cola 0.5 *
 *  * 2 * 12₿ - Twix      * 10₿ - Nuts     *
 */
public class RowColumnId {
    public int row;
    public final char column;

    public RowColumnId(int row, char column) {
        this.row = row;
        this.column = column;
    }

    public static RowColumnId fromIndices(int i, int j) {
        return new RowColumnId(i + 1, (char) ('A' + j));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RowColumnId that = (RowColumnId) o;
        return row == that.row &&
                column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
