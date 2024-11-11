create table marcas
(
    id          int auto_increment
        primary key,
    descripcion varchar(150)                 not null,
    estado      varchar(45) default 'Activo' not null
);

create table paises
(
    idpais      int auto_increment
        primary key,
    descripcion varchar(60) null,
    estado      varchar(15) null
);

create table departamentos
(
    iddepartamentos int auto_increment
        primary key,
    idpais          int         null,
    descripcion     varchar(45) null,
    estado          varchar(45) null,
    constraint departamentos_ibfk_1
        foreign key (idpais) references paises (idpais)
);

create table ciudades
(
    idciudades      int auto_increment
        primary key,
    iddepartamentos int         null,
    descripcion     varchar(45) null,
    estado          varchar(45) null,
    constraint ciudades_ibfk_1
        foreign key (iddepartamentos) references departamentos (iddepartamentos)
);

create index iddepartamentos
    on ciudades (iddepartamentos);

create table clientes
(
    idcliente      int auto_increment
        primary key,
    idciudades     int                          null,
    `nombre/razon` varchar(100)                 not null,
    `ci/ruc`       varchar(45)                  not null,
    direccion      varchar(45)                  null,
    telefono       varchar(45)                  not null,
    email          varchar(45)                  null,
    estado         varchar(15) default 'Activo' not null,
    constraint clientes_ibfk_1
        foreign key (idciudades) references ciudades (idciudades)
);

create index idciudades
    on clientes (idciudades);

create index idpais
    on departamentos (idpais);

create table factura_ventas
(
    idfactura_ventas int auto_increment
        primary key,
    codigo_cliente   int                                   null,
    iva              decimal(9)                            null,
    fecha_emision    timestamp   default CURRENT_TIMESTAMP null,
    estado           varchar(45) default 'Activo'          not null,
    total_pago       decimal(9)  default 0                 null,
    concepto         set ('Credito', 'Contado')            null,
    constraint factura_ventas_ibfk_1
        foreign key (codigo_cliente) references clientes (idcliente)
);

create index codigo_cliente
    on factura_ventas (codigo_cliente);

create table productos
(
    idproductos   int auto_increment
        primary key,
    descripcion   varchar(100) null,
    unidad_medida varchar(45)  null,
    precio_compra decimal(9)   null,
    precio_venta  decimal(9)   null,
    stock         int          null,
    estado        varchar(15)  null,
    id_marca      int          null,
    constraint productos_marcas_id_fk
        foreign key (id_marca) references marcas (id)
);

create table detalle_factura
(
    iddetalle_factura int auto_increment
        primary key,
    idfactura_ventas  int         null,
    idproductos       int         null,
    cantidad          varchar(45) null,
    precio_venta      decimal(9)  null,
    constraint detalle_factura_ibfk_1
        foreign key (idfactura_ventas) references factura_ventas (idfactura_ventas),
    constraint detalle_factura_ibfk_2
        foreign key (idproductos) references productos (idproductos)
);

create index idfactura_ventas
    on detalle_factura (idfactura_ventas);

create index idproductos
    on detalle_factura (idproductos);

create table usuarios
(
    idusuario int auto_increment
        primary key,
    user      varchar(45) null,
    password  varchar(45) null,
    acceso    varchar(45) null,
    estado    varchar(45) null,
    nombre    varchar(45) null,
    apellido  varchar(45) null,
    ci_nro    varchar(45) null
);


