package upm.tdd.training;
import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
	//a bowling game is made of (at least) 10 frames
	private List<Frame> frames = new ArrayList<Frame>();
	private Frame bonus;
	
	public BowlingGame(){}
	
	public void addFrame(Frame frame) throws BowlingException{
		if(frames.size()<10)
			frames.add(frame);
		else if (frames.size()>=10) {
			setBonus(frame.getFirstThrow(), frame.getSecondThrow());
			frames.add(bonus);
		}
	}
	
	public void setBonus(int firstThrow, int secondThrow) throws BowlingException {
		bonus = new Frame(firstThrow, secondThrow);
	}
	
	public int score(){
		int score=0;
		
		for (int i=0; i<frames.size(); i++)
		{
			int frameScore=0;
			Frame thisFrame=frames.get(i);
			
			if (i<frames.size()-2) {
				Frame nextFrame=frames.get(i+1);
				Frame lastFrame=frames.get(i+2);
				
				if(thisFrame.isStrike()) {
					if (nextFrame.isStrike()){
						frameScore=10;
						frameScore+=bonus(nextFrame, lastFrame);
					}
					else {
						frameScore+=bonus(thisFrame, nextFrame);
					}
				}
				else if (thisFrame.isSpare()) {
					frameScore+=bonus(thisFrame, nextFrame);
				}
				else
					frameScore=thisFrame.score();
			}
			else if (i<frames.size()-1) {
				Frame nextFrame=frames.get(i+1);
				
				if(thisFrame.isStrike()) {
					if (nextFrame.isStrike())
					{
						frameScore=10;
						frameScore+=bonus(nextFrame, bonus);
					}
					else {
						frameScore+=bonus(thisFrame, nextFrame);
					}
				}
				else if (thisFrame.isSpare()) {
					frameScore+=bonus(thisFrame, nextFrame);
				}
				else
					frameScore=thisFrame.score();
			}
			else if (i==frames.size()) {
				if(thisFrame.isStrike()) {
					frameScore+=bonus(thisFrame, bonus);
				}
				else if (thisFrame.isSpare()) {
					frameScore+=bonus(thisFrame, bonus);
				}
				else
					frameScore=thisFrame.score();
			}
			
			score+=frameScore;
		}
		return score;
	}
	
	private int bonus(Frame frist, Frame next){	
		int bonus=0;
		
		if(frist.isStrike())
			bonus=frist.score()+next.score();
		else if(frist.isSpare())
			bonus=frist.score()+next.getFirstThrow();
		
		return bonus;
	}
	
	
	public boolean isNextFrameBonus(){
		//to be implemented
		return false;
	}
}
