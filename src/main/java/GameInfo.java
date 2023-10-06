public class GameInfo {

    public static String difficulty = "easy";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    private static int gridsize = determineGridSize(difficulty);
    public static int MINECOUNT = determineMinecount(difficulty);

    private static int determineGridSize(String difficulty) {
        if (difficulty.equals("easy")) {
            return 10;
        } else if (difficulty.equals("moderate")) {
            return 12;
        } else {
            return 18;
        }
    }


    private static int determineMinecount(String difficulty) {
        if (difficulty.equals("easy")) {
            return 10;
        } else if (difficulty.equals("moderate")) {
            return 22;
        } else {
            return 65;
        }
    }

    public static int getGridsize() {
        return gridsize;
    }

    public static void setGridsize(int gridsize) {
        GameInfo.gridsize = gridsize;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String difficulty) {
        GameInfo.difficulty = difficulty;
        GameInfo.setMINECOUNT(determineMinecount(difficulty));
        GameInfo.setGridsize(determineGridSize(difficulty));
    }

    public static int getMINECOUNT() {
        return MINECOUNT;
    }

    public static void setMINECOUNT(int MINECOUNT) {
        GameInfo.MINECOUNT = MINECOUNT;
    }
}
