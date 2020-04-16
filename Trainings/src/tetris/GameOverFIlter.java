package tetris;

public class GameOverFIlter {
    private Model model;
    private boolean gameOver;

    public GameOverFIlter(Model model)
    {
        this.model = model;
        gameOver = false;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean getGameOver()
    {
        return this.gameOver;
    }

    public void moveLeft()
    {
        if (!gameOver) model.moveLeft();
    }

    public void moveRight()
    {
        if (!gameOver) model.moveRight();
    }

    public void moveDown()
    {
        if (!gameOver) model.moveDown();
    }
    public void dropDown()
    {
        if (!gameOver) model.dropDown();
    }

    public void rotateLeft() {
        if (!gameOver) model.rotateLeft();
    }
}