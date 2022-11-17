ALTER TABLE IF EXISTS ONLY public.chat_message
    DROP CONSTRAINT IF EXISTS pk_chat_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.clinic
    DROP CONSTRAINT IF EXISTS pk_clinic_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.doctor
    DROP CONSTRAINT IF EXISTS pk_doctor_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.patient
    DROP CONSTRAINT IF EXISTS pk_patient_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.users
    DROP CONSTRAINT IF EXISTS pk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.visit
    DROP CONSTRAINT IF EXISTS pk_visit_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.chat_message
    DROP CONSTRAINT IF EXISTS fk_visit_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.doctor
    DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.patient
    DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.visit
    DROP CONSTRAINT IF EXISTS fk_clinic_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.visit
    DROP CONSTRAINT IF EXISTS fk_doctor_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.visit
    DROP CONSTRAINT IF EXISTS fk_patient_id CASCADE;

DROP TABLE IF EXISTS public.chat_message CASCADE;
DROP TABLE IF EXISTS public.clinic CASCADE;
DROP TABLE IF EXISTS public.doctor CASCADE;
DROP TABLE IF EXISTS public.doctor_medical_specialities CASCADE;
DROP TABLE IF EXISTS public.patient CASCADE;
DROP TABLE IF EXISTS public.users CASCADE;
DROP TABLE IF EXISTS public.visit CASCADE;

CREATE TABLE chat_message (
                              chat_id serial NOT NULL,
                              author varchar(255) NOT NULL,
                              date timestamp NOT NULL,
                              message varchar(255) NOT NULL,
                              visit_id int8 NOT NULL
);
CREATE TABLE clinic (
                        clinic_id serial NOT NULL,
                        clinic_name varchar(255) NOT NULL,
                        latitude varchar(255) NOT NULL,
                        longitude varchar(255) NOT NULL
);
CREATE TABLE doctor (
                        doctor_id serial NOT NULL,
                        about varchar(255),
                        user_id int8 NOT NULL
);
CREATE TABLE doctor_medical_specialities (
                                             doctor_doctor_id     int8 NOT NULL,
                                             medical_specialities int4 NOT NULL
);
CREATE TABLE patient (
                         patient_id serial NOT NULL,
                         pesel varchar(255) NOT NULL,
                         premium boolean NOT NULL,
                         user_id int8 NOT NULL
);
CREATE TABLE users (
                       user_id serial NOT NULL,
                       birth_date date,
                       email varchar(255) NOT NULL,
                       first_name varchar(255),
                       image varchar(255),
                       last_name varchar(255),
                       password varchar(255) NOT NULL,
                       sex int4,
                       telephone_number varchar(255)
);
CREATE TABLE visit (
                       visit_id serial NOT NULL,
                       date timestamp NOT NULL,
                       online boolean NOT NULL,
                       reason varchar(255) NOT NULL,
                       status int4 NOT NULL,
                       clinic_id int8 NOT NULL,
                       doctor_id int8 NOT NULL,
                       patient_id int8 NOT NULL
);

ALTER TABLE ONLY public.chat_message
    ADD CONSTRAINT pk_chat_id PRIMARY KEY (chat_id);
ALTER TABLE ONLY public.clinic
    ADD CONSTRAINT pk_clinic_id PRIMARY KEY (clinic_id);
ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT pk_doctor_id PRIMARY KEY (doctor_id);
ALTER TABLE ONLY public.patient
    ADD CONSTRAINT pk_patient_id PRIMARY KEY (patient_id);
ALTER TABLE ONLY public.users
    ADD CONSTRAINT pk_user_id PRIMARY KEY (user_id);
ALTER TABLE ONLY public.visit
    ADD CONSTRAINT pk_visit_id PRIMARY KEY (visit_id);

ALTER TABLE ONLY public.chat_message
    ADD CONSTRAINT fk_visit_id FOREIGN KEY (visit_id) REFERENCES public.visit (visit_id);
ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users (user_id);
ALTER TABLE ONLY public.patient
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users (user_id);
ALTER TABLE ONLY public.visit
    ADD CONSTRAINT fk_patient_id FOREIGN KEY (patient_id) REFERENCES public.patient (patient_id);
ALTER TABLE ONLY public.visit
    ADD CONSTRAINT fk_doctor_id FOREIGN KEY (doctor_id) REFERENCES public.doctor (doctor_id);
ALTER TABLE ONLY public.visit
    ADD CONSTRAINT fk_clinic_id FOREIGN KEY (clinic_id) REFERENCES public.clinic (clinic_id);

INSERT INTO public.clinic (clinic_name, latitude, longitude) VALUES ('Konsultacje Online', '0', '0');
INSERT INTO public.clinic (clinic_name, latitude, longitude) VALUES ('Klinika Czajcza', '51.18519', '17.067718');

INSERT INTO public.users (birth_date, email, first_name, last_name, password, sex, telephone_number) VALUES ('1990-12-28', 'dobroslawaszczepanska@armyspy.com', 'Dobrosława', 'Szczepańska', 'EijieceeD2', 1, '53 552 79 13');
INSERT INTO public.patient (pesel, premium, user_id) VALUES ('90122829944', true, 1);

INSERT INTO public.users (birth_date, email, first_name, last_name, password, sex, telephone_number) VALUES ('1993-09-24', 'benedyktasawicka@dayrep.com', 'Benedykta', 'Sawicka', 'Chi2Hooh', 1, '72 451 36 19');
INSERT INTO public.doctor (about, user_id) VALUES ('Two Pesos', 2);
INSERT INTO public.doctor_medical_specialities (doctor_doctor_id, medical_specialities) VALUES (1, 5);
INSERT INTO public.doctor_medical_specialities (doctor_doctor_id, medical_specialities) VALUES (1, 6);
INSERT INTO public.doctor_medical_specialities (doctor_doctor_id, medical_specialities) VALUES (1, 7);
INSERT INTO public.doctor_medical_specialities (doctor_doctor_id, medical_specialities) VALUES (1, 8);

INSERT INTO public.users (birth_date, email, first_name, last_name, password, sex, telephone_number) VALUES ('1997-06-24', 'fryderykakwiatkowska@teleworm.us', 'Fryderyka', 'Kwiatkowska', 'Chi2Hooh', 1, '72 451 36 19');
INSERT INTO public.doctor (about, user_id) VALUES ('Młoda i przebojowa', 2);
INSERT INTO public.doctor_medical_specialities (doctor_doctor_id, medical_specialities) VALUES (2, 27);

INSERT INTO public.visit (date, online, reason, status, clinic_id, doctor_id, patient_id) VALUES ('2023-11-10 14:54:57.000000', true, 'Dekapitacja', 0, 1, 1, 1);
INSERT INTO public.visit (date, online, reason, status, clinic_id, doctor_id, patient_id) VALUES ('2021-06-10 10:53:22.001250', false, 'Starcze gadanie', 0, 2, 2, 1);
INSERT INTO public.visit (date, online, reason, status, clinic_id, doctor_id, patient_id) VALUES ('2021-12-15 17:04:27.000436', false, 'Dziadzienie', 0, 2, 2, 1);
