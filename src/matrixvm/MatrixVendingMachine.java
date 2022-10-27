package matrixvm;

import model.AbstractVendingMachine;
import model.ItemStorage;
import model.PaymentMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrixVendingMachine<Item, PM extends PaymentMethod> extends AbstractVendingMachine<RowColumnId, Item, PM> {
    protected final int itemMatrixWidth;
    protected final int itemMatrixHeight;

    public MatrixVendingMachine(List<PM> paymentMethods, Item[][] itemMatrix, Integer[][] countsMatrix) {
        super(paymentMethods, new ItemStorage<>(matrixToMap(itemMatrix), matrixToMap(countsMatrix)));

        assert itemMatrix.length == countsMatrix.length;
        for (int i = 0; i < itemMatrix.length; i++) {
            assert itemMatrix[i].length == countsMatrix[i].length;
            assert itemMatrix[0].length == itemMatrix[i].length;
        }
        itemMatrixWidth = itemMatrix[0].length;
        itemMatrixHeight = itemMatrix.length;
    }

    private static <T> Map<RowColumnId, T> matrixToMap(T[][] itemMatrix) {
        Map<RowColumnId, T> items = new HashMap<>();
        for (int i = 0; i < itemMatrix.length; i++) {
            for (int j = 0; j < itemMatrix[i].length; j++) {
                items.put(RowColumnId.fromIndices(i, j), itemMatrix[i][j]);
            }
        }
        return items;
    }
}
