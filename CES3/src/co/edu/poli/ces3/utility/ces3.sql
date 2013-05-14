drop table if exists tbl_permisos;
drop table if exists tbl_perfiles_modulos;
drop table if exists tbl_perfiles;
drop table if exists tbl_modulos;
drop table if exists tbl_usuarios;

CREATE TABLE tbl_usuarios
(
  id_usuario serial,
  nombre character varying(255) NOT NULL,
  apellido character varying(255) NOT NULL,
  usuario character varying(15) NOT NULL,
  clave character varying(50) NOT NULL,
  CONSTRAINT pk_tbl_usuarios PRIMARY KEY (id_usuario),
  CONSTRAINT un_tbl_usuarios UNIQUE(id_usuario)
); 

CREATE TABLE tbl_modulos
(
  id_modulo serial,
  nombre character varying(255) NOT NULL,
  descripcion character varying(512),
  creado timestamp not null,
  activo boolean default true,
  CONSTRAINT pk_tbl_modulos PRIMARY KEY (id_modulo)
);
CREATE TABLE tbl_perfiles
(
  id_perfil serial,
  nombre character varying(255) NOT NULL,
  descripcion character varying(512),
  activo boolean default true,
  CONSTRAINT pk_tbl_perfiles PRIMARY KEY (id_perfil)
);
CREATE TABLE tbl_perfiles_modulos
(
  id_perfil integer,
  id_modulo integer,
  CONSTRAINT pk_tbl_perfiles_modulos PRIMARY KEY (id_perfil, id_modulo),
  CONSTRAINT fk_tbl_perfiles_usuarios_tbl_modulos FOREIGN KEY (id_modulo) REFERENCES tbl_modulos ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_tbl_perfiles_usuarios_tbl_perfiles FOREIGN KEY (id_perfil) REFERENCES tbl_perfiles ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE tbl_permisos
(
  id_perfil integer,
  id_usuario integer,
  CONSTRAINT pk_tbl_permisos PRIMARY KEY (id_perfil, id_usuario),
  CONSTRAINT fk_tbl_permisos_tbl_usuarios FOREIGN KEY (id_usuario) REFERENCES tbl_usuarios ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_tbl_permisos_tbl_perfiles FOREIGN KEY (id_perfil) REFERENCES tbl_perfiles ON DELETE CASCADE ON UPDATE CASCADE
);