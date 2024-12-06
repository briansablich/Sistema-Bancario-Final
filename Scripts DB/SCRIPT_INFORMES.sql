-- VISTA SALDO POR CLIENTE (PARA INFORME 1)
DROP VIEW IF EXISTS vw_saldo_por_cliente;

CREATE VIEW vw_saldo_por_cliente AS
SELECT id_cliente, SUM(saldo) AS total_saldo
FROM cuentas
GROUP BY id_cliente
ORDER BY total_saldo DESC;

-- INFORME 1: SP BUSCA SALDOS POR CLIENTE MAYORES A
DROP PROCEDURE IF EXISTS sp_buscar_saldos_mayores;
DELIMITER //

CREATE PROCEDURE sp_buscar_saldos_mayores(IN num FLOAT)
BEGIN
    SELECT id_cliente, total_saldo
    FROM vw_saldo_por_cliente
    WHERE total_saldo >= num
    ORDER BY total_saldo ASC;
END //

DELIMITER ;


-- INFORME 2: SP BUSCA SALDOS POR CLIENTE MENORES A
DROP PROCEDURE IF EXISTS sp_buscar_saldos_menores;
DELIMITER //

CREATE PROCEDURE sp_buscar_saldos_menores(IN num FLOAT)
BEGIN
    SELECT id_cliente, total_saldo
    FROM vw_saldo_por_cliente
    WHERE total_saldo <= num
    ORDER BY total_saldo DESC;
END //

DELIMITER ;

-- INFORME 3: CANTIDAD DE CLIENTES POR PROVINCIA
DROP VIEW IF EXISTS vw_clientes_por_provincia;

CREATE VIEW vw_clientes_por_provincia AS
SELECT count(id_cliente) AS ClientesXProvincia, clientes.provincia AS id_provincia, tProv.provincia 
FROM clientes
INNER JOIN provincias AS tProv ON clientes.provincia = tProv.id_provincia 
GROUP BY clientes.provincia
ORDER BY ClientesXProvincia DESC

DELIMITER //

CREATE PROCEDURE BuscarEntreFechas(IN fecha_inicio DATE, IN fecha_fin DATE, IN id_cuenta INT)
BEGIN
    SELECT * FROM movimientos 
    WHERE movimientos.fecha >= fecha_inicio AND movimientos.fecha <= fecha_fin
    AND (movimientos.id_cuenta_origen = id_cuenta OR movimientos.id_cuenta_destino = id_cuenta)
    ORDER BY movimientos.fecha;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE PagarCuota(IN p_cbu_origen VARCHAR(22), IN id_cuota_apagar INT, IN p_monto DECIMAL(10,2))
BEGIN
    DECLARE saldo_origen DECIMAL(10,2);
	START TRANSACTION;
    SELECT saldo INTO saldo_origen FROM cuentas WHERE CBU = p_cbu_origen;
	
    IF saldo_origen >= p_monto THEN
        UPDATE cuentas SET saldo = saldo - p_monto WHERE CBU = p_cbu_origen;
        UPDATE cuotas_prestamos SET estado = 'Pagada', fecha_pago = NOW() WHERE id_cuota_apagar = cuotas_prestamos.id_cuota;
	COMMIT;
        -- Opcional: Se pueden Insertar registros de movimiento aca
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No hay suficiente saldo para realizar la operacion';
        ROLLBACK;
    END IF;
END //
DELIMITER ;