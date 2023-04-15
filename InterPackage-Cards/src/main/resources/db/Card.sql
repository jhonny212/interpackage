CREATE TABLE public.card_transaction(
    card_transaction_id bigint DEFAULT 0 NOT NULL,
    card_id bigint DEFAULT 0 NOT NULL,
    date timestamp DEFAULT '1900-01-01 00:00:00'::timestamp NOT NULL,
    amount numeric(23,20) DEFAULT 0.0 NOT NULL,
    payment_reference bigint DEFAULT 0 NOT NULL,
    CONSTRAINT card_transaction PRIMARY KEY (card_transaction_id)
);

CREATE TABLE public.card(
    card_id bigint DEFAULT 0 NOT NULL,
    expired_date timestamp DEFAULT '1900-01-01 00:00:00'::timestamp NOT NULL,
    csv int DEFAULT 0 NOT NULL,
    card_type_id bigint DEFAULT 0 NOT NULL,
    payment_network_id bigint DEFAULT 0 NOT NULL,
    CONSTRAINT card PRIMARY KEY (card_id)
);

CREATE TABLE public.card_type(
    card_type_id bigint DEFAULT 0 NOT NULL,
    name  character varying(75) DEFAULT ''::character varying NOT NULL,
    description  character varying(500) DEFAULT ''::character varying NOT NULL,
    CONSTRAINT card PRIMARY KEY (card_type_id)
);

CREATE TABLE public.payment_network(
    payment_network_id bigint DEFAULT 0 NOT NULL,
    name  character varying(75) DEFAULT ''::character varying NOT NULL,
    description  character varying(500) DEFAULT ''::character varying NOT NULL,
    CONSTRAINT card PRIMARY KEY (payment_network_id)
);

--
---Adding constraints----
--
ALTER TABLE ONLY public.card_transaction
    ADD CONSTRAINT card_transaction_card__fk FOREIGN KEY (card_id) REFERENCES public.card(card_id);

ALTER TABLE ONLY public.card
    ADD CONSTRAINT card_card_type_fk FOREIGN KEY (card_type_id) REFERENCES public.card_type(card_type_id);

ALTER TABLE ONLY public.card
    ADD CONSTRAINT card_payment_network_fk FOREIGN KEY (payment_network_id) REFERENCES public.payment_network(payment_network_id);