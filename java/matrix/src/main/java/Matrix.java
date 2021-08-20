import java.util.Arrays;

class Matrix {
    private int[][] rows;

    Matrix(String matrixAsString) {
        String[] lines = matrixAsString.split("\n");

        rows = Arrays
            .stream(lines)
            .map(line ->
                Arrays
                .stream(line.split(" "))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray()
            )
            .toArray(int[][]::new);
    }

    int[] getRow(int rowNumber) {
        return rows[rowNumber - 1];
    }

    int[] getColumn(int columnNumber) {
        return Arrays
            .stream(rows)
            .mapToInt(row -> row[columnNumber - 1])
            .toArray();
    }
}
