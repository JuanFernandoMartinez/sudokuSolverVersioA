
module Demo {

    sequence<string> StringArr;
    
    interface Solver {
        StringArr solve(string sudoku);
    }
}