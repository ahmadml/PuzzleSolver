public class heurstic {
	// sum of Manhattan distances between tiles and goal
	int manhattan_algorithmD(char[][]arr , char[][] goal) {

		int sum = 0;
		int c [][] = new int[arr.length][arr.length];
		int c1 [][] = new int[arr.length][arr.length];
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c.length; j++) {
				if (arr[i][j]==(goal[i][j]) && arr[i][j]!=('_')) {
					c[i][j] = 1;
				}
			}
		}

		int min = 0;
		int r_min=Integer.MAX_VALUE;
		int y_min=Integer.MAX_VALUE;
		int b_min=Integer.MAX_VALUE;
		int g_min=Integer.MAX_VALUE;
		boolean flag = false;
		int index_i = -1;
		int index_j = -1;

		for (int i = 0; i < goal.length; i++) {
			for (int j = 0; j < goal[0].length; j++) {
				min = 0;
				r_min=Integer.MAX_VALUE;
				y_min=Integer.MAX_VALUE;
				b_min=Integer.MAX_VALUE;
				g_min=Integer.MAX_VALUE;
				flag = false;
				for (int i2 = 0; i2 < arr.length; i2 ++) {
					for (int j2 = 0; j2 < arr[0].length; j2++) {

						if (c[i][j] == 0 && c[i2][j2] == 0 && c1[i2][j2] == 0 && goal[i][j]!=('_') && arr[i2][j2]!=('_') && arr[i2][j2]==(goal[i][j])) {
							flag = true;
							if(arr[i2][j2]==('R')) {
								min =  Math.abs(i-i2 )+Math.abs(j-j2);
								if(r_min == Integer.MAX_VALUE) {
									r_min = min; 
									sum+=r_min; 
									index_i=i2; 
									index_j=j2;
								}
								else if(min < r_min) {
									sum-=r_min;
									r_min = min;
									sum+=r_min;
									index_i = i2; index_j=j2;
								}
							}
							else if(arr[i2][j2]==('Y')) {
								min = Math.abs(i-i2 )+Math.abs(j-j2);
								if(y_min == Integer.MAX_VALUE) {y_min = min; sum+=y_min;index_i = i2; index_j=j2;}
								else if(min < y_min) {
									sum-=y_min;
									y_min = min;
									sum+=y_min;
									index_i = i2; index_j=j2;
								}
							}

							else if(arr[i2][j2]==('B')) {
								min = (Math.abs(i-i2 )+Math.abs(j-j2))*2;
								if(b_min == Integer.MAX_VALUE) {b_min = min; sum+=b_min;index_i = i2; index_j=j2;}
								else if(min < b_min) {
									sum-=b_min;
									b_min = min;
									sum+=b_min;
									index_i = i2; index_j=j2;
								}
							}
							else if(arr[i2][j2]==('G')) {
								min = (Math.abs(i-i2 )+Math.abs(j-j2))*10;
								if(g_min == Integer.MAX_VALUE) {g_min = min; sum+=g_min;index_i = i2; index_j=j2;}
								else if(min < g_min) {
									sum-=g_min;
									g_min = min;
									sum+=g_min;
									index_i = i2; index_j=j2;
								}
							}

						}
					}

				}

				if(flag) c1[index_i][index_j]=1;

			}
		}
		return sum;

	}
}