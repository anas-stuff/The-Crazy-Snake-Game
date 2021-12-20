package code.media;

public enum SFX {
    // Sound effects
    BlockShow("blockShow.mp3"),
    BlockHide("blockHide.mp3"),
    SnakeEat("snakeEat.mp3"),
    SnakeCollided("snakeCollided.mp3"),
    Win("win.mp3"),
    Lose("Lose.mp3"),
    ButtonClick("buttonClick.mp3");

    private final String fileName;

    SFX(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
