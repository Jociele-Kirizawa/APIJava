package com.jociele.cartoes.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;



    @Entity
    @Data
    @Table(name="cartao")
    public class CardModel  {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        long id_card;

        @Column (name = "cvv", length=3)
        int cvv;

        @Column(name = "nome",length=150)
        String nome;

        @Column(name = "numero", length=10)
        String numero;

        @Column (name = "status")
        int status;

        public CardModel(String nome, String numero, int cvv, int status) {
            this.nome = nome;
            this.numero = numero;
            this.cvv = cvv;
            this.status = status;
        }

        public CardModel() {

        }
    }


