import java.util.Random;

public class MoveEnemy implements Runnable {
	
	private Thread move;
	private Character enemy;
	private Character player;
	private Boolean loop;
	
	public Character getEnemy() {
		return enemy;
	}
	public void setEnemy(Character enemy) {
		this.enemy = enemy;
	}
	
	public Character getPlayer() {
		return player;
	}
	public void setPlayer(Character player) {
		this.player = player;
	}
	
	public Boolean getLoop() {
		return loop;
	}
	public void setLoop(Boolean loop) {
		this.loop = loop;
	}
	
	public MoveEnemy(Character player, Character enemy) {
		this.enemy = enemy;
		this.player = player;
		this.loop = true;
	}
	
	public void startThread() {
        move = new Thread(this, "Move");
        move.start();
	}
	
	@Override
	public void run() {
		
		int lastDir = 0;
		
		while(enemy.getAlive()) {
			if(loop) {
				
				if(enemy.getX() < player.getX() - 20 && lastDir != 2 && enemy.getY() > player.getY() - 15) {
					enemy.setDir(1);
					lastDir = 2;
				} else if(enemy.getX() > player.getX() + 20 && lastDir != 1 && enemy.getY() > player.getY() - 15){
					enemy.setDir(2);
					lastDir = 1;
				} else if(lastDir != 3 && enemy.getInAir() == false && enemy.getY() < player.getY() - 25){
					Random rand2 = new Random();
					int randNum2 = rand2.nextInt(2) + 1;
					enemy.setDir(randNum2);
					lastDir = 3;
				}
				
				if(enemy.getY() > player.getY() - 20 && (enemy.getX() > player.getX() - 86 && enemy.getX() < player.getX() + 86) ){
					
					enemy.setPunch(true);
					enemy.setPunchDir(lastDir);
					
				}
				
				if(enemy.getX() > 1620) {
					enemy.setDir(2);
				}
				if(enemy.getX() < 220) {
					enemy.setDir(1);
				}
				
				if(enemy.getMm() < 0) {
					Random rand3 = new Random();
					int randNum3 = rand3.nextInt(50) + 1;
					if(randNum3 == 1) {
						enemy.setDir(1);
					}
				}
				if(enemy.getMm() > 0) {
					Random rand3 = new Random();
					int randNum3 = rand3.nextInt(50) + 1;
					if(randNum3 == 1) {
						enemy.setDir(2);
					}
				}
				
				Random rand = new Random();
				int randNum = rand.nextInt(1000) + 1;
				if(randNum == 1 && enemy.getX() > 500 && enemy.getX() < 1420) {
					enemy.setJump(true);
				}
				
				loop = false;
			}
		}
		
	}
	
}
