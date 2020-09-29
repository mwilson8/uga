package stat;
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double[]	data	=	{-5.3,	2.5,	88.9,	0,	0.0,	28,	16.5,	88.9,	109.5,	-90,	88.9};
		double[]	data2	=	{100.34,	50.01,	50.01,	-8};
		Stat	stat1	=	new Stat();
		System.out.println("stat1	data	= " +	 stat1.toString());
		stat1	=	new Stat(data);
		System.out.println("stat1	has	been	altered.");
		System.out.println("stat1	data	=	" +	 stat1.toString());
		System.out.println("stat1	min	=	" +	stat1.min());
		System.out.println("stat1	max	=	" +	stat1.max());
		System.out.println("stat1	average	=	" +	stat1.average());
		System.out.println("stat1	mode	=	" +	stat1.mode()	+	"\n");
		Stat	stat2	=	new Stat();
		stat2.setData(data2);
		Stat	stat3	=	new Stat(stat1.getData());
		System.out.println("stat2	data	=	" +	 stat2.toString());
		System.out.println("stat3	data	=	" +	 stat3.toString());
		System.out.println();
		
		stat2.setData(data2);
		stat3.setData(data2);
		System.out.println("stat1	is	equal	to	stat2	using	\"equals()\"?	" +			
		stat1.equals(stat2));
		System.out.println("stat2	is	equal	to	stat3		using	\"equals()\"?	" +	
		stat2.equals(stat3));
		System.out.println("stat1	is	equal	to	stat3		using	\"==\"?	" +	(stat1	==	stat3));
		
	}

}
