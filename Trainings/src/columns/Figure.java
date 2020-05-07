package columns;
import java.applet.*;
import java.awt.*;
import java.util.*;


class Figure {
	static int column=Columns.FIELD_WIDTH/2+1, row=1, colorsOfFigureBoxes[]=new int[4];
	static Random r = new Random();

	Figure()
	{
		column = Columns.FIELD_WIDTH/2+1;
		row = 1;
		colorsOfFigureBoxes[0] = 0;
		colorsOfFigureBoxes[1] = (int)(Math.abs(r.nextInt())%7+1);
		colorsOfFigureBoxes[2] = (int)(Math.abs(r.nextInt())%7+1);
		colorsOfFigureBoxes[3] = (int)(Math.abs(r.nextInt())%7+1);
	}
}