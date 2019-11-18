package org.lantu.utils;

import java.util.List;

/**
 * Created by runshu.lin on 2018/4/8.
 */
public class User {
	private int id;
	private String name;

	private List<Integer> dl;

	public User() {
	}

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return 2;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getDl() {
		return dl;
	}

	public void setDl(List<Integer> dl) {
		this.dl = dl;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
