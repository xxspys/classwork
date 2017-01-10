package gui.Simon;

import java.awt.Color;
import java.util.ArrayList;

import gui.Screens.ClickableScreen;
import gui.components.Action;
import gui.components.TextArea;
import gui.components.TextLabel;
import gui.components.TextMultiLines;
import gui.components.Visible;

public class SimonScreenJiaMing extends ClickableScreen implements Runnable {

//	ProgressInterfaceX
//	ArrayList<MoveInterfaceX>
//	an int to keep track of the roundNumber
//	a boolean to keep track of whether of not input from the user is being accepted
	
	private ProgressInterfaceJiaMing progress;
	private ButtonInterfaceJiaMing[] buttons;
	private ArrayList<MoveInterfaceJiaMing> moves;
	private TextMultiLines label;
	private TextLabel status;;
	private boolean acceptingInput;
	private int currentSeq;
	private int previousButton;
	
	public SimonScreenJiaMing(int width, int height) {
		super(width, height);
		progress.setRoundNum(0);
		Thread play = new Thread(this);
		play.start();
	}

	public ButtonInterfaceJiaMing getAButton() {
		return null;
	}
	
	public MoveInterfaceJiaMing getAMove(ButtonInterfaceJiaMing b) {
		return null;
	}
	
	public ProgressInterfaceJiaMing getAProgress() {
		return null;
	}
	
	@Override
	public void initAllObjects(ArrayList<Visible> clickables) {
		progress = getAProgress();
		buttons = new ButtonInterfaceJiaMing[6];
		Color limeGreen = new Color(125, 255, 100);
		label = new TextMultiLines(20, 20, 100, 125, limeGreen);
		status = new TextLabel(300, 20, 100, 125, "Let's play Simon!");
		moves = new ArrayList<MoveInterfaceJiaMing>();
		addButtons();
		previousButton = -1;
		moves.add(randomButton());
		moves.add(randomButton());
		viewObjects.add(label);
		viewObjects.add(status);
		
	}

	private void addButtons() {
		Color[] colors = {Color.BLUE, Color.PINK, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE};
		int[] coordX = {400, 400, 200, 200, 600, 600};
		int[] coordY = {200, 600, 300, 400, 300, 400};
		for(int i = 0; i < buttons.length; i ++) {
			final ButtonInterfaceJiaMing b = getAButton();
			b.setColor(colors[i]);
			b.setX(coordX[i]);
			b.setY(coordY[i]);
			b.setAction(new Action() {
				
				@Override
				public void act() {
					if(acceptingInput) {
						Thread blink = new Thread(new Runnable() {
							
							@Override
							public void run() {
								b.highlight();
								try {
									Thread.sleep(800);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								b.dim();
							}
						});
						blink.start();
						if(b.equals(moves.get(currentSeq).getButton())) {
							currentSeq ++;
							if(moves.size() == currentSeq) {
								Thread nextRound = new Thread(SimonScreenJiaMing.this);
								nextRound.start(); 
							}
						} else {
							gameOver();
						}
					}
				}
			});
			viewObjects.add(b);
			buttons[i] = b;
		}
	}

	private void gameOver() {
		label.addString("WRONG YOU LOSE!");
		acceptingInput = false;
	}
	
	private void nextRound() {
		acceptingInput = false;
		moves.add(randomButton());
		changeText("Simon's turn");
		playSequence();
		changeText("Your turn");
		acceptingInput = true;
		
	}
	
	private void playSequence() {
		ButtonInterfaceJiaMing b = null;
		for(int i = 0; i < moves.size(); i ++) {
			if(b != null) b.dim();
			b = moves.get(i).getButton();
			b.highlight();
			try {
				Thread.sleep((long) (2500 / Math.sqrt(progress.getRoundNum())));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		b.dim();
	}

	private void changeText(String string) {
		status.setText(string);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		status.setText("");
	}

	private MoveInterfaceJiaMing randomButton() {
		ButtonInterfaceJiaMing b;
		int rand = (int) (Math.random() * buttons.length);
		while(previousButton == rand) rand = (int) (Math.random() * buttons.length);
		previousButton = rand;
		b = buttons[rand];
		return getAMove(b);
	}
	
	private void updateLabel() {
		while(label.getStringList().size() > 0) label.remove(0);
		label.addString("Round " + progress.getRoundNum());
		label.addString("Sequence Number " + progress.getSequenceLength());
	}
	
	public void run() {
		progress.increaseRoundNum(1);
		progress.setSequenceLength(moves.size());
		updateLabel();
		nextRound();
		
	}
}
