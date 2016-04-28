SELECT
a.DATAORAPREN as dataOraPreno
,a.NOTAPREN as notaPren
,m.NOME as macchina
,pp.NOMEPUNTOPREL as nomePuntoPrel
,td.DESCRIZIONE tipoDona
,td.SIGLA as sigla
,d.COGNOMEENOME as nome
,d.LUOGONASCITA as luogoNascita
,d.PROVDINASCITA provNascita
,d.DATADINASCITA as dataNascita
,d.CODICEFISCALE codiceFiscale
,d.cellulare as cellulare
,d.domtel as telefono
from Agenda as a
inner join macchine as m on  m.ID = a.idMacchina
inner join puntoprelievo pp on pp.CODICEPUNTOPREL = m.pp
inner join tipodonaz td on td.CODICE = m.TIPODONAZ_ID
inner join donatore d on d.CODINTERNODONAT = a.CODINTERNODONAT
WHERE a.dataOraPren >= $P{dateFrom}
and a.dataOraPren <= $P{dateTo}
and a.CODINTERNODONAT is not null
and (m.PP = $P{puntoPrelievo} or 0 = $P{puntoPrelievo})
and (m.TIPODONAZ_ID = $P{tipoDona} or 0 = $P{tipoDona})
order by td.DESCRIZIONE, pp.NOMEPUNTOPREL
, a.dataOraPren, a.idMacchina