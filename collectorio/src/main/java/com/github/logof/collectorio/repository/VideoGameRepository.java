package com.github.logof.collectorio.repository;

import com.github.logof.collectorio.entitty.game.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VideoGameRepository extends JpaRepository<VideoGame, UUID> {
}
