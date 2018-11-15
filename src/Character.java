import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Character implements Runnable {
	
	private static final int TILE = 32;
	private BufferedImage spriteSheet;
	private Image frame;
	
	private Thread moveThread;
	private int lives;
	private JPanel[] livesDisplay;
	private int x;
	private int y;
	private int animateFrame;
	private int dir;
	private int punchDir;
	private int jumpH;
	private int mm;
	private int dmg;
	private Boolean inAir;
	private Boolean punch;
	private Boolean alive;
	private Boolean jump;
	private Rectangle r;
	private Rectangle punchBox;
	private Rectangle ground;
	private Rectangle[] platforms;
	private JLabel stickMan;
	private int delay;
	private Boolean loop;
	private String name;
	
	private JLabel dmgLabel;
	
	private Character enemy;
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	public void setSpriteSheet(String spriteSheet) {
		try {
			this.spriteSheet = ImageIO.read(getClass().getResource(spriteSheet));
		} catch (IOException e) {
			e.printStackTrace();
			this.spriteSheet = null;
		}
	}
	
	public Image getFrame() {
		return frame;
	}
	public void setFrame(int x, int y, int size) {
		this.frame = this.spriteSheet.getSubimage(TILE*x, TILE*y, TILE, TILE);
		this.frame = this.frame.getScaledInstance(TILE*size, TILE*size, Image.SCALE_DEFAULT);
	}
	
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public JPanel getLivesDisplay(int i) {
		return livesDisplay[i];
	}
	public void setLivesDisplay(JPanel[] livesDisplay) {
		this.livesDisplay = livesDisplay;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getAnimateFrame() {
		return animateFrame;
	}
	public void setAnimateFrame(int animateFrame) {
		this.animateFrame = animateFrame;
	}
	
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	
	public int getPunchDir() {
		return punchDir;
	}
	public void setPunchDir(int punchDir) {
		this.punchDir = punchDir;
	}
	
	public int getJumpH() {
		return jumpH;
	}
	public void setJumpH(int jumpH) {
		this.jumpH = jumpH;
	}
	
	public int getMm() {
		return mm;
	}
	public void setMm(int mm) {
		this.mm = mm;
	}
	
	public int getDmg() {
		return dmg;
	}
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
	
	public Boolean getInAir() {
		return inAir;
	}
	public void setInAir(Boolean inAir) {
		this.inAir = inAir;
	}
	
	public Boolean getPunch() {
		return punch;
	}
	public void setPunch(Boolean punch) {
		this.punch = punch;
	}
	
	public Boolean getAlive() {
		return alive;
	}
	public void setAlive(Boolean alive) {
		this.alive = alive;
	}
	
	public Boolean getJump() {
		return jump;
	}
	public void setJump(Boolean jump) {
		this.jump = jump;
	}
	
	public Rectangle getRec() {
		return r;
	}
	public void updateRec(int x, int y) {
		r.x = x + 33;
		r.y = y + 9;
	}
	
	public Rectangle getPunchBox() {
		return punchBox;
	}
	public void updatePunchBox(int x, int y) {
		punchBox.x = x + 33;
		punchBox.y = y + 9;
	}
	
	public Rectangle getGround() {
		return ground;
	}
	public void setGround(Rectangle ground) {
		this.ground = ground;
	}
	
	public Rectangle getPlat(int i) {
		return platforms[i];
	}
	public void setPlats(Rectangle r0, Rectangle r1, Rectangle r2) {
		this.platforms[0] = r0;
		this.platforms[1] = r1;
		this.platforms[2] = r2;
	}
	
	public JLabel getStickMan() {
		return stickMan;
	}
	public void setStickMan(JLabel stickMan) {
		this.stickMan = stickMan;
	}
	
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public Boolean getLoop() {
		return loop;
	}
	public void setLoop(Boolean loop) {
		this.loop = loop;
	}
	
	public Character getEnemy() {
		return enemy;
	}
	public void setEnemy(Character enemy) {
		this.enemy = enemy;
	}
	
	public JLabel getDmgLabel() {
		return dmgLabel;
	}
	public void setDmgLabel(JLabel dmgLabel) {
		this.dmgLabel = dmgLabel;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void startThread() {
        moveThread = new Thread(this, "Move Character");
        moveThread.start();
	}
	
	public Character(String name) {
		r = new Rectangle(x + 33, y + 9, 33, 87);
		punchBox = new Rectangle(0, 0, 66, 9);//r.x, r.y + 33
		platforms = new Rectangle[3];
		loop = false;
		punch = false;
		inAir = false;
		dmg = 0;
		this.name = name;
		try {
			if(this.name == "P1") {
				this.spriteSheet = ImageIO.read(getClass().getResource("sprite_sheet_blue.png"));
			} else if(this.name == "P2") {
				this.spriteSheet = ImageIO.read(getClass().getResource("sprite_sheet_red.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
			this.spriteSheet = null;
		}
	}
	
	public void setLabelText(String text) {
		this.dmgLabel.setText(text);
	}
	
	public void setLabelColor0to25() {
		this.dmgLabel.setForeground(Color.WHITE);
	}
	public void setLabelColor25to60() {
		this.dmgLabel.setForeground(Color.YELLOW);
	}
	public void setLabelColor60to85() {
		this.dmgLabel.setForeground(Color.ORANGE);
	}
	public void setLabelColor85plus() {
		this.dmgLabel.setForeground(Color.RED);
	}
	
	@Override
	public void run() {
		
		int prevDir = 0;
		int gr = 1;
		int dj = 2;
		x = stickMan.getX();
		y = stickMan.getY();
		animateFrame = 0;
		int punchFrame = 0;
		int punchCooldown = 0;
		Boolean done = false;
		
		while(alive) {
			
			if(loop) {

				if(jump && dj > 0) {
					if(prevDir == 0) {
						prevDir = 1;
					}
					if(dir == 1 || prevDir == 1) {
						this.setFrame(0, 4, 3);
						stickMan.setIcon(new ImageIcon(this.getFrame()));
					} else if(dir == 2 || prevDir == 2) {
						this.setFrame(0, 5, 3);
						stickMan.setIcon(new ImageIcon(this.getFrame()));
					}
					jumpH = 20;
					dj -= 1;
					jump = false;
					inAir = true;
				}
				
				if(delay % 10 == 0) {
					
					if(mm != 0) {
						x += mm;
						r.x = x + 33;
						
						if(!inAir) {
							if(mm > 0) {
								mm -= 4;
								if(mm < 0) {
									mm = 0;
								}
							} else {
								mm += 4;
								if(mm > 0) {
									mm = 0;
								}
							}
						}
					}
					
					y -= jumpH;
					r.y = y + 9;
					jumpH -= gr;
					
					if(jumpH < 0 && inAir) {
						if(prevDir == 0) {
							prevDir = 1;
						}
						if(dir == 1 || prevDir == 1) {
							this.setFrame(1, 4, 3);
							stickMan.setIcon(new ImageIcon(this.getFrame()));
						} else if(dir == 2 || prevDir == 2) {
							this.setFrame(1, 5, 3);
							stickMan.setIcon(new ImageIcon(this.getFrame()));
						}
					}
					if(r.intersects(ground)) {
						while(r.intersects(ground) || r.intersects(platforms[0]) || r.intersects(platforms[1]) || r.intersects(platforms[2])) {
							r.y -= 1;
						}
						y = r.y - 9;
						jumpH = 0;
						dj = 2;
						inAir = false;
					}
					if((r.intersects(platforms[0]) || r.intersects(platforms[1]) || r.intersects(platforms[2])) && jumpH < 0) {
						while(r.intersects(platforms[0]) || r.intersects(platforms[1]) || r.intersects(platforms[2])) {
							r.y -= 1;
						}
						y = r.y - 9;
						jumpH = 0;
						dj = 2;
						inAir = false;
					}
				}
				
				if(dir == 0) {
					
					if(!inAir && !punch) {
						if(prevDir == 1 || prevDir == 0) {
							this.setFrame(0, 2, 3);
							stickMan.setIcon(new ImageIcon(this.getFrame()));
						} else if(prevDir == 2) {
							this.setFrame(0, 3, 3);
							stickMan.setIcon(new ImageIcon(this.getFrame()));
						}
					}
					animateFrame = 0;
				}
	
				if(dir == 1) {
					prevDir = 1;
					if(delay % 2 == 0) {
						
						if(mm == 0) {
							x += 1;
							r.x = x + 33;
						} else if(delay % 10 == 0 && mm < 0) {
							mm += 1;
						}
						if(r.intersects(ground)) {
							while(r.intersects(ground)) {
								r.x -= 1;
							}
							x = r.x - 33;
						}
						if((r.intersects(platforms[0]) || r.intersects(platforms[1]) || r.intersects(platforms[2])) && jumpH < 0) {
							while(r.intersects(platforms[0]) || r.intersects(platforms[1]) || r.intersects(platforms[2])) {
								r.x -= 1;
							}
							x = r.x - 33;
						} 
					}
					if(delay % 50 == 0 && !inAir) {
						animateFrame++;
						if(animateFrame > 6) {
							animateFrame = 0;
						}
						this.setFrame(animateFrame, 0, 3);
						stickMan.setIcon(new ImageIcon(this.getFrame()));
					}
					
				}
				
				if(dir == 2) {
					prevDir = 2;
					if(delay % 2 == 0) {
						
						if(mm == 0) {
							x -= 1;
							r.x = x + 33;
						} else if(delay % 10 == 0 && mm > 0) {
							mm -= 1;
						}
						
						if(r.intersects(ground)) {
							while(r.intersects(ground)) {
								r.x += 1;
							}
							x = r.x - 33;
						}
						if((r.intersects(platforms[0]) || r.intersects(platforms[1]) || r.intersects(platforms[2])) && jumpH < 0) {
							while(r.intersects(platforms[0]) || r.intersects(platforms[1]) || r.intersects(platforms[2])) {
								r.x += 1;
							}
							x = r.x - 33;
						}
					}
					if(delay % 50 == 0 && !inAir) {
						animateFrame++;
						if(animateFrame > 6) {
							animateFrame = 0;
						}
						this.setFrame(animateFrame, 1, 3);
						stickMan.setIcon(new ImageIcon(this.getFrame()));
					}
				}
				
				if(punch && punchCooldown == 0) {
					if(punchDir == 1) {
						this.setFrame(1, 6, 3);
						stickMan.setIcon(new ImageIcon(this.getFrame()));
						this.updatePunchBox(r.x - 66, r.y + 33);
					}
					if(punchDir == 2) {
						this.setFrame(0, 6, 3);
						stickMan.setIcon(new ImageIcon(this.getFrame()));
						this.updatePunchBox(r.x, r.y + 33);
					}
					if(punchDir == 3 && inAir) {
						if(prevDir == 1 || dir == 1) {
							this.setFrame(3, 6, 3);
						} else if(prevDir == 2 || dir == 2) {
							this.setFrame(2, 6, 3);
						}
						stickMan.setIcon(new ImageIcon(this.getFrame()));
						this.updatePunchBox(r.x, r.y + 96);
					}
					if(punchBox.intersects(enemy.getRec()) && !done) {
						if(punchDir == 1 || punchDir == 3) {
							if(enemy.getDmg() > 0) {
								enemy.setDmg((-1)*(enemy.getDmg()));
							}
							if(punchDir == 1) {
								enemy.setDmg(enemy.getDmg() - 1);
							} else {
								enemy.setDmg(enemy.getDmg() - 5);
							}
						}
						if(punchDir == 2) {
							if(enemy.getDmg() < 0) {
								enemy.setDmg((-1)*(enemy.getDmg()));
							}
							
							enemy.setDmg(enemy.getDmg() + 1);
						}
						enemy.setLabelText(Integer.toString(Math.abs(enemy.getDmg()) * 4));
						if(Math.abs(enemy.getDmg()) * 4 >= 0 && Math.abs(enemy.getDmg()) * 4 < 25) {
							enemy.setLabelColor0to25();
						} else if(Math.abs(enemy.getDmg()) * 4 >= 25 && Math.abs(enemy.getDmg()) * 4 < 60) {
							enemy.setLabelColor25to60();
						} else if(Math.abs(enemy.getDmg()) * 4 >= 60 && Math.abs(enemy.getDmg()) * 4 < 85) {
							enemy.setLabelColor60to85();
						} else {
							enemy.setLabelColor85plus();
						}
						if(punchDir == 1 || punchDir == 2) {
							enemy.setMm(enemy.getDmg());
							enemy.setJumpH(Math.abs(enemy.getDmg()));
							enemy.setInAir(true);
						} else {
							enemy.setJumpH(enemy.getDmg());
						}
						done = true;
					}
					punchFrame += 1;
					if(punchFrame > 60) {
						punch = false;
						done = false;
						punchFrame = 0;
						punchCooldown = 100;
						if(name == "P1") {
							this.updatePunchBox(0, 0);
						} else if(name == "P2") {
							this.updatePunchBox(1920, 0);
						}
					}
				}
				
				if(punchCooldown > 0) {
					punchCooldown -= 1;
				}
				
				if(y > 8000) {
					lives -= 1;
					livesDisplay[lives].setVisible(false);
					if(name == "P1") {
						x = 200;
					} else if(name == "P2") {
						x = 1620;
					}
					y = 200;
					jumpH = 0;
					mm = 0;
					dmg = 0;
					inAir = false;
					prevDir = 0;
					this.setLabelText("0");
					this.setLabelColor0to25();
					if(lives == 0) {
						this.setAlive(false);
						y = -96;
					}
				}
				
				this.setX(x);
				this.setY(y);
				stickMan.setLocation(x, y);
				this.updateRec(x, y);
				loop = false;
			}
			
		}//while alive
		
	}
	
}
