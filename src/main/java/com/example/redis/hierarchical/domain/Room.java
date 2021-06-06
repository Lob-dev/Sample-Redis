package com.example.redis.hierarchical.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.List;

/**
 * @since       2021.05.28
 * @author      lob
 * @description Room
 **********************************************************************************************************************/
@Getter
@Builder
@RedisHash("chat.room")
public class Room implements Serializable {

	@Id
	private Long id;

	@Indexed
	private Long chatId;

	private List<Creator> creators;

	public void addCreators(List<Creator> creators) {
		this.creators.addAll(creators);
	}

	@Override
	public String toString() {
		return "Room{" +
				"id=" + id +
				'}';
	}
}
