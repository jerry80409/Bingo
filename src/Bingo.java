import java.util.Random;

/**
 * Created by jerry on 2016/4/11.
 */
public class Bingo {

    private int scale;
    private int[][] myArray;

    // 0: count bingo , 1: count bingo
    private int[][] result = new int[][]{{0, 0}, {1, 0}};


    public Bingo() {

        this.init();
        this.show();

        this.checkBingo();

        this.play();

    }

    /**
     * 產生隨機 二維陣列
     */
    public void init() {
        Random random = new Random();

        this.scale = 2 + Math.abs(random.nextInt(3));
        this.myArray = new int[this.scale][this.scale];

        for (int i = 0; i < this.myArray.length; i++) {
            for (int j = 0; j < this.myArray[i].length; j++) {
                this.myArray[i][j] = random.nextInt(2);
            }
        }

        System.out.println("電腦隨機產生一個 " + this.scale + " * " + this.scale + " 的二維布爾值陣列");

    }


    public void checkBingo() {

        // check row
        for (int[] rows : this.myArray) {
            this.countBingo(rows);
        }

        // check column
        int[] checkColumn = new int[this.scale];

        for (int rows = 0; rows < this.myArray.length; rows++) {
            for (int cell = 0; cell < this.myArray[rows].length; cell++) {
                checkColumn[cell] = this.myArray[cell][rows];
            }
            this.countBingo(checkColumn);
        }


        // check slash
        int[] leftSlash = new int[this.scale];
        int[] rightSlash = new int[this.scale];

        for (int x = 0, y = this.myArray.length - 1; x < this.myArray.length; x++, y--) {
            leftSlash[x] = this.myArray[x][x];
            rightSlash[x] = this.myArray[x][y];
        }

        this.countBingo(leftSlash);
        this.countBingo(rightSlash);

    }

    private void countBingo(int[] checkArray) {

        // 檢查 第一個元素是 0 or 1
        boolean isZero = checkArray[0] == 0;

        // 第一元素是0 且 陣列元素都相同
        if (isZero && hasBingoLine(checkArray)) {
            this.result[0][1]++;
        }

        if (!isZero && hasBingoLine(checkArray)) {
            this.result[1][1]++;
        }

    }

    /**
     * @param checkArray
     * @return
     */
    public boolean hasBingoLine(int[] checkArray) {

        // 設定比較值
        int compare = checkArray[0];

        // 比較陣列元素是否相同
        for (int item : checkArray) {
            if (compare != item) {
                return false;
            }
        }

        return true;
    }


    public void show() {

        for (int[] rows : this.myArray) {
            for (int cell : rows) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }


    public void play() {
        System.out.println("\n---");
        System.out.println("0' Bingo : " + this.result[0][1]);
        System.out.println("1' Bingo : " + this.result[1][1]);
    }


}
