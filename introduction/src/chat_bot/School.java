package chat_bot;

public class School implements Topic{
	private boolean inSchoolLoop;
	private String schoolResponse;
	
	public void talk(){
		inSchoolLoop = true;
		while(inSchoolLoop){
			JiaMingMain.print("Tell me about school.");
			schoolResponse = JiaMingMain.getInput();
			JiaMingMain.print("That's my favorite " + "part about school too.");
		}
	}

}
