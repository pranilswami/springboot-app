package com.app.repository.evaluation;

import com.app.entity.evaluation.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    @Query("SELECT a FROM Agent a WHERE a.id =:agentId")
    List<Agent> findByAgentId(@Param("agentId") Agent agentId);

    @Query("SELECT a FROM Agent a WHERE a.name =:name")
    Optional<Agent> findByAgentName(@Param("name") String name);
}