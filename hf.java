
	public int getMinPairIndex()
	{
		int mincount=100000;
		int minindex;
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

	public int getUniqueVal(String s)
	{
		int ascii_l=s.charAt(1);
		int ascii_f=s.charAt(0);
		int sp =(ascii_l<<8)+ascii_f;
		int index=((sp&1536)>>9)|((sp&6)<<1);
		return index;
	}


