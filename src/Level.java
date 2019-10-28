import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Level extends Screen {

	ArrayList<Mob> mobsToDraw;
	ArrayList<Tile> tilesToDraw;
	private int levelNum;
	public final int cellWidthHeight = 100;
	public final int xCells = 10;
	public final int yCells = 10;
	private HashMap<String, Boolean> keyMap;

	public Level(String title, int levelNum) {
		super(title);
		this.levelNum = levelNum;
		mobsToDraw = new ArrayList<Mob>();
		tilesToDraw = new ArrayList<Tile>();

//		readLevelFile();
	}

	public void giveHeroKeyMap(Hero hero) {
		hero.giveKeyMap(keyMap);
		System.out.println("KeyMap got");
	}

	public void readLevelFile() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("level" + levelNum + ".txt"));
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			return;
		}
		int posY = 0;

		// add bakground tiles before others
		for (int i = 0; i < this.xCells; i++) {
			for (int j = 0; j < this.yCells; j++) {
				Tile backgroundTile = new Air(i * this.cellWidthHeight, j * this.cellWidthHeight, this.cellWidthHeight,
						this.cellWidthHeight);
				this.tilesToDraw.add(backgroundTile);
			}
		}

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			for (int i = 0; i < line.length(); i++) {
				char toBuild = line.charAt(i);
				if (toBuild == 'H' || toBuild == 'M') {

					if (toBuild == 'H') {

						Hero newMob = new Hero(i * this.cellWidthHeight, posY * this.cellWidthHeight);
						giveHeroKeyMap(newMob);
						mobsToDraw.add(newMob);

					}

				}

				if (toBuild == 'T' || toBuild == 'E') {
					Tile newTile;
					if (toBuild == 'T') {
						newTile = new Tile(i * this.cellWidthHeight, posY * this.cellWidthHeight, this.cellWidthHeight,
								this.cellWidthHeight, "Tile.png");

						this.tilesToDraw.add(newTile);
					}
					if (toBuild == 'E') {
//						newTile = new Air(i * this.cellWidthHeight, posY * this.cellWidthHeight, this.cellWidthHeight,
//								this.cellWidthHeight);
//						
//						this.tilesToDraw.add(newTile);
						// need to put air behind all other tiles -- put in draw everything
					}

				}

				// i gives posX
				// posY gives y value(0 at top of page)
			}

			posY++;

			// PUT METHOD HERE THAT BUILDS CHARACTERS AND BLOCKS FROM CHAR USING DRAW

		}
		scanner.close();

	}

	public void drawEverything(Graphics2D g2) {

		// draw background and solids
		for (int i = 0; i < this.tilesToDraw.size(); i++) {
			ImageObserver observer = null; // is this a problem????

			Tile thisTile = this.tilesToDraw.get(i);
			g2.drawImage(thisTile.getImage(), thisTile.getX(), thisTile.getY(), thisTile.getX() + this.cellWidthHeight,
					thisTile.getY() + this.cellWidthHeight, 0, 0, thisTile.getImage().getWidth(observer),
					thisTile.getImage().getHeight(observer), observer);

		}
		// draw mobs
		for (int i = 0; i < this.mobsToDraw.size(); i++) {
			ImageObserver observer = null; // is this a problem????
			Mob thisMob = this.mobsToDraw.get(i);
			g2.drawImage(thisMob.getImage(), thisMob.getX(), thisMob.getY(), thisMob.getX() + this.cellWidthHeight,
					thisMob.getY() + this.cellWidthHeight, 0, 0, thisMob.getImage().getWidth(observer),
					thisMob.getImage().getHeight(observer), observer);
			thisMob.updateMovement();

			if (keyMap.get("left")) {
				thisMob.setXAcceleration(-10);
			}

		}

	}
	// MasterList SOMEWHERE! for letters
	// H is = Hero

}
