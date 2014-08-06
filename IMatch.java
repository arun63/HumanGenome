public interface IMatch
{
	public void createHashTableText(String str);
	public void createHashTablePattern(String str);
	public void findMatch(String text,String pattern);
	public int getMinPairIndex();
	public void sortPtr();
	public int compare(int x,String t,String p);
}