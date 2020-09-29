
package stat;
public class Stat {
	
	private double[] data; 
	
	public Stat(){
		
		data = new double[1];
		data[0] = 0.0;
		 
	
	}
	
	public Stat( double[] d ){
		
		data = new double[d.length];
		setData( d ); 
		
	}
	
	public double[] getData(){
		
		double[] dataCopy = new double[ data.length ]; 
		
		for( int i = 0; i < data.length; i++ ){
			
			dataCopy[i] = data[i]; 
			
		}
		
		return dataCopy; 
		
	}
	
	public void setData( double[] d ){
		  
		double[] dCopy =  new double[ d.length ]; 
		
		for( int i = 0; i < d.length; i++ ){
			
			dCopy[i] = d[i]; 
		} 
		
		data = dCopy; 
		
	}
	
	public boolean equals( Stat s ){
		
		boolean isEqual = false; 
		 
 
		int i           = 0; 
		if( data.length != s.data.length ){ 
			
			return false; 
			
		} 
		
		if (this == null || s == null) return false;
		
			
			for( i = 0; i < data.length; i++ ){
				
				
				if( Math.abs(data[i] - s.data[i]) > 0.000001 ){ return false; }
				
			}//for-loop
			
	
		return true;
		//if( i == data.length-1  ) { isEqual = true; }
		
		//return isEqual; 
		
	}//equal method
  
	public String toString(){
	  
		String arrayString  = "["; 
		double[] array      = getData(); 
		
		for( int i = 0; i < array.length; i++ ){
			
			arrayString += " " + array[i] + ","; 
			
		}
		
		arrayString += "]"; 
		return arrayString; 
		
   }
	
  public double min(){
	  
	  double[] array = getData(); 
	  double a       = 0; 
	  double b       = 0; 
	  double min     = array[0]; 
	  double tempMin = 0; 
	  
	  for( int i = 0; i < array.length; i++ ){
		  
		  if( i == array.length - 1 ){
			  
			  a = array[array.length - 2]; 
			  b = array[array.length - 1]; 
			  
		  }//if-statement
		  
		  else {
			  
			  a = array[i]; 
			  b = array[i + 1]; 
			 
		  }//else
		  
		  min = Math.min(a, b);
		  
		  if( i == 0 ){ tempMin = min; }
		  else{ 
			  
			  if( min < tempMin  ){ tempMin = min; }
		  }
		  
	  }//for loop
	  return tempMin; 
	  
 }//min()
  
 public double max(){
	 
	 double[] array   = getData(); 
	 double   a       = 0; 
	 double   b       = 0; 
	 double   max     = array[0]; 
	 double   tempMax = 0; 
	 
	 for( int i = 0; i < array.length; i++ ){
		  
		  if( i == array.length - 1 ){
			  
			  a = array[array.length - 2]; 
			  b = array[array.length - 1]; 
			  
		  }//if-statement
		  
		  else {
			  
			  a = array[i]; 
			  b = array[i + 1]; 
			 
		  }//else
		  
		  max = Math.max(a, b);
		  
		  if( i == 0 ){ tempMax = max; }
		  else{ 
			  
			  if( max > tempMax  ){ tempMax = max; }
		  }
		  
	  }//for loop
	  return tempMax; 
 }//max()
 
 public double average(){
	 
	 double sum         = 0; 
	 double numElements = data.length; 
	 double mean        = 0; 
	 
	 for( int i = 0; i < numElements; i++ ){
		 
		 sum += data[i]; 
	 }
	 
	 mean = sum / numElements; 
	 return mean; 
	 
 }//average()
 
 public double mode(){
	 
	 double[] a       = getData(); 
	 int aCount       = 1; 
	 int bCount       = 1; 
	 double aVal      = a[0]; 
	 double bVal 	  = 0; 
	 double mode      = 0; 
	 double modeCount = 0; 
	 double valSearch = 0; 
	 
	 for( int i = 0; i < a.length; i++ ){
		 
		 valSearch = a[i]; 
		 if( valSearch == aVal ){ aCount++; }
	 }
	 
	 modeCount = aCount; 
	 
	 for( int n = 1; n < a.length; n++) {
		 
		 bVal = a[n]; 
		 
		 if( bVal != aVal ){
			 
			 for( int i = 2; i < a.length; i++ ){
				 
				 valSearch = a[n]; 
				 if( valSearch == bVal ){ bCount++; }
				 
			 }//for
			 
			 if ( modeCount == bCount ){ mode = Double.NaN; }
			 else if ( bCount > modeCount ){ 
				 
				 modeCount = bCount; 
				 mode      = bVal; 
			
			 }
		 }//if
	 }//for
	 
	 return mode; 
 }//mode class
}
