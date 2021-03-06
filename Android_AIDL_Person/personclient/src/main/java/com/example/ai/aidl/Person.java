package com.example.ai.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AI on 2018/3/28.
 */

public class Person implements Parcelable{

    private String name;
    private int age;

    public Person(Parcel source){
        this.name=source.readString();
        this.age=source.readInt();
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeInt(age);
    }

    public static final Creator<Person> CREATOR=new Creator<Person>(){
        @Override
        public Person createFromParcel(Parcel source) {

            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
