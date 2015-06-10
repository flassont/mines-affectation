insert into uv(nom,id) VALUES ('physique',777)

insert into uv(id, nom) VALUES (778, 'Probabilites et Statistiques')
insert into uv(id, nom) VALUES (779, 'Comptabilite et Gestion Financiere')
insert into uv(id, nom) VALUES (780, 'Modelisation des Systemes Physiques')

insert into module (id,datedebut,datefin,nom,uv_id) VALUES (701,'01/01/2016','31/12/2016','mécanique',777)
insert into module (id,datedebut,datefin,nom,uv_id) VALUES (702,'01/01/2016','31/12/2016','thermique',777)

insert into enseignement (id, forme, nbgroupes, nbheures, module_id) VALUES (700, 'COURS', 1, 20, 701)
insert into enseignement (id, forme, nbgroupes, nbheures, module_id) VALUES (701, 'TP', 5, 25, 701)

insert into utilisateur(id, firstname, lastname, email, password, admin) VALUES (700, 'Alex', 'GOURBILIERE', 'alex.gourbi@gmail.com', 'gourbi', true)
insert into utilisateur(id, firstname, lastname, email, password, admin) VALUES (701, 'Florian', 'LASSONT', 'florian.lassont@etudiant.mines-nantes.fr', 'test', false)

insert into affectation(id, year, intervenant_id, enseignement_id, nbgroupes) VALUES (1, 2015, 700, 700, 1)
insert into affectation(id, year, intervenant_id, enseignement_id, nbgroupes) VALUES (2, 2015, 701, 701, 1)
insert into affectation(id, year, intervenant_id, enseignement_id, nbgroupes) VALUES (3, 2015, 700, 701, 2)

