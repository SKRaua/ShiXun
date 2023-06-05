#include <iostream>

using namespace std;

struct Boy //小孩节点
{
    int code;
    Boy *pNext;
};

Boy *pFirt = 0;    //第一个小孩指针
Boy *pCurrent = 0; //当前小孩指针
Boy *pivot = 0;    //上一个小孩指针
int main()
{
    //输入小孩总数
    int numOfBoy;
    cout << "please input the number of the boys: ";
    cin >> numOfBoy;

    //输入小孩间隔
    int m; // Josephus问题中的间隔m
    cout << "please input the interval: ";
    cin >> m;

    // n个小孩围成圈
    //添加第一个小孩
    pFirt = new Boy;
    pFirt->code = 1;
    pCurrent = pFirt;

    //添加其他小孩
    for (int i = 1; i < numOfBoy; i++)
    {
        pivot = pCurrent;        //前一个小孩改为"当前的小孩"
        pCurrent = new Boy;      //在当前的小孩添加一个小孩
        pCurrent->code = i + 1;  //为加入的小孩编号
        pivot->pNext = pCurrent; //前一个小孩指向当前的小孩
    }
    pCurrent->pNext = pFirt; //最后一个小孩指向第一个，此时pCurrent还在最后一个小孩

    //输出开始时的小孩编号
    for (int i = 0; i < numOfBoy; i++)
    {
        pCurrent = pCurrent->pNext;
        cout << pCurrent->code << ",";
    }
    cout << endl;

    //小孩依次离开,，此时pCurrent还在最后一个小孩
    while (pCurrent->pNext != pCurrent)
    {
        int j;
        j = m; //第m个小孩离开
        do
        {
            //将位置移到下一个小孩
            pivot = pCurrent;
            pCurrent = pCurrent->pNext;
            j--;         //数一个小孩
        } while (j > 0); //循环m次表示数m个小孩

        cout << pCurrent->code << "'s out,"; //输出离开小孩编号
        pivot->pNext = pCurrent->pNext;      //表示该位置小孩离开
        delete pCurrent;                     //删除这个小孩
        pCurrent = pivot;                    // pCurrent退回到上一小孩
    }
    cout << endl;

    //宣布获胜者
    cout << pCurrent->code << " is the winner.";
    cout << endl;

    system("pause");
}
