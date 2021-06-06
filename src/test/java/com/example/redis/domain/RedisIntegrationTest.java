package com.example.redis.domain;


import com.example.redis.hierarchical.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @since       2021.05.28
 * @author      lob
 * @description RedisIntegrationTest
 **********************************************************************************************************************/
public class RedisIntegrationTest extends TestIntegrationContext{

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private RoomRepository roomRepository;

	@BeforeEach
	public void init() {
		List<Room> rooms = getRooms(1L, getCreators(1L, 2L, 3L));
		rooms.addAll(getRooms(2L, getCreators(4L, 5L, 6L)));

		Chat chat = Chat.builder()
				.id(1L)
				.rooms(rooms)
				.build();

		chatRepository.save(chat);
	}

	@Test
	void addChat_test() {

		Chat room = chatRepository.findById(1L)
				.orElseGet(() -> Chat.builder().build());
		room.addRooms(getRooms(3L, getCreators(7L, 8L, 9L)));
		chatRepository.save(room);

		Chat roomEntity = chatRepository.findById(room.getId())
				.orElseGet(() -> Chat.builder().build());

		System.out.println("room = " + room);
		System.out.println("roomEntity = " + roomEntity);
	}

	@Test
	void addRoom_test() {

		Room room = Room.builder()
				.id(3L)
				.creators(getCreators(1L, 2L, 3L))
				.chatId(1L)
				.build();

		Room room1 = Room.builder()
				.id(4L)
				.creators(getCreators(4L, 5L, 6L))
				.chatId(2L)
				.build();

		Room saveByChatOne = roomRepository.save(room);
		Room saveByChatTwo = roomRepository.save(room1);
		assertNotEquals(saveByChatOne, saveByChatTwo);


		Iterable<Room> findAll = roomRepository.findAll();
		Iterable<Room> findAllByExample = roomRepository.findAll(Example.of(Room.builder().chatId(1L).build()));
		assertNotEquals(findAll, findAllByExample);
	}

	private List<Creator> getCreators(Long firstId, Long SecondId, Long thirdId) {
		Creator creator1 = Creator.builder()
				.id(firstId)
				.name("lob")
				.email("lob@hello.com")
				.build();

		Creator creator2 = Creator.builder()
				.id(SecondId)
				.name("bol")
				.email("bol@hello.com")
				.build();

		Creator creator3 = Creator.builder()
				.id(thirdId)
				.name("lbo")
				.email("lbo@hello.com")
				.build();

		List<Creator> creators = new ArrayList<>();
		creators.add(creator1);
		creators.add(creator2);
		creators.add(creator3);
		return creators;
	}
	private List<Room> getRooms(Long roomId, List<Creator> creatorsByRoomOne) {
		Room roomOne = Room.builder()
				.id(roomId)
				.creators(creatorsByRoomOne)
				.build();

		List<Room> rooms = new ArrayList<>();
		rooms.add(roomOne);
		return rooms;
	}

}
