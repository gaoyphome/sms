package com.bmac.ffan.modular.basebusiness.warpper;

import java.util.Arrays;
import java.util.List;

public class Person {
	String name;
	int age;
	
	public Person(String name, int age) {
		super();
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
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	public static void main(String[] args) {
		List<Person> list = Arrays.asList(new Person("a",1),
				new Person("b",2),
				new Person("c",3));
		BusCompanyWarpper warp = new BusCompanyWarpper(list);
		System.out.println(warp.warp());
	}
}
