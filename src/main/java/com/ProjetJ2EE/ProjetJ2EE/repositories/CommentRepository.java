package com.ProjetJ2EE.ProjetJ2EE.repositories;

import com.ProjetJ2EE.ProjetJ2EE.entities.Comment;
import com.ProjetJ2EE.ProjetJ2EE.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long>
{
    List<Comment> findByGame(Game game);

}
