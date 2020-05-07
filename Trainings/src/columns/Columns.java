package columns;

import java.applet.*;
import java.awt.*;
import java.util.*;

public class Columns extends Applet implements Runnable {
	static final int FIELD_LEFT_OFFSET = 2, FIELD_TOP_OFFSET = 2, SIZE_OF_COMPONENTS = 25, MAX_LEVEL = 7,
			TimeShift = 250, FigToDrop = 33, MinTimeShift = 200, FIELD_DEPTH = 15, FIELD_WIDTH = 7;

	private View view;

	private Color colors[] = { Color.black, Color.cyan, Color.blue, Color.red, Color.green, Color.yellow, Color.pink,
			Color.magenta, Color.black };

	int currentLevel, k, playerKeyPressed;
	long playerScore, playerScoreAddition;
	Figure figure;
	int newField[][], oldField[][];
	boolean NoChanges = true, KeyPressed = false;

	Thread thread = null;

	void CheckNeighbours(int a, int b, int c, int d, int i, int j) {
		if ((newField[j][i] == newField[a][b]) && (newField[j][i] == newField[c][d])) {
			oldField[a][b] = 0;
			view.drawBox(a, b, 8);
			oldField[j][i] = 0;
			view.drawBox(j, i, 8);
			oldField[c][d] = 0;
			view.drawBox(c, d, 8);
			NoChanges = false;
			playerScore += (currentLevel + 1) * 10;
			k++;
		}
		;
	}

