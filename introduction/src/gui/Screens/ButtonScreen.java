package gui.Screens;

import java.util.ArrayList;

import gui.Screen;
import gui.components.TextArea;
import gui.components.Visible;

public class ButtonScreen extends Screen {
	
	private TextArea area;
	
	public ButtonScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initObjects(ArrayList<Visible> viewObjects) {
		area = new TextArea(20, 200, 500, 375, "Whenever I get a package of plain M&Ms, I make it my duty to continue the "
				+ "strength and robustness of the candy as a species. To this end, I hold M&M duels. Taking two candies between "
				+ "my thumb and forefinger, I apply pressure, squeezing them together until one of them cracks and splinters. "
				+ "That is the �loser,� and I eat the inferior one immediately. The winner gets to go another round. I have found "
				+ "that, in general, the brown and red M&Ms are tougher, and the newer blue ones are genetically inferior. I have "
				+ "hypothesized that the blue M&Ms as a race cannot survive long in the intense theater of competition that is the "
				+ "modern candy and snack-food world. Occasionally I will get a mutation, a candy that is misshapen, or pointier, "
				+ "or flatter than the rest. Almost invariably this proves to be a weakness, but on very rare occasions it gives "
				+ "the candy extra strength. In this way, the species continues to adapt to its environment. When I reach the end "
				+ "of the pack, I am left with one M&M, the strongest of the herd. Since it would make no sense to eat this one as "
				+ "well, I pack it neatly in an envelope and send it to M&M Mars, A Division of Mars, Inc., Hackettstown, "
				+ "NJ 17840-1503 U.S.A., along with a 3�5 card reading, �Please use this M&M for breeding purposes.� This week they "
				+ "wrote back to thank me, and sent me a coupon for a free 1/2 pound bag of plain M&Ms. I consider this �grant money.� "
				+ "I have set aside the weekend for a grand tournament. From a field of hundreds, we will discover the True Champion. "
				+ "There can be only one. ");
		viewObjects.add(area);
	}
}
