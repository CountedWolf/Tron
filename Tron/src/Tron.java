import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tron extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;

	private static JLabel array[][];

	private int direction;
	private int direction2;
	private final int UP = 0;
	private final int DOWN = 1;
	private final int LEFT = 2;
	private final int RIGHT = 3;
	private int x = 16;
	private int y = 24;
	private int x2 = 16;
	private int y2 = 8;
	private int difficulty;
	private int retry;

	public static int input(String message, String Playagain, String Exit) {
		String buttons[] = { Playagain, Exit };

		return JOptionPane.showOptionDialog(null, message, "Tron", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, buttons, buttons);

	}

	public Tron() {
		direction2 = UP;
		direction = UP;
		String buttons[] = { "Hard", "Medium", "Easy" }; // 2=easy 1=medium
															// 0=hard

		difficulty = JOptionPane.showOptionDialog(null, "Difficulty", "Difficulty setting", JOptionPane.NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]);

		if (difficulty == 2) {
			difficulty = 100;
		} else if (difficulty == 1) {
			difficulty = 75;
		} else if (difficulty == 0) {
			difficulty = 40;
		}

		array = new JLabel[32][32];

		JPanel bluePanel = new JPanel();
		bluePanel.setPreferredSize(new Dimension(650, 650));

		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 32; j++) {
				array[i][j] = new JLabel();
				array[i][j].setOpaque(true);
				array[i][j].setBackground(Color.BLACK);
				array[i][j].setPreferredSize(new Dimension(15, 15));

				bluePanel.add(array[i][j]);
			}
		}
		
		addWindowListener(new WindowAdapter()
		{ public void windowClosing(WindowEvent e)
			{ 
				System.exit(0);
			}
		});
		addKeyListener(this);
		bluePanel.setBackground(Color.BLACK);
		add(bluePanel);
		setSize(new Dimension(675, 685));
		setVisible(true);

		snakePosition();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			direction = RIGHT;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			direction = LEFT;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			direction = UP;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			direction = DOWN;
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void snakePosition() {
		while (true) {
			try {
				Thread.sleep(difficulty);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

			if (direction == UP) {
				x--;
			}
			else if (direction == DOWN) {
				x++;
			}
			else if (direction == RIGHT) {
				y++;
			}
			else if (direction == LEFT) {
				y--;
			}

			if (x == -1 || x >= 32 || y == -1 || y >= 32) {
				JOptionPane.showMessageDialog(null, "GAME OVER");
				input("Would you like to Play again or Exit?", "Play again", "Exit");
				break;

			}

			if (array[x][y].getBackground().equals(Color.ORANGE)) {
				array[x][y].setBackground(Color.RED);
				JOptionPane.showMessageDialog(null, "GAME OVER");
				input("Would you like to Play again or Exit?", "Play again", "Exit");
				break;
			}

			if (array[x][y].getBackground().equals(Color.CYAN)) {
				array[x][y].setBackground(Color.RED);
				JOptionPane.showMessageDialog(null, "GAME OVER");
				input("Would you like to Play again or Exit?", "Play again", "Exit");
				break;
			}

			array[x][y].setBackground(Color.ORANGE);

			if (direction2 == UP) {
				x2--;
			} else if (direction2 == DOWN) {
				x2++;
			} else if (direction2 == RIGHT) {
				y2++;
			} else if (direction2 == LEFT) {
				y2--;
			}
			/////////////
			if (Math.pow(x2 - x, 2) > Math.pow(y2 - y, 2)) {
				if (x2 > x) {
					direction2 = UP;
				}

				else {
					direction2 = DOWN;
				}
			} else {
				if (y2 > y) {
					direction2 = LEFT;
				}

				else {
					direction2 = RIGHT;
				}
			}

			for (int i = 0; i < 4; i++) {
				if (direction2 == UP && x2 != 0 && !array[x2 - 1][y2].getBackground().equals(Color.BLACK)) {
					direction2 = LEFT;
				} else if (direction2 == DOWN && x2 != 31 && !array[x2 + 1][y2].getBackground().equals(Color.BLACK)) {
					direction2 = RIGHT;
				} else if (direction2 == RIGHT && y2 != 31 && !array[x2][y2 + 1].getBackground().equals(Color.BLACK)) {
					direction2 = UP;
				} else if (direction2 == LEFT && y2 != 0 && !array[x2][y2 - 1].getBackground().equals(Color.BLACK)) {
					direction2 = DOWN;
				}
			}

			if (y2 == 0 && x2 == 31) {
				direction2 = RIGHT;
			} else if (y2 == 31 && x2 == 31) {
				direction2 = UP;
			} else if (y2 == 31 && x2 == 0) {
				direction2 = LEFT;
			} else if (y2 == 0 && x2 == 0) {
				direction2 = DOWN;
			} else if (y2 == 31 && direction2 == RIGHT) {
				direction2 = UP;
			} else if (y2 == 0 && direction2 == LEFT) {
				direction2 = DOWN;
			} else if (x2 == 31 && direction2 == DOWN) {
				direction2 = RIGHT;
			} else if (x2 == 0 && direction2 == UP) {
				direction2 = LEFT;
			}

			if (x2 == -1 || x2 >= 32 || y2 == -1 || y2 >= 32) {
				JOptionPane.showMessageDialog(null, "You won!");
				input("Would you like to Play again or Exit?", "Play again", "Exit");
				if (retry == 2) {
					break;
				}
			}

			if (array[x2][y2].getBackground().equals(Color.ORANGE)) {
				JOptionPane.showMessageDialog(null, "You won!");
				input("Would you like to Play again or Exit?", "Play again", "Exit");
				if (retry == 2) {
					break;
				}
			}

			if (array[x2][y2].getBackground().equals(Color.CYAN)) {
				JOptionPane.showMessageDialog(null, "You won!");
				retry = input("Would you like to Play again or Exit?", "Play again", "Exit");
				if (retry == 2) {
					break;
				}
			}

			if (x == -1 || x >= 32 || y == -1 || y >= 32) {
				JOptionPane.showMessageDialog(null, "You won!");
				input("Would you like to Play again or Exit?", "Play again", "Exit");
				if (retry == 2) {
					break;
				}

			}

			array[x2][y2].setBackground(Color.CYAN);
		}
	}

	public static void main(String[] args) {
		new Tron();
	}
}