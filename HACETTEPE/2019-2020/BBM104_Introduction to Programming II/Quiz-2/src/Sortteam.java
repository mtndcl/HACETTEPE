import java.util.Comparator;

public class Sortteam implements Comparator<Sport> {
		@Override
		public int compare(Sport sport1, Sport sport2) {
			int score1 = sport1.getScore();
			int score2= sport2.getScore();
	 
			if (score1 < score2) {
				return 1;
			} else if (score1 > score2) {
				return -1;
			} else {
				return 0;
			}
		}

}
