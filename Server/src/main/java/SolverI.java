import code.*;
import java.util.List;

public class SolverI implements Demo.Solver {
    

    public String[] solve(String sudoku, com.zeroc.Ice.Current current){
        return code(sudoku);
    }

    private String[] code(String s){
        DancingLinksAlgorithm dla = new DancingLinksAlgorithm();
        int[][] matrix = dla.ParseInput(s);
        List<String> lista = dla.solve(matrix);

        String[] arr = new String[lista.size()];
        arr = lista.toArray(arr);

        return arr;
    }
}
