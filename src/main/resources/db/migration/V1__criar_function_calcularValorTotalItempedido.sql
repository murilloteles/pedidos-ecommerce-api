CREATE OR REPLACE FUNCTION valor_total_pedido (id_pedido_param numeric) 
RETURNS TABLE (total numeric)
LANGUAGE plpgsql AS
$$
BEGIN
	RETURN QUERY
	SELECT sum(i.quantidade * i.preco) AS "total"
	FROM   itens i
	WHERE  i.pedido_id = id_pedido_param
	GROUP BY i.pedido_id;
END
$$