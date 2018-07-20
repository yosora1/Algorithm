using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bisection
{
    class Bisection
    {
        public void Bise(int[] list) {
            int start;
            int end;
            int temp, mid;
            
            for (int i = 1; i < list.Length;i++ )
            {
                start = 0;
                temp = list[i];
                end = i - 1;
                //定位
                
                while (start <= end)
                {
                    mid = (start + end) / 2;
                    if (list[mid] > temp)
                    {
                        end = mid - 1;
                    }
                    else if (list[mid] < temp)
                    {
                        start = mid + 1;
                    }
                }
                //移动数组
                for (int j = i; j > start; j--)
                {
                    list[j] = list[j - 1];
                }
                //插入
                list[start] = temp;
                
                foreach (int a in list)
                {
                    Console.Write("{0} ",a);
                }
                Console.WriteLine("第{0}次",i);
                
            }
            Console.ReadKey();
        }
        static void Main(string[] args)
        {
            int[] list = { 1, 13, 22, 43, 21, 35, 2, 33, 65, 8, 19 };
            Bisection bise = new Bisection();
            bise.Bise(list);
        }
    }
}
