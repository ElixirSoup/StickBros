import javax.swing.JLabel;
import javax.swing.JPanel;

public class Timer implements Runnable {
	
	private Thread frameCounter;
	private Character player1;
	private Character enemy;
	private MoveEnemy AI;
	private JPanel modeSelect;
	private JLabel modeText;

	public Character getPlayer1() {
		return player1;
	}
	public void setPlayer1(Character player1) {
		this.player1 = player1;
	}

	public Character getEnemy() {
		return enemy;
	}
	public void setEnemy(Character enemy) {
		this.enemy = enemy;
	}
	
	public MoveEnemy getMoveEnemy() {
		return AI;
	}
	public void setMoveEnemy(MoveEnemy AI) {
		this.AI = AI;
	}
	
	public JPanel getModeSelect() {
		return modeSelect;
	}
	public void setModeSelect(JPanel modeSelect) {
		this.modeSelect = modeSelect;
	}
	
	public JLabel getModeText() {
		return modeText;
	}
	public void setModeText(JLabel modeText) {
		this.modeText = modeText;
	}
	
	public void startThread() {
        frameCounter = new Thread(this, "Timer");
        frameCounter.start();
	}
	
	public Timer(Character p1, Character p2) {
		this.player1 = p1;
		this.enemy = p2;
		AI = null;
	}

	@Override
	public void run() {
		
		int frame = 0;
		
		while(player1.getAlive() && enemy.getAlive()) {
			try {
				frame += 1;
				player1.setDelay(frame);
				enemy.setDelay(frame);
				if(frame > 500) {
					frame = 0;
				}
				player1.setLoop(true);
				enemy.setLoop(true);
				if(AI != null) {
					AI.setLoop(true);
				}
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(!player1.getAlive()) {
			enemy.setAlive(false);
			modeSelect.setVisible(true);
			modeText.setText("  Player 2 Wins");
		} else if(!enemy.getAlive()){
			player1.setAlive(false);
			modeSelect.setVisible(true);
			modeText.setText("  Player 1 Wins");
		}
		
	}

}
