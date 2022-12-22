package com.github.logof.collectorio.repository;

import com.github.logof.collectorio.entitty.game.CollectableGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CollectableGameRepository extends JpaRepository<CollectableGame, UUID> {

}
