// IRemoteService.aidl
package com.example.ai.aidl;

//数据类型不支持short类型,如果是List类型 要用in或out声明是输入或输出：
// 如：in List<String> str
interface IRemoteService {

     /**
      * 计算两个数的和
      */
    int add(int num1,int num2);


}
