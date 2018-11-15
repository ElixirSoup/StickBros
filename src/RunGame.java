import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RunGame extends JFrame implements KeyListener, ActionListener {
	
	private Container content;
	private JLabel stickManLabel;
	private ImageIcon stickManImage;
	private Character stickMan;
	private JLabel enemyLabel;
	private ImageIcon enemyImage;
	private Character enemy;
	private Boolean movementFlag;
	private Boolean movementFlag2;
	private JPanel ground;
	private Rectangle groundHbox;
	private JPanel[] platforms;
	private Rectangle[] platformsHbox;
	private Timer time;
	private MoveEnemy AI;
	
	private JLabel modeSelectText;
	private JButton singlePlayerBtn;
	private JButton localMultiplayerBtn;
	private JPanel modeSelectBack;
	
	private JLabel player1Dmg;
	private JLabel player2Dmg;
	
	private JPanel[] p1LivesDisplay;
	private JPanel[] p2LivesDisplay;
	private JPanel p1LivesBox;
	private JPanel p2LivesBox;
	
	private int mode;
	private int lives;
	private JPanel livesPanel;
	private JPanel livesPanel2;
	private JButton livesUp;
	private JButton livesDn;
	private JLabel livesTxt;
	
	public RunGame() {
		
		movementFlag = false;
		movementFlag2 = false;
		
		mode = 0;
		lives = 4;
		
		stickManLabel = new JLabel();
		stickMan = new Character("P1");
		stickManImage = new ImageIcon();
		
		enemyLabel = new JLabel();
		enemy = new Character("P2");
		enemyImage = new ImageIcon();
		
		player1Dmg = new JLabel("0");
		player2Dmg = new JLabel("0");
		
		p1LivesBox = new JPanel();
		p1LivesBox.setBounds(300, 875, 300, 55);
		p1LivesBox.setLayout(new GridLayout(1,5,10,10));
		p1LivesBox.setBackground(Color.GRAY);
		
		p2LivesBox = new JPanel();
		p2LivesBox.setBounds(1320, 875, 300, 55);
		p2LivesBox.setLayout(new GridLayout(1,5,10,10));
		p2LivesBox.setBackground(Color.GRAY);
		
		AI = new MoveEnemy(stickMan, enemy);
		
		time = new Timer(stickMan, enemy);
		
		modeSelectText = new JLabel("     Stick Bros");
		modeSelectText.setFont(new Font("Sans-Serif", Font.BOLD, 70));
		singlePlayerBtn = new JButton("Single Player");
		localMultiplayerBtn = new JButton("Local Multiplayer");
		modeSelectBack = new JPanel();
		
		livesPanel = new JPanel();
		livesPanel.setLayout(new GridLayout(1,2,10,10));
		livesPanel2 = new JPanel();
		livesPanel2.setLayout(new GridLayout(2,1,10,10));
		livesUp = new JButton("^");
		livesDn = new JButton("v");
		livesPanel2.add(livesUp);
		livesPanel2.add(livesDn);
		livesUp.addActionListener(this);
		livesDn.addActionListener(this);
		livesPanel.add(livesPanel2);
		livesTxt = new JLabel("Lives: " + lives);
		livesTxt.setFont(new Font("Sans-Serif", Font.BOLD, 50));
		livesPanel.add(livesTxt);
		
		modeSelectBack.setBounds(606, 200, 606, 600);
		modeSelectBack.setBackground(Color.LIGHT_GRAY);
		modeSelectBack.setLayout(new GridLayout(4,1,10,10));
		modeSelectBack.add(modeSelectText);
		modeSelectBack.add(livesPanel);
		modeSelectBack.add(singlePlayerBtn);
		modeSelectBack.add(localMultiplayerBtn);
		singlePlayerBtn.addActionListener(this);
		localMultiplayerBtn.addActionListener(this);
		
		ground = new JPanel();
		groundHbox = new Rectangle(200, 696, 1520, 8000);
		ground.setBounds(groundHbox);
		ground.setBackground(Color.GRAY);
		platformsHbox = new Rectangle[3];
		platformsHbox[0] = new Rectangle(400,500,306,20);
		platformsHbox[1] = new Rectangle(806,300,306,20);
		platformsHbox[2] = new Rectangle(1212,500,306,20);
		platforms = new JPanel[3];
		platforms[0] = new JPanel();
		platforms[1] = new JPanel();
		platforms[2] = new JPanel();
		platforms[0].setBounds(platformsHbox[0]);
		platforms[1].setBounds(platformsHbox[1]);
		platforms[2].setBounds(platformsHbox[2]);
		platforms[0].setBackground(Color.GRAY);
		platforms[1].setBackground(Color.GRAY);
		platforms[2].setBackground(Color.GRAY);
		
		player1Dmg.setFont(new Font("Sans-Serif", Font.BOLD, 100));
		player1Dmg.setForeground(Color.WHITE);
		player1Dmg.setSize(300, 300);
		player1Dmg.setLocation(300, 650);
		
		player2Dmg.setFont(new Font("Sans-Serif", Font.BOLD, 100));
		player2Dmg.setForeground(Color.WHITE);
		player2Dmg.setSize(300, 300);
		player2Dmg.setLocation(1320, 650);
		
		stickMan.setDir(0);
		stickMan.setAlive(true);
		stickMan.setJump(false);
		stickMan.setGround(groundHbox);
		stickMan.setStickMan(stickManLabel);
		stickMan.setFrame(0, 2, 3);
		stickMan.setPlats(platformsHbox[0], platformsHbox[1], platformsHbox[2]);
		stickMan.setEnemy(enemy);
		stickMan.setDmgLabel(player1Dmg);
		stickManImage.setImage(stickMan.getFrame());
		stickManLabel.setIcon(stickManImage);
		stickManLabel.setSize(96, 96);
		stickManLabel.setLocation(200, 200);
		
		enemy.setDir(0);
		enemy.setAlive(true);
		enemy.setJump(false);
		enemy.setGround(groundHbox);
		enemy.setStickMan(enemyLabel);
		enemy.setFrame(0, 3, 3);
		enemy.setPlats(platformsHbox[0], platformsHbox[1], platformsHbox[2]);
		enemy.setEnemy(stickMan);
		enemy.setDmgLabel(player2Dmg);
		enemyImage.setImage(enemy.getFrame());
		enemyLabel.setIcon(enemyImage);
		enemyLabel.setSize(96, 96);
		enemyLabel.setLocation(1620, 200);
		
		content = getContentPane();
		
		setSize(1920,1080);
		content.setBackground(Color.WHITE);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content.setFocusable(true);
		content.addKeyListener(this);
		
		add(modeSelectBack);
		
		add(stickManLabel);
		
		add(player1Dmg);
		add(player2Dmg);
		add(p1LivesBox);
		add(p2LivesBox);
		
		add(enemyLabel);
		add(ground);
		add(platforms[0]);
		add(platforms[1]);
		add(platforms[2]);
		
		
	}
	
	public static void main(String[] args) {
		
		RunGame game = new RunGame();
		game.setResizable(false);
		game.setVisible(true);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(mode != 0) {
		
			if(e.getKeyCode() == KeyEvent.VK_D) {
				if(!movementFlag) {
					stickMan.setDir(1);
					movementFlag = true;
				}
			}
			
			if(e.getKeyCode() == KeyEvent.VK_A) {
				if(!movementFlag) {
					stickMan.setDir(2);
					movementFlag = true;
				}
			}
			
			if(mode == 1) {
			
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					stickMan.setJump(true);
				}
				
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					stickMan.setPunch(true);
					stickMan.setPunchDir(1);
				}
				
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					stickMan.setPunch(true);
					stickMan.setPunchDir(2);
				}
				
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					stickMan.setPunch(true);
					stickMan.setPunchDir(3);
				}
			
			} else if(mode == 2) {
				
				if(e.getKeyCode() == KeyEvent.VK_W) {
					stickMan.setJump(true);
				}
				if(e.getKeyCode() == KeyEvent.VK_V) {
					stickMan.setPunch(true);
					stickMan.setPunchDir(1);
				}
				if(e.getKeyCode() == KeyEvent.VK_N) {
					stickMan.setPunch(true);
					stickMan.setPunchDir(2);
				}
				if(e.getKeyCode() == KeyEvent.VK_B) {
					stickMan.setPunch(true);
					stickMan.setPunchDir(3);
				}
				
				if(e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
					if(!movementFlag2) {
						enemy.setDir(1);
						movementFlag2 = true;
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
					if(!movementFlag2) {
						enemy.setDir(2);
						movementFlag2 = true;
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
					enemy.setJump(true);
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					enemy.setPunch(true);
					enemy.setPunchDir(1);
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					enemy.setPunch(true);
					enemy.setPunchDir(2);
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					enemy.setPunch(true);
					enemy.setPunchDir(3);
				}
				
			}
		
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(mode != 0) {
		
			if(e.getKeyCode() == KeyEvent.VK_D) {
				stickMan.setDir(0);
				movementFlag = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_A) {
				stickMan.setDir(0);
				movementFlag = false;
			}
			
			if(mode == 2) {
			
				if(e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
					enemy.setDir(0);
					movementFlag2 = false;
				}
				if(e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
					enemy.setDir(0);
					movementFlag2 = false;
				}
			
			}
		
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == singlePlayerBtn) {
			
			stickMan.setDir(0);
			stickMan.setAlive(true);
			stickMan.setDmg(0);
			enemy.setDir(0);
			enemy.setAlive(true);
			enemy.setDmg(0);
			stickManLabel.setLocation(200, 200);
			enemyLabel.setLocation(1620, 200);
			
			player1Dmg.setText("0");
			player2Dmg.setText("0");
			player1Dmg.setForeground(Color.WHITE);
			player2Dmg.setForeground(Color.WHITE);
			
			p1LivesBox.removeAll();
			p2LivesBox.removeAll();
			
			p1LivesDisplay = new JPanel[lives];
			p2LivesDisplay = new JPanel[lives];
			for(int i = 0; i < lives; i++) {
				p1LivesDisplay[i] = new JPanel();
				p1LivesDisplay[i].setSize(50, 50);
				p1LivesDisplay[i].setBackground(Color.BLUE);
				p1LivesBox.add(p1LivesDisplay[i]);
				
				p2LivesDisplay[i] = new JPanel();
				p2LivesDisplay[i].setSize(50, 50);
				p2LivesDisplay[i].setBackground(Color.RED);
				p2LivesBox.add(p2LivesDisplay[i]);
			}
			stickMan.setLives(lives);
			stickMan.setLivesDisplay(p1LivesDisplay);
			enemy.setLives(lives);
			enemy.setLivesDisplay(p2LivesDisplay);
			
			time.setModeSelect(modeSelectBack);
			time.setModeText(modeSelectText);
			time.setMoveEnemy(AI);
			stickMan.startThread();
			enemy.startThread();
			time.startThread();
			
			AI.startThread();
			
			modeSelectBack.setVisible(false);
			
			mode = 1;
		}
		if(e.getSource() == localMultiplayerBtn) {
			
			stickMan.setDir(0);
			stickMan.setAlive(true);
			stickMan.setDmg(0);
			enemy.setDir(0);
			enemy.setAlive(true);
			enemy.setDmg(0);
			stickManLabel.setLocation(200, 200);
			enemyLabel.setLocation(1620, 200);
			
			player1Dmg.setText("0");
			player2Dmg.setText("0");
			player1Dmg.setForeground(Color.WHITE);
			player2Dmg.setForeground(Color.WHITE);
			
			p1LivesBox.removeAll();
			p2LivesBox.removeAll();
			
			p1LivesDisplay = new JPanel[lives];
			p2LivesDisplay = new JPanel[lives];
			for(int i = 0; i < lives; i++) {
				p1LivesDisplay[i] = new JPanel();
				p1LivesDisplay[i].setSize(50, 50);
				p1LivesDisplay[i].setBackground(Color.BLUE);
				p1LivesBox.add(p1LivesDisplay[i]);
				
				p2LivesDisplay[i] = new JPanel();
				p2LivesDisplay[i].setSize(50, 50);
				p2LivesDisplay[i].setBackground(Color.RED);
				p2LivesBox.add(p2LivesDisplay[i]);
			}
			stickMan.setLives(lives);
			stickMan.setLivesDisplay(p1LivesDisplay);
			enemy.setLives(lives);
			enemy.setLivesDisplay(p2LivesDisplay);
			
			time.setModeSelect(modeSelectBack);
			time.setModeText(modeSelectText);
			stickMan.startThread();
			enemy.startThread();
			time.startThread();
			modeSelectBack.setVisible(false);
			
			mode = 2;
		}
		
		if(e.getSource() == livesUp) {
			lives += 1;
			if(lives > 5) {
				lives = 1;
			}
			livesTxt.setText("Lives: " + lives);
		}
		if(e.getSource() == livesDn) {
			lives -= 1;
			if(lives < 1) {
				lives = 5;
			}
			livesTxt.setText("Lives: " + lives);
		}
		
	}

}
