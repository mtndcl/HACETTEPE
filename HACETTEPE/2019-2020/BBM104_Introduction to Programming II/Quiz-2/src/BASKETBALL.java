
public class BASKETBALL  implements Sport{
	
	
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
	
	public  BASKETBALL(String home_club) {
		
		
		this.id="B"+home_club;
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
	public void Win(int in, int out) {
		
		
		this.score=this.score+2;
		number_played_match++;
		win_number++;
		set_win=set_win+in;
		set_lost=set_lost+out;
		
	}

	@Override
	public void Lost(int in, int out) {
		this.score=this.score+1;
		number_played_match++;
		lost_number++;
		set_win=set_win+in;
		set_lost=set_lost+out;
		
	}

	@Override
	public void Tie(int in_out) {
		// TODO Auto-generated method stub
		number_played_match++;
		tie_number++;
		set_win=set_win+in_out;
		set_lost=set_lost+in_out;
		
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

	@Override
	public String getClubID() {
		// TODO Auto-generated method stub
		return this.id;
	}


	
	

}
