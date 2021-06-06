package com.example.redis.hierarchical.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @since       2021.05.28
 * @author      lob
 * @description Creator
 **********************************************************************************************************************/
@Getter
@Builder
@RedisHash("chat.room.creator")
public class Creator implements Serializable {

	private Long id;
	private String name;
	private String email;

	@Override
	public String toString() {
		return "Creator{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
