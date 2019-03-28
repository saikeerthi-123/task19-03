package six;
import java.util.*;
public class six6 {
	@SuppressWarnings({ "resource", "unchecked" })
	public static void main(String args[])

    {

        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();

        int k=sc.nextInt();

        int a[]=new int[n];

        for(int i=0;i<n;i++)

            a[i]=sc.nextInt();

        ArrayList<Integer> arr[]=new ArrayList[k];

        for(int i=0;i<k;i++)

            arr[i]=new ArrayList<Integer>();

        for(int i=0;i<n;i++)

        {

            int weight=a[i]%k;

            arr[weight].add(a[i]);

        }

        for(int j=k-1;j>=0;j--)

        {

            Collections.sort(arr[j]);

            for(int i=0;i<=arr[j].size()-1;i++)

            {

                System.out.print(arr[j].get(i)+" ");

            }

        }

      

    }

}
