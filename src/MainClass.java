import java.util.ArrayList;

class MainClass {
    enum DIRECTION{UP,DOWN,LEFT,RIGHT};
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList resultList = new ArrayList<>();
        if(matrix==null){
            return resultList;
        }

        int lines = matrix.length;
        int columns = matrix[0].length;
        DIRECTION now = DIRECTION.UP;

        for(int i=0;i<lines;i++){
            for(int j=0;j<columns;j++){

            }
        }


        return null;
    }
}