	void Delay(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
		}
		;
	}

	void DropFigure(Figure f) {
		int zz;
		if (f.row < FIELD_DEPTH - 2) {
			zz = FIELD_DEPTH;
			while (newField[f.column][zz] > 0)
				zz--;
			playerScoreAddition = (((currentLevel + 1) * (FIELD_DEPTH * 2 - f.row - zz) * 2) % 5) * 5;
			f.row = zz - 2;
		}
	}

	boolean isFieldFull() {
		for (int col = 1; col <= FIELD_WIDTH; col++) {
			if (newField[col][3] > 0)
				return true;
		}
		return false;
	}

	void HideFigure(Figure figure) {
		for (int i = 0; i < 3; i++) {
			view.drawBox(figure.column, figure.row + i, 0);
		}
	}

	public void init() {
		newField = new int[FIELD_WIDTH + 2][FIELD_DEPTH + 2];
		oldField = new int[FIELD_WIDTH + 2][FIELD_DEPTH + 2];
		Graphics graphics = getGraphics();
		view = new View((int row, int col, int color) -> {
			graphics.setColor(colors[color]);
			graphics.fillRect(FIELD_LEFT_OFFSET + col * SIZE_OF_COMPONENTS - SIZE_OF_COMPONENTS,
					FIELD_TOP_OFFSET + row * SIZE_OF_COMPONENTS - SIZE_OF_COMPONENTS, SIZE_OF_COMPONENTS,
					SIZE_OF_COMPONENTS);
			graphics.setColor(Color.black);
			graphics.drawRect(FIELD_LEFT_OFFSET + col * SIZE_OF_COMPONENTS - SIZE_OF_COMPONENTS,
					FIELD_TOP_OFFSET + row * SIZE_OF_COMPONENTS - SIZE_OF_COMPONENTS, SIZE_OF_COMPONENTS,
					SIZE_OF_COMPONENTS);
		}, (int currentLevel) -> {
			graphics.setColor(Color.black);
			graphics.clearRect(FIELD_LEFT_OFFSET + 100, 390, 100, 20);
			graphics.drawString("Level: " + currentLevel, FIELD_LEFT_OFFSET + 100, 400);
		}, (long playerScore) -> {
			graphics.setColor(Color.black);
			graphics.clearRect(FIELD_LEFT_OFFSET, 390, 100, 20);
			graphics.drawString("Score: " + playerScore, FIELD_LEFT_OFFSET, 400);
		});
	}

	public boolean keyDown(Event e, int k) {
		KeyPressed = true;
		playerKeyPressed = e.key;
		return true;
	}

	public boolean lostFocus(Event e, Object w) {
		KeyPressed = true;
		playerKeyPressed = 'P';
		return true;
	}

	void PackField() {
		for (int col = 1; col <= FIELD_WIDTH; col++) {
			int n = FIELD_DEPTH;
			for (int row = FIELD_DEPTH; row > 0; row--) {
				if (oldField[col][row] > 0) {
					newField[col][row] = oldField[col][row];
					n--;
				}
			}
			;
			for (int j = n; j > 0; j--)
				newField[col][j] = 0;
		}
	}

	public void paint(Graphics graphics) {
		graphics.setColor(Color.black);
		view.showLevel(currentLevel);
		view.showScore(playerScore);
		view.drawField(newField);
		view.drawFigure(figure);
		requestFocus();
	}

	void PasteFigure(Figure figure) {
		for (int i = 0; i < 3; i++) {
			newField[figure.column][figure.row + i] = figure.colorsOfFigureBoxes[i + 1];
		}
	}

	public void run() {
		for (int col = 0; col < FIELD_WIDTH + 1; col++) {
			for (int row = 0; row < FIELD_DEPTH + 1; row++) {
				newField[col][row] = 0;
				oldField[col][row] = 0;
			}
		}
		currentLevel = 0;
		playerScore = 0;
		int i = 0;
		k = 0;
		requestFocus();
		do {
			long tc = System.currentTimeMillis();
			new Figure();
			view.drawFigure(figure);
			while ((figure.row < FIELD_DEPTH - 2) && (newField[figure.column][figure.row + 3] == 0)) {
				if ((int) (System.currentTimeMillis() - tc) > (MAX_LEVEL - currentLevel) * TimeShift + MinTimeShift) {
					tc = System.currentTimeMillis();
					HideFigure(figure);
					figure.row++;
					view.drawFigure(figure);
				}
				playerScoreAddition = 0;
				do {
					Delay(50);
					if (KeyPressed) {
						KeyPressed = false;
						switch (playerKeyPressed) {
						case Event.LEFT:
							if ((figure.column > 1) && (newField[figure.column - 1][figure.row + 2] == 0)) {
								HideFigure(figure);
								figure.column--;
								view.drawFigure(figure);
							}
							break;
						case Event.RIGHT:
							if ((figure.column < FIELD_WIDTH) && (newField[figure.column + 1][figure.row + 2] == 0)) {
								HideFigure(figure);
								figure.column++;
								view.drawFigure(figure);
							}
							break;
						case Event.UP:
							i = figure.colorsOfFigureBoxes[1];
							figure.colorsOfFigureBoxes[1] = figure.colorsOfFigureBoxes[2];
							figure.colorsOfFigureBoxes[2] = figure.colorsOfFigureBoxes[3];
							figure.colorsOfFigureBoxes[3] = i;
							view.drawFigure(figure);
							break;
						case Event.DOWN:
							i = figure.colorsOfFigureBoxes[1];
							figure.colorsOfFigureBoxes[1] = figure.colorsOfFigureBoxes[3];
							figure.colorsOfFigureBoxes[3] = figure.colorsOfFigureBoxes[2];
							figure.colorsOfFigureBoxes[2] = i;
							view.drawFigure(figure);
							break;
						case ' ':
							HideFigure(figure);
							DropFigure(figure);
							view.drawFigure(figure);
							tc = 0;
							break;
						case 'P':
						case 'p':
							while (!KeyPressed) {
								HideFigure(figure);
								Delay(500);
								view.drawFigure(figure);
								Delay(500);
							}
							tc = System.currentTimeMillis();
							break;
						case '-':
							if (currentLevel > 0)
								currentLevel--;
							k = 0;
							view.showLevel(currentLevel);
							break;
						case '+':
							if (currentLevel < MAX_LEVEL)
								currentLevel++;
							k = 0;
							view.showLevel(currentLevel);
							break;
						}
					}
				} while ((int) (System.currentTimeMillis() - tc) <= (MAX_LEVEL - currentLevel) * TimeShift
						+ MinTimeShift);
			}
			;
			PasteFigure(figure);
			do {
				NoChanges = true;
				TestField();
				if (!NoChanges) {
					Delay(500);
					PackField();
					view.drawField(newField);
					playerScore += playerScoreAddition;
					view.showScore(playerScore);
					if (k >= FigToDrop) {
						k = 0;
						if (currentLevel < MAX_LEVEL)
							currentLevel++;
						view.showLevel(currentLevel);
					}
				}
			} while (!NoChanges);
		} while (!isFieldFull());
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		if (thread != null) {
			thread.stop();
			thread = null;
		}
	}

	void TestField() {
		for (int row = 1; row <= FIELD_DEPTH; row++) {
			for (int col = 1; col <= FIELD_WIDTH; col++) {
				oldField[col][row] = newField[col][row];
			}
		}
		for (int col = 1; col <= FIELD_DEPTH; col++) {
			for (int row = 1; row <= FIELD_WIDTH; row++) {
				if (newField[row][col] > 0) {
					CheckNeighbours(row, col - 1, row, col + 1, col, row);
					CheckNeighbours(row - 1, col, row + 1, col, col, row);
					CheckNeighbours(row - 1, col - 1, row + 1, col + 1, col, row);
					CheckNeighbours(row + 1, col - 1, row - 1, col + 1, col, row);
				}
			}
		}
	}
}