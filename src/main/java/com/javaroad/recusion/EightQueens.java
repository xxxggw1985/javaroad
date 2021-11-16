package com.javaroad.recusion;

public class EightQueens {
    private Integer[] result = new Integer[8];
    public void cal8Queens(int row){
        if(row == 8){
            printQueens(result);
            return;
        }
        for(int column = 0; column < 8; ++column){
            if(isOk(row,column)){
                result[row] = column;
                cal8Queens(row +1);
            }
        }
    }
    private  boolean isOk(int row,int column){
        int leftup = column - 1,rightUp = column + 1;
        for (int i = row-1; i >= 0; --i){
            if(result[i] == column) return  false;
            if(leftup >= 0){
                if(result[i] == leftup) return false;
            }
            if(rightUp < 8){
                if(result[i] == rightUp) return  false;
            }
            --leftup; ++rightUp;
        }
        return true;
    }
    public void printQueens(Integer[] result){
        for(int row = 0; row < 8; ++row){
            for(int column = 0; column < 8; ++column){
                if(result[row]!=null && result[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println("----------------");
    }

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
//        Integer[] result = new Integer[8];
//        result[1] = 4;
        eightQueens.cal8Queens(0);
//        eightQueens.printQueens(result);
    }
}
