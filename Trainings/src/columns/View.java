package columns;

import java.awt.Color;
import java.awt.Graphics;

public class View {
	static final int FIELD_DEPTH = 15, FIELD_WIDTH = 7, FIELD_LEFT_OFFSET = 2, FIELD_TOP_OFFSET = 2,
			GAME_NUMBOX_CONSTANT = 3;

	private DrawViewGraphics viewGraphics;
	private DrawPlayerLevelInformation viewLevel;
	private DrawPlayerScoreInformation viewScore;

	private Color colors[] = { Color.black, Color.cyan, Color.blue, Color.red, Color.green, Color.yellow, Color.pink,
			Color.magenta, Color.black };

	public View(DrawViewGraphics viewGraphics, DrawPlayerLevelInformation viewLevel,
			DrawPlayerScoreInformation viewScore) {
		this.viewGraphics = viewGraphics;
		this.viewLevel = viewLevel;
		this.viewScore = viewScore;
	}

	public void drawBox(int col, int row, int color) {
		viewGraphics.drawRect(row, col, color);
	}

	public void drawField(int[][] gameField) {
		for (int row = 1; row <= FIELD_DEPTH; row++) {
			for (int col = 1; col <= FIELD_WIDTH; col++) {
				drawBox(col, row, gameField[col][row]);
			}
		}
	}

	public void drawFigure(Figure figure) {
		for (int i = 0; i < GAME_NUMBOX_CONSTANT; i++) {
			drawBox(figure.column, figure.row + i, figure.colorsOfFigureBoxes[i + 1]);
		}
	}

	void showLevel(int currentLevel) {
		viewLevel.showLevel(currentLevel);
	}

	void showScore(long playerScore) {
		viewScore.showScore(playerScore);
	}
	
	//Not mandatory
//		 void showHelp() {
//        graphics.setColor(Color.black);
//        graphics.drawString(" Keys available:",200-FIELD_LEFT_OFFSET,102);
//        graphics.drawString("Roll Box Up:     ",200-FIELD_LEFT_OFFSET,118);
//        graphics.drawString("Roll Box Down:   ",200-FIELD_LEFT_OFFSET,128);
//        graphics.drawString("Figure Left:     ",200-FIELD_LEFT_OFFSET,138);
//        graphics.drawString("Figure Right:    ",200-FIELD_LEFT_OFFSET,148);
//        graphics.drawString("Level High/Low: +/-",200-FIELD_LEFT_OFFSET,158);
//        graphics.drawString("Drop Figure:   space",200-FIELD_LEFT_OFFSET,168);
//        graphics.drawString("Pause:           P",200-FIELD_LEFT_OFFSET,180);
//        graphics.drawString("Quit:     Esc or Q",200-FIELD_LEFT_OFFSET,190);
//    }
}
