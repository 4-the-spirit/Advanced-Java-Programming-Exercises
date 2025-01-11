
public class Sentence implements Flipable<Sentence> {
	private String[] words;
	
	public Sentence(String[] words) {
		this.words = words;
	}
	
	public String toString() {
		String finalString = "";
		for (int i = 0; i < words.length; i++) {
			finalString += (words[i] + " ");
		}
		return finalString;
	}
	
	public Sentence flip() {
		String[] newWords = new String[words.length];
		for (int i = 0; i < newWords.length; i++) {
			newWords[i] = words[words.length-1 - i];
		}
		return new Sentence(newWords);
	}
}
