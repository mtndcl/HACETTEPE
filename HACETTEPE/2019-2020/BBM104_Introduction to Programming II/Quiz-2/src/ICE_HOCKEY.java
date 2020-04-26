
public class ICE_HOCKEY  implements Sport{
	
	
	String id;
	int rank;
	String club_name;
	int number_played_match;
	int win_number;
	int tie_number;
	int lost_number;
	int set_win;
	int set_lost;
	int score;
	
	public  ICE_HOCKEY(String home_club) {
		
		
		this.id="I"+home_club;
		this.rank=-1;
		this.club_name=home_club;
		this.number_played_match=0;
		this.win_number=0;
		this.tie_number=0;
		this.lost_number=0;
		this.set_win=0;
		this.set_lost=0;
		this.score=0;
		
	}

	@Override
	public String getClubID() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void Win(int in, int out) {
		this.score=this.score+3;
		set_win=set_win+in;
		set_lost=set_lost+out;
		number_played_match++;
		win_number++;
		
	}

	@Override
	public void Lost(int in, int out) {
		// TODO Auto-generated method stub
		set_win=set_win+in;
		set_lost=set_lost+out;
		number_played_match++;
		lost_number++;
		
	}

	@Override
	public void Tie(int in_out) {
		this.score=this.score+1;
		set_win=set_win+in_out;
		set_lost=set_lost+in_out;
		number_played_match++;
		tie_number++;
		
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return this.score;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.club_name+"\t"+ this.number_played_match+"\t"+this.win_number+"\t"+this.tie_number+"\t"+this.lost_number+"\t"+this.set_win+":"+this.set_lost+"\t"+this.score+"\n";
	}


}
