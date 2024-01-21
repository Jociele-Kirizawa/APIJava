package com.jociele.cartoes.repository;

import com.jociele.cartoes.model.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterfaceCardRepository extends JpaRepository<CardModel, Long> {

        public List<CardModel> findByNome(String nome);


    }

