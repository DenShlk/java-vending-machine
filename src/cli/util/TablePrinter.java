package cli.util;

import java.util.Objects;

/**
 * Utility class for printing tables. Calculates width of each column, then fills elements with spaces to match
 * calculated width of the column. Prints result to System.out
 */
public class TablePrinter {
    private static final String SEP = " * ";

    public static void printTable(String[][] table) {
        int[] width = new int[table[0].length];
        for (String[] row : table) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] != null) {
                    width[i] = Math.max(width[i], row[i].length());
                }
            }
        }

        for (String[] row : table) {
            System.out.print(SEP);
            for (int i = 0; i < row.length; i++) {
                StringBuilder builder = new StringBuilder(row[i] == null ? "" : row[i]);
                for (int j = builder.length(); j < width[i]; j++) {
                    builder.append(' ');
                }
                System.out.print(builder.toString());
                System.out.print(SEP);
            }
            System.out.println();
        }
    }
}
