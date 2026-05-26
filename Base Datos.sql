
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'bd_latinoamericano')
    BEGIN
        CREATE DATABASE bd_latinoamericano
    END;

go;

USE bd_latinoamericano;
go;

CREATE FUNCTION dbo.get_ip_client()
    RETURNS varchar(55)
AS
BEGIN
    RETURN (SELECT client_net_address FROM sys.dm_exec_connections WHERE session_id = @@SPID);
END;
go

CREATE FUNCTION dbo.get_ip_server()
    RETURNS varchar(55)
AS
BEGIN
    RETURN (SELECT local_net_address FROM sys.dm_exec_connections WHERE session_id = @@SPID);
END;
go

CREATE FUNCTION dbo.get_session_id()
    RETURNS varchar(55)
AS
BEGIN
    RETURN (SELECT session_id FROM sys.dm_exec_connections WHERE session_id = @@SPID);
END;
go




IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'dbo' AND  TABLE_NAME = 'clientes')
    BEGIN
        CREATE TABLE clientes (
                                  id int identity (600, 1) constraint T_CLIENTES_PK primary key,

                                  cliente_id VARCHAR(20) NOT NULL,

                                  cliente_id VARCHAR(20), /*campo de auditoria interna*/

                                  nombre varchar(255) NOT NULL,
                                  genero char(1),
                                  edad INT,
                                  identificacion VARCHAR(15) UNIQUE NOT NULL,
                                  direccion VARCHAR(255),
                                  telefono VARCHAR(20),

                                  estado  bit default 1,
                                  contrasena varchar(50) not null,
                                  reg_date datetime default getdate(),
                                  ip_client varchar(50) default(dbo.get_ip_client()),
                                  ip_server varchar(50) default(dbo.get_ip_server()),
                                  session_id varchar(50) default(dbo.get_session_id()),

                                  estado  char(1) default '1',
                                  contrasena varchar(50) not null,

                                  CONSTRAINT CK_persona_gender CHECK (genero IN ('M', 'F','O'))
        );
    End

go



IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'dbo' AND  TABLE_NAME = 'cuentas')
    BEGIN
        CREATE TABLE cuentas (
                                 cliente_id int constraint CUENTA_CLIENTE_ID  references clientes(id),
                                 numero_cuenta varchar(50) not null constraint U_ACCOUNT_PK  PRIMARY KEY ,
                                 tipo_cuenta  char(2),
                                 saldo_inicial DECIMAL(10,2) NOT NULL,

                                 estado   bit default 1,

                                 estado   char(1) default '1',

                                 date_reg datetime default getdate(),
                                 ip_client varchar(50) default(dbo.get_ip_client()),
                                 ip_server varchar(50) default(dbo.get_ip_server()),
                                 session_id varchar(50) default(dbo.get_session_id()),
                                 CONSTRAINT tipo_cuenta_check CHECK (tipo_cuenta IN ('AH', 'CR','VAR'))
        );
    END;

go




IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'dbo' AND  TABLE_NAME = 'movimientos')
    BEGIN
        CREATE TABLE movimientos (
                                     id int identity (600, 1) constraint T_TRANSACTION_PK primary key,
                                     tipo_movimiento char(2) NOT NULL,
                                     cantidad DECIMAL(10,2) default 0 ,
                                     valor DECIMAL(10,2) NOT NULL,
                                     saldo DECIMAL(10,2),
                                     numero_cuenta varchar(50) constraint TRANSACTION_ACCOUNT_ID  references cuentas(numero_cuenta),

                                     estado   bit default 1,

                                     estado   char(1) default '1',

                                     date_reg datetime default getdate(),
                                     ip_client varchar(50) default(dbo.get_ip_client()),
                                     ip_server varchar(50) default(dbo.get_ip_server()),
                                     session_id varchar(50) default(dbo.get_session_id()),
                                     CONSTRAINT ck_transaction_type CHECK (tipo_movimiento IN ('DE', 'RE','TR','PA')),
        );
    end

go



CREATE OR ALTER TRIGGER trg_movimientos_actualizar_saldo_cantidad



CREATE TRIGGER tr_calculoSaldos

    ON movimientos
    AFTER INSERT
    AS
BEGIN
    SET NOCOUNT ON;

    -- 1. Actualizar CANTIDAD = # transacciones del día por cuenta
    UPDATE m
    SET cantidad = (
        SELECT COUNT(*)
        FROM movimientos m_dia
        WHERE m_dia.numero_cuenta = i.numero_cuenta
          AND CAST(m_dia.date_reg AS DATE) = CAST(i.date_reg AS DATE)
    )
    FROM movimientos m
             INNER JOIN inserted i ON m.id = i.id;

    -- 2. Actualizar SALDO acumulativo
    UPDATE m
    SET saldo = (
        ISNULL((
                   SELECT saldo
                   FROM movimientos m_prev
                   WHERE m_prev.numero_cuenta = i.numero_cuenta
                     AND m_prev.id = (
                       SELECT MAX(id)
                       FROM movimientos
                       WHERE numero_cuenta = i.numero_cuenta
                         AND id < i.id
                   )
               ), 0) +
        CASE
            WHEN i.tipo_movimiento = 'DE' THEN +i.valor
            WHEN i.tipo_movimiento IN ('RE', 'PA') THEN -i.valor
            ELSE 0
            END
        )
    FROM movimientos m
             INNER JOIN inserted i ON m.id = i.id;
END;
GO


CREATE TRIGGER trg_GenerarClienteId
    ON clientes
    AFTER INSERT
    AS
BEGIN
    SET NOCOUNT ON;

    UPDATE c
    SET cliente_id = ABS(CHECKSUM(NEWID())) % 9000 + 1000
    FROM clientes c
             INNER JOIN inserted i ON c.id = i.id  -- Reemplaza 'pk_id' por tu PK real
    WHERE c.cliente_id IS NULL OR c.cliente_id = 0;
END;
GO


/*
drop trigger trg_GenerarClienteId
go
drop trigger tr_calculoSaldos
go
drop table movimientos
go
drop table cuentas
go
drop table clientes
go
*/

