#include <iostream>
using namespace std;

int main()
{
    const int numOfBoy = 7; //小孩总数
    int boys[numOfBoy];     //小孩数组

    // n个小孩围成圈
    int i = 0; //小孩位置指示器
    for (i = 0; i < numOfBoy; i++)
    {
        boys[i] = i + 1;
    }

    cout << "The number of boys: " << numOfBoy << endl;
    //输入小孩间隔
    int m; // Josephus问题中的间隔m
    cout << "please input the interval: ";
    cin >> m;

    //输出开始时的小孩编号
    for (i = 0; i < numOfBoy; i++)
    {
        cout << boys[i] << ",";
    }
    cout << endl;

    //小孩依次离开
    i = numOfBoy - 1;                  //将i定位到最后一个小孩，下一次移动位能够得到i=0
    for (int n = numOfBoy; n > 1; n--) //离开 numOfBoy - 1 个小孩
    {
        //数到第m个小孩离开
        int j = m;
        do
        {
            //将位置移到下一个小孩
            i = (i + 1) % numOfBoy;
            while (boys[i] == 0)
            {
                i = (i + 1) % numOfBoy;
            }

            j--; //数一个小孩
        } while (j > 0);

        //第m个小孩离开
        cout << boys[i] << "'s out,"; //输出离开小孩编号
        boys[i] = 0;                  //表示i小孩离开
    }
    cout << endl;

    //宣布获胜者
    for (i = 0; i < numOfBoy; i++)
    {
        if (boys[i] != 0)
        {
            cout << boys[i] << " is the winer." << endl;
            break;
        }
    }
    system("pause");
}
