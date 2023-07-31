public class Game {

    private boolean won = false;
    private boolean lost = false;
    private boolean menu = true;
    private boolean difficulty = false;

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    private boolean play = false;
    private boolean exit = false;
    private final int width;
    private final int height;
    private int gridSize;
    private int mineCount;
    private Handler handler;
    private MenuWindow window;



    public boolean isWon() {
        return won;
    }

    public boolean isLost() {
        return lost;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public MenuWindow getWindow() {
        return window;
    }


    public int getGridSize() {
        return gridSize;
    }

    public int getMineCount() {
        return mineCount;
    }

    public boolean isMenu() {
        return menu;
    }

    public void setMenu(boolean menu) {
        this.menu = menu;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public boolean isDifficulty() {
        return difficulty;
    }

    public void setDifficulty(boolean difficulty) {
        this.difficulty = difficulty;
    }

    public Game(int width, int height, int gridSize, int mineCount, Handler handler) {
        this.width = width;
        this.height = height;
        this.gridSize = gridSize;
        this.mineCount = mineCount;
        this.handler = handler;
    }
    //creates JFrame and Grid Window
    public void runGame(Game game) {
        this.window = new MenuWindow(GameInfo.WIDTH, GameInfo.HEIGHT, "Minesweeper", handler,
                game);
        window.initializeMainMenu();
    }
    public void setMainMenu() {

        window.resetWindow();
        window.initializeMainMenu();
        won = false;
        lost = false;
        menu = false;
    }
    public void playGame() {
        menu = true;
        if (play) {
            window.getFrame().dispose();
            mineCount = GameInfo.getMINECOUNT();
            gridSize = GameInfo.getGridsize();
            window.initializeBoard();
            play = false;
        }
    }
    public void setDifficulty() {
        window.getFrame().dispose();
        window.initializeDifficultyMenu();
        difficulty = false;
    }
}