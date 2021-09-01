SET
default_tablespace = '';
SET
default_table_access_method = heap;
--
-- Name: address; Type: TABLE; Schema: public; Owner: dbuser
--
CREATE TABLE public.address
(
  id      uuid NOT NULL,
  city    character varying,
  state   character varying,
  country character varying
);
ALTER TABLE public.address OWNER TO dbuser;
--
-- Name: booking; Type: TABLE; Schema: public; Owner: dbuser
--
CREATE TABLE public.booking
(
  id                uuid NOT NULL,
  check_in_date     character varying,
  check_out_date    character varying,
  property_id       uuid,
  payment_method_id uuid,
  customer_id       uuid,
  cost_id           uuid
);
ALTER TABLE public.booking OWNER TO dbuser;
--
-- Name: cost; Type: TABLE; Schema: public; Owner: dbuser
--
CREATE TABLE public.cost
(
  id       uuid NOT NULL,
  cost     integer,
  currency character varying
);
ALTER TABLE public.cost OWNER TO dbuser;
--
-- Name: customer; Type: TABLE; Schema: public; Owner: dbuser
--
CREATE TABLE public.customer
(
  id            uuid NOT NULL,
  name          character varying,
  surname       character varying,
  date_of_birth character varying,
  email         character varying
);
ALTER TABLE public.customer OWNER TO dbuser;
--
-- Name: parking_info; Type: TABLE; Schema: public; Owner: dbuser
--
CREATE TABLE public.parking_info
(
  id               uuid NOT NULL,
  covered          boolean,
  parking_provider character varying
);
ALTER TABLE public.parking_info OWNER TO dbuser;
--
-- Name: payment_method; Type: TABLE; Schema: public; Owner: dbuser
--
CREATE TABLE public.payment_method
(
  id            uuid NOT NULL,
  payment_type  character varying,
  provider_name character varying
);
ALTER TABLE public.payment_method OWNER TO dbuser;
--
-- Name: property; Type: TABLE; Schema: public; Owner: dbuser
--
CREATE TABLE public.property
(
  id              uuid NOT NULL,
  address_id      uuid,
  num_of_bedrooms integer,
  num_of_wc       integer,
  floor           integer,
  size            integer,
  parking_info_id uuid,
  is_furnished    boolean
);
ALTER TABLE public.property OWNER TO dbuser;
--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser
--
ALTER TABLE ONLY public.address
  ADD CONSTRAINT address_pkey PRIMARY KEY (id);
--
-- Name: booking booking_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser
--
ALTER TABLE ONLY public.booking
  ADD CONSTRAINT booking_pkey PRIMARY KEY (id);
--
-- Name: cost cost_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser
--
ALTER TABLE ONLY public.cost
  ADD CONSTRAINT cost_pkey PRIMARY KEY (id);
--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser
--
ALTER TABLE ONLY public.customer
  ADD CONSTRAINT customer_pkey PRIMARY KEY (id);
--
-- Name: parking_info parking_info_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser
--
ALTER TABLE ONLY public.parking_info
  ADD CONSTRAINT parking_info_pkey PRIMARY KEY (id);
--
-- Name: payment_method payment_method_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser
--
ALTER TABLE ONLY public.payment_method
  ADD CONSTRAINT payment_method_pkey PRIMARY KEY (id);
--
-- Name: property property_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser
--
ALTER TABLE ONLY public.property
  ADD CONSTRAINT property_pkey PRIMARY KEY (id);
--
-- PostgreSQL database dump complete
--
