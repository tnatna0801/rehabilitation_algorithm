package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_14719_빗물 {
	static int n, m, map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		map=new int[n][m];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			int now=Integer.parseInt(st.nextToken());
			for(int j=0;j<now;j++) {
				map[j][i]=1;
			}
		}
		
		int result=0;
		for(int i=0;i<n;i++) {
			int cnt=0;
			for(int j=1;j<m;j++) {
				
				if(map[i][j]==1) {
					if(cnt!=0) {
						result+=cnt;
						cnt=0;
					}
					continue;
				}
				if(cnt==0) {
					if(map[i][j-1]==1) {
						cnt++;
					}
				}else if(j==m-2) {
					if(map[i][j+1]!=1) {
						cnt=0;
					}else {
						cnt++;
					}
				}else if(j==m-1){
					if(map[i][j]!=1) {
						cnt=0;
					}
				}else if(cnt>0) {
					cnt++;
				}
			}
		}
		
		System.out.println(result);
		
	}
}
