public class MatchSequence implements IMatch
{
	int minpairindex=0;
	int n = 10000;
	int txt[][] = new int[16][n];
	int ptr[][] = new int[16][n];
	int indexSort[][] = new int[16][n];
	HashDefined hd = new HashDefined();
	public MatchSequence()
	{
		for(int i=0;i<16;i++)
		{
			for(int j = 1;j<n;j++)
			{
				ptr[i][j] = -1;
				txt[i][j] = -1;
			}
		}
	}
	
	public void createHashTableText(String str)
	{
		char c,d;
		String s;
		int j,k;
		for(int i = 0;i< str.length()-1 ; i++ )
		{
			s = "";
			k = 1;
			c = str.charAt(i);
			d = str.charAt(i+1);
			s = s+c+d;
			j = hd.getUniqueVal(s);
			txt[j][0]++;
			while(txt[j][k]>=0)
			{
				k++;
			}
			txt[j][k]=i;
			//i++;
		}
		/*
		System.out.println("Count\tIndexes --> -->");
		for(int i=0;i<16;i++)
		{
			System.out.print(txt[i][0]+"\t");
			for(k = 1;k<= txt[i][0];k++)
			{
				System.out.print(txt[i][k]+"\t");
			}
			System.out.println("\n");
		}
		*/
	}
	
	public void createHashTablePattern(String str)
	{
		char c,d;
		String s;
		int j,k;
		for(int i = 0;i< str.length() ; i++ )
		{
			s = "";
			k = 1;
			c = str.charAt(i);
			d = str.charAt(i+1);
			s = s+c+d;
			j = hd.getUniqueVal(s);
			ptr[j][0]++;
			while(ptr[j][k]>=0)
			{
				k++;
			}
			ptr[j][k]=i;
			i++;
		}
		/*
		System.out.println("Count\tIndexes --> -->");
		for(int i=0;i<16;i++)
		{
			System.out.print(ptr[i][0]+"\t");
			for(k = 1;k<= ptr[i][0];k++)
			{
				System.out.print(ptr[i][k]+"\t");
			}
			System.out.println("\n");
		}
		*/
	}
	
	public void sortPtr()
	{
		int j , k, i;
		for(i=0;i<16;i++)
		{
			j = getMinPairIndex();
			indexSort[i][0]=j;
			k =0;
			while( ptr[j][k] > -1)
			{
				indexSort[i][k+1] = ptr[j][k];
				k++;
			}
			indexSort[i][k+1] = -1;
			ptr[j][0] = 0;
		}
		/*
		for(int l=0;l<16;l++)
		{
			if(indexSort[l][0] != 0)
			{
				System.out.print(indexSort[l][0]+"\t"+indexSort[l][1]+"\t");
				for( k =0; k < indexSort[l][1];k++)
				{
					System.out.print(indexSort[l][k+2]+"\t");
				}
			} 
			System.out.println();
		}
		*/
	}
		
	public int getMinPairIndex()
	{
		int mincount=100000;
		int minindex=0;
		for(int i=0;i<16;i++)
		{
			if(ptr[i][0]!=0 && ptr[i][0]<mincount)
			{
				mincount=ptr[i][0];
				minindex=i;
			}	
		}
		return minindex;
	}	
	
	public void findMatch(String text,String pattern)
	{
		//System.out.println("TEXT");
		createHashTableText(text);
		//System.out.println("\nPATTERN");
		createHashTablePattern(pattern);
		sortPtr();
		/*
			STARTING OF BASE PAIR COMPARISON
		*/	
		int res = -1;
		int j = 1;
		int count = 0;
		int min = indexSort[0][0];
		for(int i=0; i<txt[min][0] ; i++)
		{
			int effIndex = txt[min][j] - indexSort[0][2];
			//System.out.println(effIndex);
			res = compare(effIndex,text,pattern);
			if(res>0)
			{
				System.out.println("A match found at index: "+res);
				count++;
				j++;
			}
			else
			{
				j++;
			}
		}
		if(count>0)
		{
			System.out.println("Gene segment count: "+count);
		}
		else
		{
			System.out.println("Gene segment Unidentified in the parent prototype");
		}
	}
	public int compare(int ind,String text,String pattern)
	{
		int x = ind + pattern.length();
		if(x < text.length())
		{
			String s = text.substring(ind,ind+pattern.length());
			//System.out.println(s);
			if(s.compareTo(pattern)==0)
			{
				return ind;
			}
		}
		return -1;
	}
}

class HashDefined
{
	public HashDefined()
	{
		
	}
	public int getUniqueVal(String s)
	{
		int ascii_l=s.charAt(1);
		int ascii_f=s.charAt(0);
		int sp =(ascii_l<<8)+ascii_f;
		int index=((sp&1536)>>9)|((sp&6)<<1);
		return index;
	}
}