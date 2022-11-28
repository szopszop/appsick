INSERT INTO public.clinics (clinic_name, latitude, longitude) VALUES ('Konsultacje Online', '0', '0');
INSERT INTO public.clinics (clinic_name, latitude, longitude) VALUES ('Klinika Czajcza', '51.18519', '17.067718');

INSERT INTO public.users (birth_date, email, first_name, last_name, password, sex, telephone_number) VALUES ('1990-12-28', 'dobroslawaszczepanska@armyspy.com', 'Dobrosława', 'Szczepańska', 'EijieceeD2', 1, '53 552 79 13');
INSERT INTO public.patients (pesel, premium, user_id) VALUES ('90122829944', true, 1);

INSERT INTO public.users (birth_date, email, first_name, last_name, password, sex, telephone_number) VALUES ('1993-09-24', 'benedyktasawicka@dayrep.com', 'Benedykta', 'Sawicka', 'Chi2Hooh', 1, '72 451 36 19');
INSERT INTO public.doctors (about, user_id) VALUES ('Two Pesos', 2);
INSERT INTO public.medical_specialities (doctor_doctor_id, medical_specialities) VALUES (1, 5);
INSERT INTO public.medical_specialities (doctor_doctor_id, medical_specialities) VALUES (1, 6);
INSERT INTO public.medical_specialities (doctor_doctor_id, medical_specialities) VALUES (1, 7);
INSERT INTO public.medical_specialities (doctor_doctor_id, medical_specialities) VALUES (1, 8);

INSERT INTO public.users (birth_date, email, first_name, last_name, password, sex, telephone_number, image) VALUES ('1997-06-24', 'fryderykakwiatkowska@teleworm.us', 'Fryderyka', 'Kwiatkowska', 'Chi2Hooh', 1, '72 451 36 19', 'https://www.getmaple.ca/site-content/uploads/2020/08/female_dr.jpg');
INSERT INTO public.doctors (about, user_id) VALUES ('Młoda i przebojowa', 3);
INSERT INTO public.medical_specialities (doctor_doctor_id, medical_specialities) VALUES (2, 27);

INSERT INTO public.users (birth_date, email, first_name, last_name, password, sex, telephone_number) VALUES ('1984-12-24', 'romanwojciechowski@armyspy.com', 'Roman', 'Wojciechowski', 'eiKah1oophe', 0, '72 672 17 82');
INSERT INTO public.doctors (about, user_id) VALUES ('Utalentowany', 4);
INSERT INTO public.medical_specialities (doctor_doctor_id, medical_specialities) VALUES (3, 35);

INSERT INTO public.visits (date, online, reason, status, clinic_id, doctor_id, patient_id) VALUES ('2023-11-10 14:54:57.000000', true, 'Dekapitacja', 0, 1, 1, 1);
INSERT INTO public.visits (date, online, reason, status, clinic_id, doctor_id, patient_id) VALUES ('2022-11-17 21:54:57.000000', true, 'Boli Gardło', 0, 1, 3, 1);
INSERT INTO public.visits (date, online, reason, status, clinic_id, doctor_id, patient_id) VALUES ('2021-06-10 10:53:22.001250', false, 'Starcze gadanie', 0, 2, 2, 1);
INSERT INTO public.visits (date, online, reason, status, clinic_id, doctor_id, patient_id) VALUES ('2021-12-15 17:04:27.000436', false, 'Dziadzienie', 0, 2, 2, 1);
