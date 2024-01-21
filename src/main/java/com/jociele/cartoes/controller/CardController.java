package com.jociele.cartoes.controller;

import com.jociele.cartoes.model.CardModel;
import com.jociele.cartoes.repository.InterfaceCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/card")
@CrossOrigin(origins ="http://localhost:9000")
public class CardController {


    @Autowired
    private InterfaceCardRepository interfaceCardRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<CardModel>> list(@RequestParam(required = false) String nome) {
        try {
            List<CardModel> listaAll = new ArrayList<CardModel>();

            if (nome == null) {
                interfaceCardRepository.findAll().forEach(listaAll::add);
            } else {
                interfaceCardRepository.findByNome(nome).forEach(listaAll::add);

            }
            if(listaAll.isEmpty()){
                return new ResponseEntity<>(listaAll, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listaAll,HttpStatus.OK);


        }catch (Exception e){
            return null;
        }
    }

    @GetMapping("/listarid/{id}")
    public ResponseEntity<CardModel> getCardById(@PathVariable("id") long id_card) {
        Optional<CardModel> cardData = interfaceCardRepository.findById(id_card);

        if (cardData.isPresent()) {
            return new ResponseEntity<>(cardData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/salvar")
    public ResponseEntity<CardModel> createCard(@RequestBody CardModel cardModel) {
        try {
            CardModel cardInsert = new CardModel(cardModel.getNome(), cardModel.getNumero(), cardModel.getCvv(), cardModel.getStatus());
            CardModel card = interfaceCardRepository.save(cardInsert);
            return new ResponseEntity<>(card, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<CardModel> updateTutorial(@PathVariable("id") long id_card, @RequestBody CardModel cardModel) {
        Optional<CardModel> cardData = interfaceCardRepository.findById(id_card);

        if (cardData.isPresent()) {
            CardModel cardModel1 = cardData.get();
            cardModel1.setNome (cardModel.getNome());
            cardModel1.setNumero(cardModel.getNumero());
            cardModel1.setCvv(cardModel.getCvv());
            cardModel1.setStatus(cardModel.getStatus());
            return new ResponseEntity<>(interfaceCardRepository.save(cardModel1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCard(@PathVariable("id") long id_card) {
        try {
            interfaceCardRepository.deleteById(id_card);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllCards() {
        try {
            interfaceCardRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}


