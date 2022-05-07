package model;

public class KeyWord {
	private int idKeyWord;
	private String key_word;
	
	public KeyWord() {
		
	}
	
	public KeyWord(int idKeyWord, String key_word) {
		this.idKeyWord = idKeyWord;
		this.key_word = key_word;
	}
	
	public int getIdKeyWord() {
		return idKeyWord;
	}
	public void setIdKeyWord(int idKeyWord) {
		this.idKeyWord = idKeyWord;
	}
	public String getKey_word() {
		return key_word;
	}
	public void setKey_word(String key_word) {
		this.key_word = key_word;
	}
}