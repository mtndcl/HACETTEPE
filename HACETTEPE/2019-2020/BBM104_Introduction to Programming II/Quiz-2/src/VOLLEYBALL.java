

public class VOLLEYBALL implements Sport {

	
	
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
	
	public  VOLLEYBALL(String home_club) {
		
		
		this.id="V"+home_club;
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
		
		
		if(in==3  &&  (out ==0 ||  out==1)) {
			this.score=this.score+3;
		}else if(in==3 && out==2) {
			this.score=this.score+2;
		}
		
		this.number_played_match++;
		
		this.win_number++;
		
		this.set_win=this.set_win+in;
		this.set_lost=this.set_lost+out;

	}

	@Override
	public void Lost(int in, int out) {
		
		if(in==2 && out==3) {
			this.score+=1;
		}
		this.number_played_match++;
		this.lost_number++;
		this.set_win=this.set_win+in;
		this.set_lost=this.set_lost+out;
	}

	@Override
	public void Tie(int in_out) {
		
		this.number_played_match++;
		this.tie_number++;
		this.set_win=this.set_win+in_out;
		this.set_lost=this.set_lost+in_out;
		
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
