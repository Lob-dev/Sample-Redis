package com.example.redis.hierarchical.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

/**
 * @since       2021.05.28
 * @author      lob
 * @description Chat
 **********************************************************************************************************************/
@Getter
@Builder
@RedisHash("chat")
public class Chat implements Serializable {

	@Id
	private final Long id;
	private final List<Room> rooms;

	public void addRooms(List<Room> rooms) {
		this.rooms.addAll(rooms);
	}

	@Override
	public String toString() {
		return "Chat{" +
				"id=" + id +
				'}';
	}
}
