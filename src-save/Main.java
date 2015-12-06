public class Main {
	public static void main(String[] args) {
		Game game = new Game();
		RunningFrame frame = new RunningFrame("The Whaler lord", 900,700);
	    Controls controls = new Controls(game);
	    frame.startRun(controls, game);
	}
}
//Steps	