// IMyAidlInterface.aidl
package com.example.ai.aidl;

// Declare any non-default types here with import statements
import com.example.ai.aidl.Person;

interface IMyAidlInterface {


      List<Person> add(in Person person);
}
