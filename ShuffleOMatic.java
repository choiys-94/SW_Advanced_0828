package aaaaaaa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ShuffleOMatic {
	static int N;
	static int res = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for(int T = 1; T <= tc; T++) {
			N = sc.nextInt();
			ArrayList<Integer> cards = new ArrayList<>();
			for(int i=0; i<N; i++) {
				cards.add(sc.nextInt());
			}
			res = Integer.MAX_VALUE;
			ArrayList<Integer> arranged = new ArrayList<>(cards);
			Collections.sort(arranged);
			
			
//			for(int i=0; i<N; i++) {
//				System.out.println(shuffle(cards, i).toString());
//			}
//			System.out.println();
			
			//오름차순
			boolean flag = true;
			for(int j=0; j<N; j++) {
				if(cards.get(j) != arranged.get(j)) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				res = 0;
			}
			else {
				solve(new ArrayList<Integer>(cards), 1, arranged);
			}
			
			arranged = new ArrayList<>(cards);
			
			Collections.sort(arranged, new Comparator<Integer>(){

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o2 - o1;
				}
				
			});
			//내림차순
			
			flag = true;
			for(int j=0; j<N; j++) {
				if(cards.get(j) != arranged.get(j)) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				res = 0;
			}
			else if(res != 0) {
				solve(new ArrayList<Integer>(cards), 1, arranged);
			}
			
			if(res == Integer.MAX_VALUE) {
				res = -1;
			}
			System.out.println("#" + T + " " + res);
		}
	}
	
	static void solve(ArrayList<Integer> cards, int idx, ArrayList<Integer> arranged) {
		if(idx == 6) {
			return;
		}
		for(int i=1; i<N; i++) {
			ArrayList<Integer> tmp = shuffle(cards, i);
			boolean flag = true;
			for(int j=0; j<N; j++) {
				if(tmp.get(j) != arranged.get(j)) {
					flag = false;
					break;
				}
			}
			if(flag) {
				res = Math.min(res, idx);
				return;
			}
			solve(new ArrayList<Integer>(tmp), idx+1, arranged);
		}
	}
	
	static ArrayList<Integer> shuffle(ArrayList<Integer> cards, int x) {
		Queue<Integer> card1 = new LinkedList<>(cards.subList(0, cards.size()/2));
		Queue<Integer> card2 = new LinkedList<>(cards.subList(cards.size()/2, cards.size()));
		ArrayList<Integer> result = new ArrayList<>();
		int idx = 0;
		while(result.size() != N) {
			
			while(x > N/2) {
				if(!card2.isEmpty())
					result.add(card2.poll());
				x--;
			}
			if(idx == (N/2)-x) {
				if(!card2.isEmpty())
					result.add(card2.poll());
				x--;
			}
			if(!card1.isEmpty())
				result.add(card1.poll());
			idx++;
		}
		return result;
	}
}
