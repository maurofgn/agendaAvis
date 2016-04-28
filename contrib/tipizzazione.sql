SELECT * FROM comunicf where id = -9585

SELECT
a.id,
a.nome, a.COGNOME
,a.IDLUOGONASCITA
,ti.kell, ti.cellano
--,ti.ab0 , te10.DESCRIZIONE
,re10.valore ab0
--,ti.rh, te11.DESCRIZIONE
,re11.valore rh
--,ti.ccee, te12.DESCRIZIONE
,re12.valore ccee
--,ti.du, te13.DESCRIZIONE
,re13.valore du
--,ti.kell, te14.DESCRIZIONE
,re14.valore Kell
--,ti.CELLANO, te51.DESCRIZIONE
, re51.valore Cellano
FROM ANAGRAFICA a
left join TIPIZZAZIONE ti on ti.IDANAGRAFICA = a.id
--
--left join TIPIESAME te10 on te10.id = 10 and te10.ISTIPIZZA = 1
left join RISULTATIESAME re10 on re10.tipoEsame = 10 and re10.indice = ti.AB0
--
--left join TIPIESAME te11 on te11.id = 11 and te11.ISTIPIZZA = 1
left join RISULTATIESAME re11 on re11.tipoEsame = 11 and re11.indice = ti.Rh
--left join TIPIESAME te12 on te12.id = 12 and te12.ISTIPIZZA = 1
left join RISULTATIESAME re12 on re12.tipoEsame = 12 and re12.indice = ti.ccee
--left join TIPIESAME te13 on te13.id = 13 and te13.ISTIPIZZA = 1
left join RISULTATIESAME re13 on re13.tipoEsame = 13 and re13.indice = ti.Du
--left join TIPIESAME te14 on te14.id = 14 and te14.ISTIPIZZA = 1
left join RISULTATIESAME re14 on re14.tipoEsame = 14 and re14.indice = ti.Kell
--left join TIPIESAME te51 on te51.id = 51 and te51.ISTIPIZZA = 1
left join RISULTATIESAME re51 on re51.tipoEsame = 51 and re51.indice = ti.Kell + case when re14.VALORE = 'pos' then 2 else 0 end 
where 
a.ID = 521314
--a.cognome = 'MARCOALDI'
--a.nome like 'mauro%'


--tipizzazione 
SELECT 
re10.valore ab0 
,re11.valore rh 
,re12.valore ccee 
,re13.valore du 
,re14.valore Kell 
,re51.valore Cellano 
FROM TIPIZZAZIONE ti 
left join RISULTATIESAME re10 on re10.tipoEsame = 10 and re10.indice = ti.AB0 
left join RISULTATIESAME re11 on re11.tipoEsame = 11 and re11.indice = ti.Rh 
left join RISULTATIESAME re12 on re12.tipoEsame = 12 and re12.indice = ti.ccee 
left join RISULTATIESAME re13 on re13.tipoEsame = 13 and re13.indice = ti.Du 
left join RISULTATIESAME re14 on re14.tipoEsame = 14 and re14.indice = ti.Kell 
left join RISULTATIESAME re51 on re51.tipoEsame = 51 and re51.indice = ti.Kell + case when re14.VALORE = 'pos' then 2 else 0 end 
where 
ti.IDANAGRAFICA = 521314	-- Mauro Fugante


SELECT
pi.valore
--,re.id rich_id
,se.*
FROM RICHESAMI re
inner join POPUPINTERNI pi on pi.idTipo = 155 and pi.indice = re.TIPORICHIESTA
inner join STORESAMI se on se.IDRICHIESTA = re.id
where re.IDANAGRAFICA = 521314
order by re.id


SELECT distinct idTipo, tipo FROM POPUPINTERNI where tipo like '%richi%'

SELECT * FROM POPUPINTERNI where idtipo = 155


SELECT * FROM STORESAMI

SELECT * FROM RISULTATIESAME


SELECT
re.tipoEsame, re.indice, re.valore
,re.*
FROM RISULTATIESAME re
where 
re.tipoEsame = 14


SELECT
re.tipoEsame, re.indice, re.valore
,re.*
FROM RISULTATIESAME re
where 
re.tipoEsame = 51
order by re.indice



SELECT
a.nome, a.COGNOME
,ti.*
--,ti.ab0 , te10.DESCRIZIONE
FROM ANAGRAFICA a
left join TIPIZZAZIONE ti on ti.IDANAGRAFICA = a.id
where 
a.nome like 'mauro%'


SELECT kell, cellano FROM TIPIZZAZIONE




SELECT
re.*
FROM RISULTATIESAME re
where re.tipoEsame = 10
and re.indice = 7



SELECT 
te.id,
--*
concat('left join TIPIESAME te', te.id , ' on ',  'te', te.id , '.id = ', te.id, ' and te',te.id,'.ISTIPIZZA = 1'),
concat('left join RISULTATIESAME re', te.id , ' on ',  're', te.id , '.tipoEsame = te', te.id, '.id and re',te.id,'.indice = ti.', te.descrizione)
FROM TIPIESAME te
where 
--id = 10
te.ISTIPIZZA = 1
and not te.id between 10 and 14
order by te.id


SELECT 
te.*
FROM TIPIESAME te
where 
--id = 10
te.ISTIPIZZA = 1
--and te.id between 10 and 14
order by te.id


SELECT * 
FROM POPUPINTERNI
where tipo like '%test%'

SELECT distinct tipo 
FROM POPUPINTERNI


SELECT idTipo, tipo, indice, valore
FROM POPUPINTERNI
where idTipo = 108
order by posizione


SELECT
*
FROM RISULTATIESAME
where tipoEsame = 10


SELECT * FROM app_user

