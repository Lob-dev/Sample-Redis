package com.example.redis.hierarchical.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

/**
 * @since       2021.05.28
 * @author      lob
 * @description RoomRepository
 **********************************************************************************************************************/
public interface RoomRepository extends CrudRepository<Room, Long>, QueryByExampleExecutor<Room> {

}
